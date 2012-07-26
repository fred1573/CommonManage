package com.frd.service;

import java.util.List;

import com.frd.model.CheckInfo;

public interface CheckInfoService {

	List<CheckInfo> findByInOrOut(String inOrOut);
	
	List<CheckInfo> findByUserId(int userId);
	
	List<CheckInfo> findByAccountIdAndInOrOut(int accountId, String inOrOut);
	
	List<CheckInfo> find();
	
	void create(CheckInfo cinfo);
	
}
