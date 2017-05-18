package com.cab.graduation.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

public class Order {
	private Integer orderId;
	private String orderNo;
	private Double orderTotalPrice;
	
	//指定日期的类型转换格式
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date orderCreateTime;
	
	private String isPay;
	private Integer orderStatus;
	private User user;
	private Address address;
	
	private Integer del;
	
	private Set<OrderItem> orderItems=new HashSet<>();
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Order(String orderNo, Double orderTotalPrice, Date orderCreateTime, String isPay, Integer orderStatus,
			User user, Set<OrderItem> orderItems) {
		super();
		this.orderNo = orderNo;
		this.orderTotalPrice = orderTotalPrice;
		this.orderCreateTime = orderCreateTime;
		this.isPay = isPay;
		this.orderStatus = orderStatus;
		this.user = user;
		this.orderItems = orderItems;
	}


	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(Double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderNo=" + orderNo + ", orderTotalPrice=" + orderTotalPrice
				+ ", orderCreateTime=" + orderCreateTime + ", isPay=" + isPay + ", orderStatus=" + orderStatus
				+ ", del=" + del + "]";
	}

}
