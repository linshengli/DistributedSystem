package com.morgan.tradergateway.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String product;
	private String period;
	private String broker;
	private int count;
	private int price;
	private int status;
	private Date time;
	private int type;

	private String itrader;
	private String itradercom;
	private char iside;

	private String ftrader;
	private String ftradercom;
	private char fside;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getItrader() {
		return itrader;
	}

	public void setITrader(String itrader) {
		this.itrader = itrader;
	}

	public String getItradercom() {
		return itradercom;
	}

	public void setITradercom(String itradercom) {
		this.itradercom = itradercom;
	}

	public char getIside() {
		return iside;
	}

	public void setIside(char iside) {
		this.iside = iside;
	}

	public String getFtrader() {
		return ftrader;
	}

	public void setFTrader(String ftrader) {
		this.ftrader = ftrader;
	}

	public String getFtradercom() {
		return ftradercom;
	}

	public void setFTradercom(String ftradercom) {
		this.ftradercom = ftradercom;
	}

	public char getFside() {
		return fside;
	}

	public void setFside(char fside) {
		this.fside = fside;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
