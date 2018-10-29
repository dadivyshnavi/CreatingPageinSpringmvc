package com.charvikent.issuetracking.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


/**
 * @author Vyshnavi
 *In the RequestLeave class , We can create every field with setters&getters and override toString() fot EmployeeAction bean
 */
@Entity
@Table(name="RequestLeave")
public class RequestLeave {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;	
	
	private String empId;
	private String emailId;
	private String fromDate;
	private String toDate;
	private String subject;
	private String description;
	
	@CreationTimestamp
	private Date createdTime;
	
	
	private int status;
	
	
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "RequestLeave [id=" + id + ", empId=" + empId + ", emailId=" + emailId + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", subject=" + subject + ", description=" + description + ", createdTime="
				+ createdTime + ", status=" + status + "]";
	}
	
	
	
	
}
