package com.frd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frd.dao.AccountOutDao;
import com.frd.model.AccountOut;

@Service("accountOutService")
public class AccountOutServiceImpl implements AccountOutService {

	@Resource
	private AccountOutDao accountOutDao;
	
	@Override
	public List<AccountOut> find() {
		return this.accountOutDao.find();
	}

	@Override
	public void produce(AccountOut aout) {
		this.accountOutDao.save(aout);
	}

	@Override
	public AccountOut get(int id) {
		return this.accountOutDao.get(id);
	}

	@Override
	public void check(AccountOut aout) {
		this.accountOutDao.update(aout);
	}

	@Override
	public List<AccountOut> find(boolean verify) {
		return this.accountOutDao.find(verify);
	}

	
}
