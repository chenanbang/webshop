package com.cab.graduation.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cab.graduation.dao.OrderDAO;
import com.cab.graduation.dao.OrderItemDAO;
import com.cab.graduation.entities.Order;
import com.cab.graduation.entities.OrderItem;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private OrderItemDAO orderItemDAO;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save(Order order) {
		getSession().save(order);
	}

	@Override
	public void delete(Order order) {
		if(order!=null){
			List<OrderItem> orderItems=orderItemDAO.listOrderItems(order.getOrderId());
			for(OrderItem orderItem : orderItems){
				//先删掉订单项
				orderItemDAO.delete(orderItem);
			}
			//再删除订单
			getSession().delete(order);
		}
		
	}

	@Override
	public void update(Order order) {
		getSession().update(order);
	}
	
	@Override
	public List<Order> queryOrders() {
		String hql="from Order order";
		List<Order> orders=getSession().createQuery(hql).list();
		
		return orders;
	}

	@Override
	public Order findOrderById(Integer orderId) {
		return (Order) getSession().get(Order.class, orderId);
	}

}
