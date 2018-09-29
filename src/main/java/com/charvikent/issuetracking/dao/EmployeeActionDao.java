package com.charvikent.issuetracking.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.charvikent.issuetracking.model.EmployeeAction;
import com.charvikent.issuetracking.model.User;

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

	public int getTimeDiffereneOnCheckOut(EmployeeAction employeeaction) {
		
		  String hql="select TIMESTAMPDIFF(hour,  action_time, now()) as diff from employee_action where action_id='1' and emp_id='"+employeeaction.getEmpId()+"' order by action_time desc limit 1";
		
		
		 
		 //List<Map<String, Object>> diffTime = jdbcTemplate.queryForList(hql);
		 
		 
		 int count = jdbcTemplate.queryForObject(hql, Integer.class);
               
		 
		return count;
			
		}
	
	
}
