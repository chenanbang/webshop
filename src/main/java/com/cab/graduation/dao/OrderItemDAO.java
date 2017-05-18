package com.cab.graduation.dao;

import java.util.List;

import com.cab.graduation.entities.OrderItem;

public interface OrderItemDAO {
	
	void save(OrderItem orderItem);
	
	void delete(OrderItem orderItem);
	
	void update(OrderItem orderItem);
	
	List<OrderItem> listOrderItems(Integer orderId);
	
}
