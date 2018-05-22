package com.morgan.tradergateway.controller;

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

import model.Order;

public class WSClient {

	public static WebSocketClient client;
	public static WebSocketClient client_only_for_broadcast;

	public static void main(String[] args)
			throws URISyntaxException, NotYetConnectedException, UnsupportedEncodingException {
		
		client = new WebSocketClient(new URI("ws://localhost:8080/JavaWebSocket/websocket"), new Draft_6455()) {
		
			@Override
			public void onOpen(ServerHandshake arg0) {
				System.out.println("打开链接");
			}

			@Override
			public void onMessage(String arg0) {
				Order order = JSON.parseObject(arg0, Order.class);
				System.out.println("收到消息" + arg0+order.getCount());
				client_only_for_broadcast.send(arg0);
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
		
		client_only_for_broadcast = new WebSocketClient(new URI("ws://localhost:8080/JavaWebSocket/websocket"), new Draft_6455()) {
			
			@Override
			public void onOpen(ServerHandshake arg0) {
				System.out.println("打开链接");
			}

			@Override
			public void onMessage(String arg0) {
				Order order = JSON.parseObject(arg0, Order.class);
				System.out.println("收到消息" + arg0+order.getCount());
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
		client_only_for_broadcast.connect();

		while (!client.getReadyState().equals(READYSTATE.OPEN)) {
			//System.out.println("还没有打开");
		}
		System.out.println("打开了");
		
		Order order = new Order();
		order.setCount(3);
		String json = JSON.toJSONString(order);
		client.send(json);
	}

	public static void send(byte[] bytes) {
		client.send(bytes);
	}
}