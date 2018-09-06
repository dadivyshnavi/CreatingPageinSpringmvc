package com.charvikent.issuetracking.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.charvikent.issuetracking.model.PasswordDetails;

@Repository
@Transactional
public class passwordDao {
	@PersistenceContext
    private EntityManager entityManager;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	public void savePWDdetails(PasswordDetails pwdDetails) 
	{
		entityManager.persist(pwdDetails);
	}
	
	
}
