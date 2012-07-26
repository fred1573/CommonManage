package com.frd.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.frd.model.AccountOut;

@Component("accountOutDao")
public class AccountOutDaoImpl implements AccountOutDao {



	@Resource
	private HibernateTemplate hibernateTemplate;

	@Override
	public AccountOut get(int id) {
		return this.hibernateTemplate.get(AccountOut.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccountOut> find() {
		return this.hibernateTemplate.find("from AccountOut");
	}

	@Override
	public void save(AccountOut aout) {
		this.hibernateTemplate.save(aout);
	}
	

	@Override
	public void update(AccountOut aout) {
		this.hibernateTemplate.update(aout);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountOut> find(boolean verify) {
		return this.hibernateTemplate.find("from AccountOut where verify=?", verify);
	}

}
