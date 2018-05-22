package com.morgan.tradergateway.controller;

import java.util.ArrayList;

import java.rmi.*;

import model.Order;

public interface RmiUtil extends Remote{
	public ArrayList<Order> getOrderBlotter() throws RemoteException;
	public Order addOrder(Order order) throws RemoteException;
}
