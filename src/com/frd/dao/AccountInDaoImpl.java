package com.frd.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.frd.model.AccountIn;

@Component("accountInDao")
public class AccountInDaoImpl implements AccountInDao {

	@Resource
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(AccountIn ain) {
		this.hibernateTemplate.save(ain);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountIn> find() {
		return this.hibernateTemplate.find("from AccountIn order by id");
	}

	@Override
	public void update(AccountIn ain) {
		this.hibernateTemplate.update(ain);
	}

	@Override
	public AccountIn get(int id) {
		return this.hibernateTemplate.get(AccountIn.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountIn> find(boolean verify) {
		return this.hibernateTemplate.find("from AccountIn where verify=?", verify);
	}

	
}
