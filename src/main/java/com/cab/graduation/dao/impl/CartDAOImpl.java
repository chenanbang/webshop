package com.cab.graduation.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cab.graduation.dao.CartDAO;
import com.cab.graduation.entities.Cart;

@Repository("cartDAO")
public class CartDAOImpl implements CartDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addCart(Cart cart) {
		getSession().save(cart);
	}

	@Override
	public void deleteCart(Cart cart) {
		getSession().delete(cart);
	}

	@Override
	public Cart findCartByGoodIdAndUserId(Integer goodId,Integer userId) {
		//return (Cart) getSession().get(Cart.class, id);
		String hql="from Cart cart where cart.goods.goodId=? and cart.user.userId=?";
		Cart cart=(Cart) getSession().createQuery(hql).setInteger(0, goodId)
									 .setInteger(1, userId)
									 .uniqueResult();
		return cart;
	}

	@Override
	public void update(Cart cart) {
		getSession().update(cart);
		
	}

	@Override
	public List<Cart> listByUserId(Integer userId) {
		
		String hql="from Cart cart where cart.user.userId=?";
		return getSession().createQuery(hql).setInteger(0, userId).list();
	}

	@Override
	public Cart findCartById(Serializable id) {
		return (Cart) getSession().get(Cart.class, id);
	}
	
}
