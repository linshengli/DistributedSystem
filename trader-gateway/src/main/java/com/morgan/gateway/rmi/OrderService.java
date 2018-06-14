package com.morgan.gateway.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.morgan.gateway.Entity.Order;

public interface OrderService extends Remote {
	
	public List<Order> GetOrderBlotter(String product) throws RemoteException;
	
	public void hello() throws RemoteException;
	
	public void addOrder(Order order) throws RemoteException;

	public List<Order> GetHistoryOrders(String username,String traderCom,int page) throws RemoteException;

	public void cancelOrder(int orderid) throws RemoteException;
}
