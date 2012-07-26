package com.frd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frd.dao.CheckInfoDao;
import com.frd.model.CheckInfo;

@Service("checkInfoService")
public class CheckInfoServiceImpl implements CheckInfoService {

	@Resource
	private CheckInfoDao checkInfoDao;

	@Override
	public List<CheckInfo> findByInOrOut(String inOrOut) {
		return this.checkInfoDao.findByInOrOut(inOrOut);
	}

	@Override
	public List<CheckInfo> find() {
		return null;
	}

	@Override
	public void create(CheckInfo cinfo) {
		this.checkInfoDao.add(cinfo);
	}

	@Override
	public List<CheckInfo> findByAccountIdAndInOrOut(int accountId,
			String inOrOut) {
		return this.checkInfoDao.findByAccountIdAndInOrOut(accountId, inOrOut);
	}

	@Override
	public List<CheckInfo> findByUserId(int userId) {
		return this.checkInfoDao.findByUserId(userId);
	}

}
