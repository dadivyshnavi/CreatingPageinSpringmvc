package com.charvikent.issuetracking.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
			
			String hql ="select * from student where status='1'";
			
			
			 RowMapper<student> rowMapper = new BeanPropertyRowMapper<student>(student.class);
			      return  this.JdbcTemplate.query(hql, rowMapper);
			
			
			
		}

	public boolean deleteStudent(Integer id, String status) {
		
		Boolean delete=false;
		try{

			student design= entityManager.find(student.class ,id);
			   design.setStatus(status);
			   entityManager.merge(design);
			if(!status.equals(design.getStatus()))
			{
				delete=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}

	public List<student> getInActiveList() {
		String hql ="select * from student where status='0'";
		
		
		 RowMapper<student> rowMapper = new BeanPropertyRowMapper<student>(student.class);
		      return  this.JdbcTemplate.query(hql, rowMapper);	
		
	}

	public void updateStudent(student user) 
	{
		student users=getStudentById(user.getId());
		
		users.setFname(user.getFname());
		users.setLname(user.getLname());
		users.setMobile(user.getMobile());
		entityManager.merge(users);
		entityManager.flush();
		
	}

	private student getStudentById(Integer id) {
		return entityManager.find(student.class, id);

		
	}

	@SuppressWarnings("unchecked")
	public student getStudentByObject(student user) {

			String hql ="from student where mobile='"+user.getMobile()+"'";

			Query query =entityManager.createQuery(hql);

			List<student>usersList =query.getResultList();
			if(usersList.isEmpty())
	               return null;
	               else
			return usersList.get(0);
		}	
}
