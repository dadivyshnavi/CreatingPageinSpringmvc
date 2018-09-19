package com.charvikent.issuetracking.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
/*
@Entity
@Table(name = "pwdDetails")*/
public class PasswordDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	@CreationTimestamp
	private Date createdTime;
	private String PWDstatus;
	private String mobileNo;
	private String PWDnumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getPWDstatus() {
		return PWDstatus;
	}

	public void setPWDstatus(String pWDstatus) {
		PWDstatus = pWDstatus;
	}

	public String getPWDnumber() {
		return PWDnumber;
	}

	public void setPWDnumber(String pWDnumber) {
		PWDnumber = pWDnumber;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "PasswordDetails [id=" + id + ", createdTime=" + createdTime + ", PWDstatus=" + PWDstatus + ", mobileNo="
				+ mobileNo + ", PWDnumber=" + PWDnumber + "]";
	}

	
	
}
