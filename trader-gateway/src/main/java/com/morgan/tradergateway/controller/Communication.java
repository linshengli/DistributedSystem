package com.morgan.tradergateway.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import com.morgan.tradergateway.model.Order;
import com.morgan.tradergateway.rmi.OrderService;

public class Communication {
	// 用rmi实现
	public static List<Order> getOrderBlotter(String brokerCompany,String product) throws NamingException, RemoteException {
		Context namingContext = new InitialContext();
		System.out.print("RMI registry bindings: ");
		NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost/" + brokerCompany);
		while (e.hasMore())
			System.out.println(e.next().getName());

		String url = "rmi://localhost:1099/order_shipper";
		OrderService orderService = (OrderService) namingContext.lookup(url);
		List<Order> orders = orderService.GetOrderBlotter(product);
		return orders;
	}

	public static Order addOrder(Order order, String brokerCompany) throws NamingException, RemoteException {
		order.setItradercom("MorganStanley");

		Context namingContext = new InitialContext();
		System.out.print("RMI registry bindings: ");
		NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost/" + brokerCompany);
		while (e.hasMore())
			System.out.println(e.next().getName());

		String url = "rmi://localhost:1099/add_order";
		OrderService orderService = (OrderService) namingContext.lookup(url);
		Order orders = orderService.addOrder(order);
		return orders;
	}
}
