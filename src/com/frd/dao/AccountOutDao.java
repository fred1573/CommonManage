package com.frd.dao;

import java.util.List;

import com.frd.model.AccountOut;

public interface AccountOutDao {
	
	List<AccountOut> find();
	
	List<AccountOut> find(boolean verify);
	
	void save(AccountOut aout);
	
	AccountOut get(int id);
	
	void update(AccountOut aout);
}
