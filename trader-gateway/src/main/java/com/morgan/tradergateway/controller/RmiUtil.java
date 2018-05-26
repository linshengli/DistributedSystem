package com.morgan.tradergateway.controller;

import java.util.ArrayList;

import com.morgan.tradergateway.model.Order;

import java.rmi.*;

public interface RmiUtil extends Remote{
	public ArrayList<Order> getOrderBlotter() throws RemoteException;
	public Order addOrder(Order order) throws RemoteException;
}
