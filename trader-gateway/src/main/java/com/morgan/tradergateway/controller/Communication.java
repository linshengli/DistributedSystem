package com.morgan.tradergateway.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import model.Order;

public class Communication {
	public static ArrayList<Order> getOrderBlotter(String BrokerCompany) throws NamingException, RemoteException {
		// 用rmi实现
		Context namingContext = new InitialContext();
		System.out.print("RMI registry bindings: ");
		NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost/" + BrokerCompany);
		while (e.hasMore())
			System.out.println(e.next().getName());

		String url = "rmi://localhost:1099/order_shipper";
		RmiUtil orderBlotter = (RmiUtil) namingContext.lookup(url);
		ArrayList<Order> orders = orderBlotter.getOrderBlotter();
		return orders;
	}

	public static Order addOrder(Order order) {
		order.setITradercom("MorganStanley");
		return null;
	}
}
