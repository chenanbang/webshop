package com.cab.graduation.entities.dto;

import java.util.Date;

public class Goods {
	private Integer goodId;
	private String goodName;
	private Double goodPrimePrice;
	private Double goodSpikePrice;
	private Integer goodStore;
	private String goodDescribe;
	private Date datetime;
	private String classifyName;
	private String goodPath;

	public Goods() {

	}

	public Goods(com.cab.graduation.entities.Goods goods) {
		goodId=goods.getGoodId();
		goodName = goods.getGoodName();
		goodPrimePrice = goods.getGoodPrimePrice();
		goodSpikePrice = goods.getGoodSpikePrice();
		goodDescribe = goods.getGoodSimpleDesc();
		
//		datetime=goods.getDatetime();
//		goodStore=goods.getGoodStore();
//		this.classifyName=goods.getClassify().getCategory();
		
		goodPath = goods.getGoodPath();
		
	}

	public Goods(com.cab.graduation.entities.Goods goods,String classifyName) {
		goodId=goods.getGoodId();
		goodName = goods.getGoodName();
		goodPrimePrice = goods.getGoodPrimePrice();
		goodSpikePrice = goods.getGoodSpikePrice();
		goodDescribe = goods.getGoodSimpleDesc();
		datetime=goods.getDatetime();
		goodStore=goods.getGoodStore();
		this.classifyName=classifyName;
		
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

	public String getGoodDescribe() {
		return goodDescribe;
	}

	public void setGoodDescribe(String goodDescribe) {
		this.goodDescribe = goodDescribe;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getGoodPath() {
		return goodPath;
	}

	public void setGoodPath(String goodPath) {
		this.goodPath = goodPath;
	}

	@Override
	public String toString() {
		return "Goods [goodId=" + goodId + ", goodName=" + goodName + ", goodPrimePrice=" + goodPrimePrice
				+ ", goodSpikePrice=" + goodSpikePrice + ", goodDescribe=" + goodDescribe + ", datetime=" + datetime
				+ ", classifyName=" + classifyName + ", goodPath=" + goodPath + "]";
	}

}
