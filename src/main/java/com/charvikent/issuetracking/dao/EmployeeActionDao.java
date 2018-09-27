package com.charvikent.issuetracking.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.charvikent.issuetracking.model.EmployeeAction;

@Repository
@Transactional
public class EmployeeActionDao {
	
	@PersistenceContext
	private EntityManager em;

	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	public void saveEmployeeAction(EmployeeAction employeeaction)
	{
		
		em.persist(employeeaction);
		
	}

}
