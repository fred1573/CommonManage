package com.frd.service;

import java.util.List;

import com.frd.model.AccountOut;

public interface AccountOutService {

	List<AccountOut> find();
	
	List<AccountOut> find(boolean verify);
	
	void produce(AccountOut aout);
	
	AccountOut get(int id);
	
	void check(AccountOut aout);
}
