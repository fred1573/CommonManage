package com.frd.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.frd.model.User;

@Component(value="userDao")
public class UserDaoImpl implements UserDao {

	@Resource
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(User u) {
		hibernateTemplate.save(u);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByName(String name) {
		return hibernateTemplate.find("from User where name=?", name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> find(User u) {
		return hibernateTemplate.find("from User where name=? and password=?", u.getName(), u.getPassword());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> find() {
		return hibernateTemplate.find("from User");
	}

	@Override
	public User get(int id) {
		return hibernateTemplate.get(User.class, id);
	}

	@Override
	public void update(User u) {
		hibernateTemplate.update(u);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return hibernateTemplate.find("from User where email=?", email);
	}

	@Override
	public void delete(User u) {
		this.hibernateTemplate.delete(u);
	}
	
	

}
