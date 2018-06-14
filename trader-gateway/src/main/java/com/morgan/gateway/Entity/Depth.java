package com.morgan.gateway.Entity;

import java.io.Serializable;

public class Depth implements Serializable {
    private int level;
    private float price;
    private int volume;
    private int dpid;
    private String product;
	
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public void setLevel(int level){
		this.level = level;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void setPrice(float price){
		this.price = price;
	}
	
	public float getPrice(){
		return this.price;
	}
	
	public void setVolume(int volume){
		this.volume = volume;
	}
	
	public int getVolume(){
		return this.volume;
	}
	
	public void setDpid(int dpid){
		this.dpid = dpid;
	}
	
	public int getDpid(){
		return dpid;
	}
	
	public Depth(int level, float price, int volume){
		this.level = level;
		this.price = price;
		this.volume = volume;
    }
	public Depth() {
		super();
	}
}
