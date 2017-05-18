package com.cab.graduation.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cab.graduation.dao.GoodsDAO;
import com.cab.graduation.entities.Goods;
import com.cab.graduation.utils.Conditions;

@Repository("goodsDAO")
public class GoodsDAOImpl implements GoodsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void saveOrUpdate(Goods goods) {
		getSession().saveOrUpdate(goods);
	}
	
	@Override
	public void delete(Goods goods) {
		getSession().delete(goods);
		
	}
	
	@Override
	public List<Goods> list() {
		String hql="from Goods";
		List<Goods> goodsList=getSession().createQuery(hql).setFirstResult(0).setMaxResults(8).list();
		System.out.println(goodsList);
		return goodsList;
	}

	@Override
	public List<Goods> findGoodsByTime() {
		String hql="from Goods g order by g.datetime desc";
		List<Goods> goodsList=getSession().createQuery(hql).setFirstResult(0).setMaxResults(8).list();
		return goodsList;
	}

	@Override
	public Goods findGoodsById(Serializable id) {
		return (Goods) getSession().get(Goods.class, Integer.valueOf(String.valueOf(id)));
	}

}
