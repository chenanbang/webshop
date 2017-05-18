package com.cab.graduation.entities;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

public class Address {
	private Integer addrId;
	private String address;
	private String receiver;
	private String receiverPhone;
	private String defaultAddress;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	private User user;
	
	private Set<Order> orderses;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Address(Integer addrId) {
		super();
		this.addrId = addrId;
	}

	public Address(Integer addrId, String address, String receiver, String receiverPhone) {
		super();
		this.addrId = addrId;
		this.address = address;
		this.receiver = receiver;
		this.receiverPhone = receiverPhone;
	}

	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(String defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Order> getOrderses() {
		return orderses;
	}

	public void setOrderses(Set<Order> orderses) {
		this.orderses = orderses;
	}

	@Override
	public String toString() {
		return "Address [addrId=" + addrId + ", address=" + address + ", receiver=" + receiver + ", receiverPhone="
				+ receiverPhone + ", defaultAddress=" + defaultAddress + ", createTime=" + createTime + "]";
	}
	
}
