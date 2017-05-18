package com.cab.graduation.service;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.cab.graduation.entities.User;

public interface UserService {
	
	public void saveOrUpdate(User user);
	
	public void delete(User user);
	
	public User findUserById(Serializable id);
	
	public User findUserByName(String username);
	
	public void executeActiveUser(String activeCode,HttpSession session);
	
	public User findUserByNameAndPassword(String username,String password);
	
	public List<User> findAllUser();
}
