package com.morgan.gateway.websocket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;

import org.java_websocket.WebSocket.READYSTATE;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import com.alibaba.fastjson.JSON;
import com.morgan.gateway.Entity.Order;

public class WSClient {

	public static void main(String[] args)
			throws URISyntaxException, NotYetConnectedException, UnsupportedEncodingException {
		
		String brokerCom = "SHFE";
		WebSocketClient client = new WebSocketClient(new URI("ws://localhost:8080/websocket2/"+brokerCom+"/gold%20swaps"), new Draft_6455()) {
		
			@Override
			public void onOpen(ServerHandshake arg0) {
				System.out.println("打开链接");
			}

			@Override
			public void onMessage(String arg0) {
				//Order order = JSON.parseObject(arg0, Order.class);
				System.out.println("收到消息" + arg0);
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
		client.connect();

		while (!client.getReadyState().equals(READYSTATE.OPEN)) {
			System.out.println("还没有打开");
		}
		System.out.println("打开了");
		
	}

}