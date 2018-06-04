package com.morgan.tradergateway.websocket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;
import org.java_websocket.WebSocket.READYSTATE;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import com.alibaba.fastjson.JSON;
import com.morgan.tradergateway.model.Depth;
import com.morgan.tradergateway.model.Order;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket2/{brokerCom}/{product}")
@Component
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    
    //订阅broker-gateway的client
    private static CopyOnWriteArraySet<WebSocketClient> webSocketClients = new CopyOnWriteArraySet<WebSocketClient>();

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    private static Map<String,Map<String,CopyOnWriteArraySet<WebSocketServer>>> webSocketSets_brokerComp_product = new HashMap<String,Map<String,CopyOnWriteArraySet<WebSocketServer>>>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     * @throws URISyntaxException 
     */
    @OnOpen
    public void onOpen(@PathParam("brokerCom") String brokerCom,@PathParam("product") String product,Session session) throws URISyntaxException{
        this.session = session;
        if(webSocketSets_brokerComp_product.containsKey(brokerCom)){
        	Map<String,CopyOnWriteArraySet<WebSocketServer>> webSockets_product = webSocketSets_brokerComp_product.get(brokerCom);
        	if(webSockets_product.containsKey(product)){
        		webSockets_product.get(product).add(this);
        	}else{
        		CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
        		webSocketSet.add(this);
        		webSockets_product.put(product, webSocketSet);
        	}
        }else{
        	CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
        	webSocketSet.add(this);
        	Map<String,CopyOnWriteArraySet<WebSocketServer>> webSocketSets_product = new HashMap<String,CopyOnWriteArraySet<WebSocketServer>>();
        	webSocketSets_product.put(product, webSocketSet);
        	webSocketSets_brokerComp_product.put(brokerCom, webSocketSets_product);
        }
        //webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        
        WebSocketClient client = new WebSocketClient(new URI("ws://localhost:8080/websocket2"+brokerCom+product), new Draft_6455()) {
    		
			@Override
			public void onOpen(ServerHandshake arg0) {
				System.out.println("打开链接");
			}

			@Override
			public void onMessage(String arg0) {
				Depth depth = JSON.parseObject(arg0, Depth.class);
				System.out.println("收到消息" + arg0+depth.getPrice());
				//client_only_for_broadcast.send(arg0);
				try {
					WebSocketServer.sendInfo(brokerCom,product,arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onError(Exception arg0) {
				arg0.printStackTrace();
				System.out.println("发生错误已关闭");
			}

			@Override
			public void onClose(int arg0, String arg1, boolean arg2) {
				System.out.println("链接已关闭");
			}

			@Override
			public void onMessage(ByteBuffer bytes) {
				try {
					System.out.println(new String(bytes.array(), "utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

		};
		while (!client.getReadyState().equals(READYSTATE.OPEN)) {
			//System.out.println("还没有打开");
		}
		System.out.println("打开了");
        webSocketClients.add(client);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        //webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
        /*for(WebSocketServer item: webSocketSet){
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }*/
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
    
    /** 
     * 群发自定义消息 
     * */  
    public static void sendInfo(String brokerCom,String product,String message) throws IOException {  
        System.out.println("群发消息");
        for(WebSocketServer item: webSocketSets_brokerComp_product.get(brokerCom).get(product)){
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
    
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}