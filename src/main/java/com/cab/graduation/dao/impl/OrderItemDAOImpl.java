package com.cab.graduation.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cab.graduation.dao.OrderItemDAO;
import com.cab.graduation.entities.OrderItem;

@Repository("orderItemDAO")
public class OrderItemDAOImpl implements OrderItemDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save(OrderItem orderItem) {
		
		getSession().save(orderItem);
		
	}

	@Override
	public void delete(OrderItem orderItem) {

		getSession().delete(orderItem);
	}

	@Override
	public void update(OrderItem orderItem) {

		getSession().update(orderItem);
	}

	@Override
	public List<OrderItem> listOrderItems(Integer orderId) {
		String hql="from OrderItem orderItem where orderItem.order.orderId=?";
		
		List<OrderItem> orderItems=getSession().createQuery(hql).setInteger(0, orderId)
									 .list();
		
		return orderItems;
	}

	
}
