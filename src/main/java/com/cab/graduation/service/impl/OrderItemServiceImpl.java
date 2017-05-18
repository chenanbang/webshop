package com.cab.graduation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cab.graduation.dao.OrderItemDAO;
import com.cab.graduation.entities.OrderItem;
import com.cab.graduation.service.OrderItemService;

@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemDAO orderItemDAO;
	
	@Override
	public void save(OrderItem orderItem) {

		orderItemDAO.save(orderItem);
	}

	@Override
	public void delete(OrderItem orderItem) {
		orderItemDAO.delete(orderItem);

	}

	@Override
	public void update(OrderItem orderItem) {
		orderItemDAO.update(orderItem);

	}


}
