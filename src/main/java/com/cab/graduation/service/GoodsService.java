package com.cab.graduation.service;

import java.io.Serializable;
import java.util.List;

import com.cab.graduation.entities.dto.Goods;
import com.cab.graduation.utils.Conditions;
import com.cab.graduation.utils.Page;

public interface GoodsService {
	
	void saveOrUpdate(com.cab.graduation.entities.Goods goods);
	void delete(com.cab.graduation.entities.Goods goods);
	com.cab.graduation.entities.Goods findGoodsById(Serializable id);
	List<Goods> listProduct();
	List<Goods> findGoodsByTime();
	List<Goods> list();
	
	void pageQuery(Class clazz,Page page,Conditions conditions);
	
}
