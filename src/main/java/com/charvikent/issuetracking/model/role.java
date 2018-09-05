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

@Entity
@Table(name = "roles")
public class role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String designame;
	@Column
	private String status;
	
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

	public String getDesigname() {
		return designame;
	}

	public void setDesigname(String designame) {
		this.designame = designame;
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

	@Override
	public String toString() {
		return "role [id=" + id + ", designame=" + designame + ", status=" + status + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + "]";
	}
	

}
