package com.morgan.BrokerGateway.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="msorder")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
    private int id;
	@Column(name="product")
    private String product;
	@Column(name="period")
    private String period;
	@Column(name="broker")
    private String broker;
	@Column(name="count")
    private int count;
    @Column(name="price")
    private float price;
    @Column(name="status")
    private int status;
    @Column(name="time")
    private Date time;
    @Column(name="type")
    private int type;
    //init trader
    @Column(name="itrader")
    private String itrader;
    @Column(name="itradercom")
    private String itradercom;
    @Column(name="iside")
    private char iside;
    //finish trader
    @Column(name="ftrader")
    private String ftrader;
    @Column(name="ftradercom")
    private String ftradercom;
    @Column(name="fside")
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
    
    public Order(){
    	super();
    }

    
}
