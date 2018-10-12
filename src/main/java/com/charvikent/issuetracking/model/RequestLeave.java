package com.charvikent.issuetracking.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


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
	private String description;
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
	@Override
	public String toString() {
		return "RequestLeave [id=" + id + ", empId=" + empId + ", emailId=" + emailId + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", description=" + description + "]";
	}
	
	
	
}
