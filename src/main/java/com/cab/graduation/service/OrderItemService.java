package com.cab.graduation.service;

import java.util.List;

import com.cab.graduation.entities.OrderItem;

public interface OrderItemService {
	
	void save(OrderItem orderItem);
	
	void delete(OrderItem orderItem);
	
	void update(OrderItem orderItem);
	
	
}
