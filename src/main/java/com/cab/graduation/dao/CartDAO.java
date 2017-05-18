package com.cab.graduation.dao;

import java.io.Serializable;
import java.util.List;

import com.cab.graduation.entities.Cart;

public interface CartDAO {
	void addCart(Cart cart);
	void deleteCart(Cart cart);
	void update(Cart cart);
	Cart findCartByGoodIdAndUserId(Integer goodId,Integer userId);
	Cart findCartById(Serializable id);
	List<Cart> listByUserId(Integer userId);
	
}
