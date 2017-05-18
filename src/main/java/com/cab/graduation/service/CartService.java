package com.cab.graduation.service;

import java.io.Serializable;
import java.util.List;

import com.cab.graduation.entities.Cart;

public interface CartService {
	void addCart(Cart cart);
	void deleteCart(Cart cart);
	void update(Cart cart);
	Cart findCartByGoodIdAndUserId(Integer goodId,Integer userId);
	Cart findCartById(Serializable id);
	List<Cart> listByUserId(Integer userId);
}
