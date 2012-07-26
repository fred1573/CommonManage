package com.frd.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.frd.model.CheckInfo;

@Component("checkInfoDao")
public class CheckInfoDaoImpl implements CheckInfoDao {

	@Resource
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CheckInfo> findByInOrOut(String inOrOut) {
		return this.hibernateTemplate.find("from CheckInfo where inOrOut=? order by accountId", inOrOut);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckInfo> find() {
		return this.hibernateTemplate.find("from CheckInfo order by accountId");
	}

	@Override
	public void add(CheckInfo cinfo) {
		this.hibernateTemplate.save(cinfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckInfo> findByAccountIdAndInOrOut(int accountId,
			String inOrOut) {
		return this.hibernateTemplate.find("from CheckInfo where accountId=? and in_out=?", accountId, inOrOut);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckInfo> findByUserId(int userId) {
		return this.hibernateTemplate.find("from CheckInfo where userId=?", userId);
	}

}
