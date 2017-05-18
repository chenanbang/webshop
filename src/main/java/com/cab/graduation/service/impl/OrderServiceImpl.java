package com.cab.graduation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.graduation.dao.OrderDAO;
import com.cab.graduation.entities.Order;
import com.cab.graduation.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;

	@Override
	public void save(Order order) {
		orderDAO.save(order);

	}

	@Override
	public void delete(Order order) {
		orderDAO.delete(order);
		
	}

	@Override
	public Order findOrderById(Integer orderId) {
		return orderDAO.findOrderById(orderId);
		
	}

	@Override
	public void update(Order order) {
		orderDAO.update(order);
		
	}

}
