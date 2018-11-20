package com.charvikent.issuetracking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Vyshnavi
 *In the User class,We create fields with setters and getters,override toString() for User bean
 */
@Entity
@Table(name = "Employee")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String empId;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String emailId;
	private String dob;
	private String doj;
	private String emergencyNo;
	private String aadharNo;
	private String roleId;
	private String shiftId;
	
	@Column
	private String password;
	@Transient
	private String npassword;
	@Transient
	private String cpassword;

	
	private String status;
	
	@CreationTimestamp
	private Date createdTime;
	
	@UpdateTimestamp
	private Date updatedTime;
	
	@Transient 
	private String role;
	
	@Transient 
	private String shift;

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



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getMobileNo() {
		return mobileNo;
	}



	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}



	public String getEmailId() {
		return emailId;
	}



	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}



	public String getDob() {
		return dob;
	}



	public void setDob(String dob) {
		this.dob = dob;
	}



	public String getDoj() {
		return doj;
	}



	public void setDoj(String doj) {
		this.doj = doj;
	}



	public String getEmergencyNo() {
		return emergencyNo;
	}



	public void setEmergencyNo(String emergencyNo) {
		this.emergencyNo = emergencyNo;
	}



	public String getAadharNo() {
		return aadharNo;
	}



	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}



	public String getRoleId() {
		return roleId;
	}



	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}



	public String getShiftId() {
		return shiftId;
	}



	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
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



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public String getShift() {
		return shift;
	}



	public void setShift(String shift) {
		this.shift = shift;
	}



	public String getNpassword() {
		return npassword;
	}



	public void setNpassword(String npassword) {
		this.npassword = npassword;
	}



	public String getCpassword() {
		return cpassword;
	}



	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}



	/**
	 * Constructor created for user bean
	 */
	public User() {
	}

	public User(User user) {
		

	this.id = user.id;
	this.empId = user.empId;
	this.firstName = user.firstName;
	this.lastName = user.lastName;
	this.mobileNo = user.mobileNo;
	this.emailId = user.emailId;
	this.dob = user.dob;
	this.doj = user.doj;
	this.emergencyNo = user.emergencyNo;
	this.aadharNo = user.aadharNo;
	this.roleId = user.roleId;
	this.shiftId = user.shiftId;
	this.password = user.password;
	this.status = user.status;
	this.createdTime = user.createdTime;
	this.updatedTime = user.updatedTime;
	this.role = user.role;
	this.shift = user.shift;
	this.npassword=user.npassword;
	this.cpassword=user.cpassword;
	
		
		
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobileNo=" + mobileNo + ", emailId=" + emailId + ", dob=" + dob + ", doj=" + doj + ", emergencyNo="
				+ emergencyNo + ", aadharNo=" + aadharNo + ", roleId=" + roleId + ", shiftId=" + shiftId + ", password="
				+ password + ", npassword=" + npassword + ", cpassword=" + cpassword + ", status=" + status
				+ ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", role=" + role + ", shift="
				+ shift + "]";
	}



	



	
}
