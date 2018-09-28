package com.charvikent.issuetracking.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="EmployeeAction")
public class EmployeeAction 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;	
	@CreationTimestamp 
	private Date actionTime;
	private String empId;
	private String actionId;
	private String shiftId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getActionTime() {
		return actionTime;
	}
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getShiftId() {
		return shiftId;
	}
	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}
	@Override
	public String toString() {
		return "EmployeeAction [id=" + id + ", actionTime=" + actionTime + ", empId=" + empId + ", actionId=" + actionId
				+ ", shiftId=" + shiftId + "]";
	}
	
	
	
}
