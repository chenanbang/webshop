package com.cab.graduation.entities;

public class OrderItem {
	private Integer orderItemId;
	private Integer orderItemNum;
	private Double orderItemSubtotal;
	private Order order;
	private Goods goods;

	public OrderItem() {
		super();
	}

	public OrderItem(Integer orderItemNum, Double orderItemSubtotal) {
		super();
		this.orderItemNum = orderItemNum;
		this.orderItemSubtotal = orderItemSubtotal;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getOrderItemNum() {
		return orderItemNum;
	}

	public void setOrderItemNum(Integer orderItemNum) {
		this.orderItemNum = orderItemNum;
	}

	public Double getOrderItemSubtotal() {
		return orderItemSubtotal;
	}

	public void setOrderItemSubtotal(Double orderItemSubtotal) {
		this.orderItemSubtotal = orderItemSubtotal;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", orderItemNum=" + orderItemNum + ", orderItemSubtotal="
				+ orderItemSubtotal + ", goods=" + goods + "]";
	}

}
