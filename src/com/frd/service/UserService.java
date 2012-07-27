package com.frd.service;

import java.util.List;

import com.frd.model.User;

public interface UserService {

	void register(User u);
	
	List<User> findByName(String name);
	
	List<User> findByEmail(String email);
	
	List<User> find(User u);
	
	List<User> find();
	
	User get(int id);
	
	void lock(User u);
	
	void update(User u);
	
	void delete(User u);
}
