package com.cab.graduation.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.stereotype.Service;

import com.cab.graduation.dao.UserDAO;
import com.cab.graduation.entities.User;
import com.cab.graduation.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void saveOrUpdate(User user) {
		userDAO.saveOrUpdate(user);
	}

	@Override
	public void delete(User user) {
		userDAO.delete(user);
	}

	@Override
	public User findUserById(Serializable id) {
		return userDAO.findUserById(id);
	}

	@Override
	public User findUserByName(String username) {
		return userDAO.findUserByName(username);
	}

	@Override
	public User findUserByNameAndPassword(String username, String password) {
		return userDAO.findUserByNameAndPassword(username, password);
	}
	
	@Override
	public List<User> findAllUser() {
		return userDAO.findAllUser();
	}

	@Override
	public void executeActiveUser(String activeCode,HttpSession session) {
		User user=userDAO.findUserByActiveCode(activeCode);
		if(user!=null&&user.getState()==0){
			user.setState(1);
			userDAO.saveOrUpdate(user);
			session.setAttribute("activeStatus", 1);
		}else{
			session.setAttribute("activeStatus", 0);
		}
	}

}
