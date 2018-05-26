package com.morgan.tradergateway.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import com.morgan.tradergateway.model.Order;

public class Communication {
	// 用rmi实现
	public static ArrayList<Order> getOrderBlotter(String brokerCompany) throws NamingException, RemoteException {
		Context namingContext = new InitialContext();
		System.out.print("RMI registry bindings: ");
		NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost/" + brokerCompany);
		while (e.hasMore())
			System.out.println(e.next().getName());

		String url = "rmi://localhost:1099/order_shipper";
		RmiUtil rmiUtil = (RmiUtil) namingContext.lookup(url);
		ArrayList<Order> orders = rmiUtil.getOrderBlotter();
		return orders;
	}

	public static Order addOrder(Order order, String brokerCompany) throws NamingException, RemoteException {
		order.setITradercom("MorganStanley");

		Context namingContext = new InitialContext();
		System.out.print("RMI registry bindings: ");
		NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost/" + brokerCompany);
		while (e.hasMore())
			System.out.println(e.next().getName());

		String url = "rmi://localhost:1099/add_order";
		RmiUtil rmiUtil = (RmiUtil) namingContext.lookup(url);
		Order orders = rmiUtil.addOrder(order);
		return orders;
	}
}
