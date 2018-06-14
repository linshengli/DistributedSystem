package com.morgan.gateway.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
    private int id;
    private String product;
    private String period;
    private String broker;
    private int count;
    private int price;
    private int status;
    private Timestamp time;
    private int type;
    //init trader
    private String itrader;
    private String itradercom;
    private char iside;
    //finish trader
    private String ftrader;
    private String ftradercom;
    private char fside;
    private Timestamp ftime;
    
    public Timestamp getFtime() {
		return ftime;
	}

	public void setFtime(Timestamp ftime) {
		this.ftime = ftime;
	}

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

    public void setItrader(String itrader) {
        this.itrader = itrader;
    }
    
    public String getItradercom() {
        return itradercom;
    }

    public void setItradercom(String itradercom) {
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

    public void setFtrader(String ftrader) {
        this.ftrader = ftrader;
    }
    
    public String getFtradercom() {
        return ftradercom;
    }

    public void setFtradercom(String ftradercom) {
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public Order(){
    	super();
    }



    
}
