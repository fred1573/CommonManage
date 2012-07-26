package com.frd.service;

import java.util.List;

import com.frd.model.AccountIn;

public interface AccountInService {
	
	void produce(AccountIn ain);
	
	List<AccountIn> find();
	
	List<AccountIn> find(boolean verify);
	
	void check(AccountIn ain);
	
	AccountIn get(int id);
}
