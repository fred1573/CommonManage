package com.frd.dao;

import java.util.List;

import com.frd.model.CheckInfo;

public interface CheckInfoDao {

	List<CheckInfo> findByInOrOut(String inOrOut);
	
	List<CheckInfo> findByUserId(int userId);
	
	List<CheckInfo> findByAccountIdAndInOrOut(int accountId, String inOrOut);
	
	List<CheckInfo> find();
	
	void add(CheckInfo cinfo);
}
