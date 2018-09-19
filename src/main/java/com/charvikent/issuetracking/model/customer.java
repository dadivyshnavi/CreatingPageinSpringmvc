package com.charvikent.issuetracking.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*import javax.persistence.Transient;*/
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/*@Entity
@Table(name = "customer")*/
public class customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String fname;
	private String lname;
	private String mobile;
	private String city;
	private String dob;
	private String files;
	private String status;
	@Transient
	private String cityid;
	
	@CreationTimestamp
	private Date createdTime;

	@UpdateTimestamp
	private Date updatedTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDob() {
		return dob;
	}
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getCityid() {
		return cityid;
	}

	@Override
	public String toString() {
		return "customer [id=" + id + ", fname=" + fname + ", lname=" + lname + ", mobile=" + mobile + ", city=" + city
				+ ", dob=" + dob + ", files=" + files + ", status=" + status + ", cityid=" + cityid + ", createdTime="
				+ createdTime + ", updatedTime=" + updatedTime + "]";
	}
	

	

	

	
	}
	
	
	
	
	
	

