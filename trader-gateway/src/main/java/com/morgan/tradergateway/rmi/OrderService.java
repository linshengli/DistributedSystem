package com.morgan.tradergateway.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.morgan.tradergateway.model.Order;

public interface OrderService extends Remote {
	
	public List<Order> GetOrderBlotter(String product) throws RemoteException;
	
	public void hello() throws RemoteException;
	
	public Order addOrder(Order order) throws RemoteException;
}
