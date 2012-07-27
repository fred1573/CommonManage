package com.frd.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="user")
public class User {
	
	@Column(name="status")
	private boolean admin;
	
	@Column(name="available")
	private boolean available;
	
	@Transient
	private CheckInfo checkInfo;
	
	@Column(name="email")
	private String email;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;

	@Column(name="registertime")
	private Date registerTime;
	
	
	@OneToMany(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="userId")
	public CheckInfo getCheckInfo() {
		return checkInfo;
	}

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public boolean isAdmin() {
		return admin;
	}

	

	public boolean isAvailable() {
		return available;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setCheckInfo(CheckInfo checkInfo) {
		this.checkInfo = checkInfo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

}
