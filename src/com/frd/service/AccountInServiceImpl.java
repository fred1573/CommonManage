package com.frd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frd.dao.AccountInDao;
import com.frd.model.AccountIn;

@Service("accountInService")
public class AccountInServiceImpl implements AccountInService {

	@Resource
	private AccountInDao accountInDao;
	
	@Override
	public void produce(AccountIn ain) {
		this.accountInDao.save(ain);
	}

	@Override
	public List<AccountIn> find() {
		return this.accountInDao.find();
	}

	@Override
	public void check(AccountIn ain) {
		this.accountInDao.update(ain);
	}

	@Override
	public AccountIn get(int id) {
		return this.accountInDao.get(id);
	}

	@Override
	public List<AccountIn> find(boolean verify) {
		return this.accountInDao.find(verify);
	}

}
