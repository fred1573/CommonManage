package com.frd.dao;

import java.util.List;

import com.frd.model.AccountIn;

public interface AccountInDao {
	
	void save(AccountIn ain);
	
	List<AccountIn> find();
	
	List<AccountIn> find(boolean verify);
	
	void update(AccountIn ain);
	
	AccountIn get(int id);
}
