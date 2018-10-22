package com.charvikent.issuetracking.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Vyshnavi
 * In the role class we can create fields with getters&setters,override toString()
 */
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String designame;
	@Column
	private String status;
	


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

	@Override
	public String toString() {
		return "role [id=" + id + ", designame=" + designame + ", status=" + status + "]";
	}

	


}
