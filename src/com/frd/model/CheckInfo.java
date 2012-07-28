package com.frd.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="checkinfo")
public class CheckInfo {

	@Column(name="accountid")
	private int accountId;
	
	@Column(name="createtime")
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="in_out")
	private String inOrOut;
	
	@Column(name="uid")
	private int userId;

	public int getAccountId() {
		return accountId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public int getId() {
		return id;
	}

	public String getInOrOut() {
		return inOrOut;
	}

	@ManyToOne(cascade=CascadeType.REMOVE, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="id")
	public int getUserId() {
		return userId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
