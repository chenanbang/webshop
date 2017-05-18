package com.cab.graduation.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.graduation.dao.CartDAO;
import com.cab.graduation.entities.Cart;
import com.cab.graduation.service.CartService;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDAO;
	
	@Override
	public void addCart(Cart cart) {
		cartDAO.addCart(cart);
	}

	@Override
	public void deleteCart(Cart cart) {
		cartDAO.deleteCart(cart);
	}

	@Override
	public void update(Cart cart) {
		cartDAO.update(cart);
	}
	
	@Override
	public Cart findCartByGoodIdAndUserId(Integer goodId,Integer userId) {
		return cartDAO.findCartByGoodIdAndUserId(goodId,userId);
	}

	@Override
	public List<Cart> listByUserId(Integer userId) {
		return cartDAO.listByUserId(userId);
	}

	@Override
	public Cart findCartById(Serializable id) {
		
		return cartDAO.findCartById(id);
	}

	
	
}
