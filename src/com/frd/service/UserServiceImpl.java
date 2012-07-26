package com.frd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frd.dao.UserDao;
import com.frd.model.User;

@Service(value="userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Override
	public void register(User u) {
		this.userDao.save(u);
	}

	@Override
	public List<User> findByName(String name) {
		return this.userDao.findByName(name);
	}

	@Override
	public List<User> find(User u) {
		return this.userDao.find(u);
	}

	@Override
	public List<User> find() {
		return this.userDao.find();
	}

	@Override
	public User get(int id) {
		return this.userDao.get(id);
	}
	
	@Override
	public void lock(User u){
		this.userDao.update(u); 
	}

	@Override
	public void update(User u) {
		this.userDao.update(u);
	}

	@Override
	public List<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return this.userDao.findByEmail(email);
	}

}
