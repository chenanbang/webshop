package com.cab.graduation.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cab.graduation.dao.UserDAO;
import com.cab.graduation.entities.User;
import com.cab.graduation.utils.SaltEncryptionUtils;

@Repository(value="userDAO")
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdate(User user) {
		getSession().saveOrUpdate(user);
	}

	@Override
	public void delete(User user) {
		getSession().delete(user);
	}

	@Override
	public User findUserById(Serializable id) {
		return (User) getSession().get(User.class, id);
	}

	@Override
	public User findUserByName(String username) {
		String hql="from User where username=?";
		User user=(User) getSession().createQuery(hql)
									 .setString(0, username)
									 .uniqueResult();
		return user;
	}

	@Override
	public User findUserByNameAndPassword(String username, String password) {
		User user = findUserByName(username);
		if(user!=null){
			if(SaltEncryptionUtils.verify(password, user.getPassword())){
				return user;
			}
		}
		return null;
	}
	
	@Override
	public List<User> findAllUser() {
		
		Object obj = getSession().createQuery("from User").list();
		System.out.println(obj.getClass());
		return null;
	}

	@Override
	public User findUserByActiveCode(String activeCode) {
		String hql="from User where activeCode=?";
		User user=(User) getSession().createQuery(hql)
									 .setString(0, activeCode)
									 .uniqueResult();
		return user;
	}

	@Override
	public List<User> listUser() {
		String hql="from User user";
		List<User> users=getSession().createQuery(hql).list();
		
		return users;
	}

}
