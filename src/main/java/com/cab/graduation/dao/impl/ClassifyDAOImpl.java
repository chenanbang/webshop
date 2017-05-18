package com.cab.graduation.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cab.graduation.dao.ClassifyDAO;
import com.cab.graduation.entities.Classify;

@Repository("classifyDAO")
public class ClassifyDAOImpl implements ClassifyDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<Classify> list() {
		String hql="from Classify c";
		List<Classify> classifies=getSession().createQuery(hql).list();
		return classifies;
	}

}
