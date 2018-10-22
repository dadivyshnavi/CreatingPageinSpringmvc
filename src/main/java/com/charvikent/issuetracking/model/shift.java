package com.charvikent.issuetracking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Vyshnavi
 *In the shift class,here we create every field with setters&getters and override toString()
 */
@Entity
@Table(name = "shifts")
public class shift {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String shift;
	
	@Column
	private String Status;
	@CreationTimestamp
	protected Date createdTime ;

	@UpdateTimestamp
	protected Date updatedTime ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
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

	@Override
	public String toString() {
		return "shift [id=" + id + ", shift=" + shift + ", Status=" + Status + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + "]";
	}
	
	
	
	
	
	
}
