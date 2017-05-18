package com.cab.graduation.dao;

import java.io.Serializable;
import java.util.List;

import com.cab.graduation.entities.User;

public interface UserDAO {
	
	public void saveOrUpdate(User user);

	public void delete(User user);

	public User findUserById(Serializable id);

	public User findUserByName(String username);
	
	public List<User> listUser();
	
	public User findUserByActiveCode(String activeCode);

	public User findUserByNameAndPassword(String username,String password);
	
	public List<User> findAllUser();

}
