package com.cab.graduation.service;

import com.cab.graduation.entities.Order;

public interface OrderService {

	void save(Order order);
	
	void delete(Order order);
	
	void update(Order order);
	
	Order findOrderById(Integer orderId);
}
