package com.cab.graduation.entities;

public class Cart {
	private Integer cId;
	private Integer cNum;
	private Double cSubtotal;
	private Goods goods;
	private User user;

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public Integer getcNum() {
		return cNum;
	}

	public void setcNum(Integer cNum) {
		this.cNum = cNum;
	}

	public Double getcSubtotal() {
		return cSubtotal;
	}

	public void setcSubtotal(Double cSubtotal) {
		this.cSubtotal = cSubtotal;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Cart [cId=" + cId + ", cNum=" + cNum + ", cSubtotal=" + cSubtotal + ", goods=" + goods + "]";
	}

}
