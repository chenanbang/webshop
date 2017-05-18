package com.cab.graduation.dao;

import java.io.Serializable;
import java.util.List;

import com.cab.graduation.entities.Goods;
import com.cab.graduation.utils.Conditions;


public interface GoodsDAO {
	
	void saveOrUpdate(Goods goods);
	void delete(Goods goods);
	Goods findGoodsById(Serializable id);
	List<Goods> list();
	List<Goods> findGoodsByTime();
	
}
