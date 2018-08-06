package com.charvikent.issuetracking.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.issuetracking.model.student;

@Repository
@Transactional
public class studentDao {
	
	@Autowired
	JdbcTemplate JdbcTemplate;
	
	 @Autowired
	    private EntityManager entityManager;
	
	
	 
	 public void addStudent(student student) {
		 
		 
		 
		 entityManager.persist(student);
		 
		 
	 }
	 
	 public  List<student> getAllStudent() {
			
			String hql ="select * from student";
			
			
			 RowMapper<student> rowMapper = new BeanPropertyRowMapper<student>(student.class);
			      return  this.JdbcTemplate.query(hql, rowMapper);
			
			
			
		}
	 
	 
	 

}
