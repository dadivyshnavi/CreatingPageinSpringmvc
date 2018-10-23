package com.charvikent.issuetracking.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.charvikent.issuetracking.model.RequestLeave;
@Repository
@Transactional
public class NotificationDao {
	@Autowired JdbcTemplate jdbcTemplate;
	@PersistenceContext private EntityManager em;
	
	public List<RequestLeave> getAllMails()
	{
		String hql ="select * from request_leave order by created_time desc ";
		System.out.println(hql);
		RowMapper<RequestLeave> rowMapper = new BeanPropertyRowMapper<RequestLeave>(RequestLeave.class);
		 
	      return  this.jdbcTemplate.query(hql, rowMapper);
	}
}
