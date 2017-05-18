package com.cab.graduation.dao;

import java.util.List;

import com.cab.graduation.entities.Order;

public interface OrderDAO {
	
	void save(Order order);
	void delete(Order order);
	void update(Order order);
	Order findOrderById(Integer orderId);
	List<Order> queryOrders();
	
}
