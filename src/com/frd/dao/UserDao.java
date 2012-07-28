package com.frd.dao;

import java.util.List;

import com.frd.model.User;

public interface UserDao {
	
	void save(User u);
	
	List<User> findByName(String name);
	
	List<User> findByEmail(String email);
	
	List<User> find(User u);
	
	List<User> find();
	
	User get(int id);
	
	void update(User u);
	
	void delete(User u);
}
