package com.cab.graduation.entities;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

public class Goods {
	private Integer goodId;
	private String goodName;
	private Double goodPrimePrice;
	private Double goodSpikePrice;
	private Integer goodStore;
	private String goodSimpleDesc;
	private String goodDescribe;
	private String goodPath;
	private String goodDetailPath;
	private String goodSmallPath;
	
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date datetime;
	
	private Classify classify;
	
	private Integer del;
	
	private Set<OrderItem> orderItems;
	private Set<Cart> carts;

	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Goods(Integer goodId) {
		super();
		this.goodId = goodId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public Double getGoodPrimePrice() {
		return goodPrimePrice;
	}

	public void setGoodPrimePrice(Double goodPrimePrice) {
		this.goodPrimePrice = goodPrimePrice;
	}

	public Double getGoodSpikePrice() {
		return goodSpikePrice;
	}

	public void setGoodSpikePrice(Double goodSpikePrice) {
		this.goodSpikePrice = goodSpikePrice;
	}

	public Integer getGoodStore() {
		return goodStore;
	}

	public void setGoodStore(Integer goodStore) {
		this.goodStore = goodStore;
	}

	public String getGoodSimpleDesc() {
		return goodSimpleDesc;
	}

	public void setGoodSimpleDesc(String goodSimpleDesc) {
		this.goodSimpleDesc = goodSimpleDesc;
	}

	public String getGoodDescribe() {
		return goodDescribe;
	}

	public void setGoodDescribe(String goodDescribe) {
		this.goodDescribe = goodDescribe;
	}

	public String getGoodPath() {
		return goodPath;
	}

	public void setGoodPath(String goodPath) {
		this.goodPath = goodPath;
	}

	public String getGoodDetailPath() {
		return goodDetailPath;
	}

	public void setGoodDetailPath(String goodDetailPath) {
		this.goodDetailPath = goodDetailPath;
	}
	
	public String getGoodSmallPath() {
		return goodSmallPath;
	}

	public void setGoodSmallPath(String goodSmallPath) {
		this.goodSmallPath = goodSmallPath;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	public Classify getClassify() {
		return classify;
	}

	public void setClassify(Classify classify) {
		this.classify = classify;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

	@Override
	public String toString() {
		return "Goods [goodId=" + goodId + ", goodName=" + goodName + ", goodPrimePrice=" + goodPrimePrice
				+ ", goodSpikePrice=" + goodSpikePrice + ", goodStore=" + goodStore + ", goodSimpleDesc="
				+ goodSimpleDesc + ", goodDescribe=" + goodDescribe + ", goodPath=" + goodPath + ", goodDetailPath="
				+ goodDetailPath + ", goodSmallPath=" + goodSmallPath + ", datetime=" + datetime + ", del=" + del + "]";
	}

}
