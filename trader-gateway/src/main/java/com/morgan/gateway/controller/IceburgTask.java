package com.morgan.gateway.controller;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.TimerTask;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.morgan.gateway.Entity.Order;
import com.morgan.gateway.rmi.OrderService;

public class IceburgTask extends TimerTask{
	private Map<String,RmiProxyFactoryBean> factoryBeans;
	private String brokerCompany;
	private Order order;
	private int volumn=20;//每次買多少手
	private OrderService orderService;

	public IceburgTask(String brokerCompany,Map<String,RmiProxyFactoryBean> factoryBeans, Order order) {
		super();
		this.brokerCompany = brokerCompany;
		this.factoryBeans = factoryBeans;
		this.order = order;
		orderService= (OrderService)factoryBeans.get(brokerCompany).getObject();
	}
	
    @Override
    public void run() {
    	int totalCount = order.getCount();
		Order smallOrder = new Order();
		smallOrder = order;
		smallOrder.setCount((totalCount<volumn)?totalCount:volumn);
		System.out.println((totalCount<volumn)?totalCount:volumn+" "+volumn);
		if(order.getCount()==0){
            System.out.println("Task 结束了！！");
            System.gc();
            cancel();
		}else{
			try {
				System.out.println(totalCount+" "+order.getCount()+" "+smallOrder.getCount());
				orderService.addOrder(smallOrder);
				//orderService.GetOrderBlotter("Gold Swaps");
				order.setCount(totalCount-smallOrder.getCount());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
    }
}