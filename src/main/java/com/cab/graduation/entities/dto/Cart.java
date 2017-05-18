package com.cab.graduation.entities.dto;

import com.cab.graduation.entities.Goods;
import com.cab.graduation.entities.OrderItem;

public class Cart {

	private Integer cId;
	private Integer goodId;
	private String smallImagePath;
	private String goodDescribe;
	private Double goodPrice;
	private Integer cNum;
	private Double subTotalPrice;
	private Integer goodStore;

	public Cart() {
		super();
	}
	
	

	public Cart(OrderItem orderItem) {
		super();
		this.goodId=orderItem.getGoods().getGoodId();
		this.smallImagePath = orderItem.getGoods().getGoodSmallPath();
		this.goodDescribe = orderItem.getGoods().getGoodDescribe().substring(0, 9)+"...";
		this.goodPrice = orderItem.getGoods().getGoodSpikePrice();
		this.cNum = orderItem.getOrderItemNum();
		this.subTotalPrice = orderItem.getOrderItemSubtotal();
		
	}



	public Cart(Goods goods,com.cab.graduation.entities.Cart cart) {
		super();
		this.cId=cart.getcId();
		this.goodId=goods.getGoodId();
		this.smallImagePath = goods.getGoodSmallPath();
		this.goodDescribe = goods.getGoodDescribe().substring(0, 9)+"...";
		this.goodPrice = goods.getGoodSpikePrice();
		this.cNum = cart.getcNum();
		this.subTotalPrice = cart.getcSubtotal();
		this.goodStore=goods.getGoodStore();
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public String getSmallImagePath() {
		return smallImagePath;
	}

	public void setSmallImagePath(String smallImagePath) {
		this.smallImagePath = smallImagePath;
	}

	public String getGoodDescribe() {
		return goodDescribe;
	}

	public void setGoodDescribe(String goodDescribe) {
		this.goodDescribe = goodDescribe;
	}

	public Double getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(Double goodPrice) {
		this.goodPrice = goodPrice;
	}

	public Integer getcNum() {
		return cNum;
	}

	public void setcNum(Integer cNum) {
		this.cNum = cNum;
	}

	public Double getSubTotalPrice() {
		return subTotalPrice;
	}

	public void setSubTotalPrice(Double subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}

	public Integer getGoodStore() {
		return goodStore;
	}

	public void setGoodStore(Integer goodStore) {
		this.goodStore = goodStore;
	}

	@Override
	public String toString() {
		return "Cart [cId=" + cId + ", goodId=" + goodId + ", smallImagePath=" + smallImagePath + ", goodDescribe="
				+ goodDescribe + ", goodPrice=" + goodPrice + ", cNum=" + cNum + ", subTotalPrice=" + subTotalPrice
				+ ", goodStore=" + goodStore + "]";
	}

}
