package com.cab.graduation.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cab.graduation.utils.Conditions;
import com.cab.graduation.utils.Page;

@Repository
public class CommonDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 查询指定表的总记录数
	 * @param clazz
	 * @return
	 */
	public long queryCountWidthConditions(Class clazz,Conditions conditions){
		String hql=null;
		if(conditions==null||conditions.getSqlFragment()==null){
			hql="select count(*) from "+clazz.getSimpleName();
			return (long) getSession().createQuery(hql).uniqueResult();
		}else{
			// String sqlFragment=" where g.classify.id = ? "
			String simpleName=clazz.getSimpleName();
			String alias=simpleName.toLowerCase();
			hql="select count(*) from "+simpleName+" "+alias+conditions.getSqlFragment();
			System.out.println(hql);
			Query query=getSession().createQuery(hql);
			for(int i=0;i<conditions.getParams().length;i++){
				query.setString(i, (String) conditions.getParams()[i]);
			}
			return (long)query.uniqueResult();
		}
		
	}
	
	
	/**
	 * 查询指定区间的记录
	 * @param clazz
	 * @param page
	 * @param conditions
	 * @return
	 */
	public <T> List<T> page(Class<T> clazz,Page<T> page,Conditions conditions){
		boolean flag=false;
		String classSimpleName=clazz.getSimpleName();
		String alias=classSimpleName.toLowerCase();
		int pageSize=page.getPageSize();
		int index=(page.getPageStartNum()-1)*pageSize;
		StringBuilder hql=new StringBuilder();
		hql.append("from ").append(classSimpleName)
		   .append(" ").append(alias);
		if(conditions.getSqlFragment()!=null){
//			 String condition=" where g.classify.id = ?"
			flag=true;
			hql.append(conditions.getSqlFragment());
		}

		Query query=getSession().createQuery(hql.toString());
		
		//里面的值是从0开始的、也就是页数的索引
		query.setFirstResult(index);
		query.setMaxResults(pageSize);
		if(flag){
			for(int i=0;i<conditions.getParams().length;i++){
				query.setString(i, (String) conditions.getParams()[i]);
			}
		}
		List<T> ts=query.list();
		return ts;
	}
	
}
