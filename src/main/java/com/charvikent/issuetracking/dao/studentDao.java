package com.charvikent.issuetracking.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.issuetracking.model.Course;
import com.charvikent.issuetracking.model.student;

@Repository
@Transactional
public class studentDao {
	
	@Autowired
	JdbcTemplate JdbcTemplate;
	
	 @Autowired
	    private EntityManager entityManager;
	
	
	 //----------Coding for Add Student in list----------
	 
	 public void addStudent(student student) {
		 
		 
		 
		 entityManager.persist(student);
		 
		 }
	 
	 
	 //----------Coding for Add Student in list----------
	 
	 
	 
	 //--------------Coding for View List in Page------------
	 public  List<student> getAllStudent() {
			
			String hql ="select s.id,s.fname,s.lname,s.status,s.mobile,s.course as courseid ,c.course as course,s.dob,s.files from student s,course c where s.course=c.id and s.status='1'";
			
			
			 RowMapper<student> rowMapper = new BeanPropertyRowMapper<student>(student.class);
			 
			      return  this.JdbcTemplate.query(hql, rowMapper);
			
			
	 	}
	//--------------Coding for View List in Page------------
	 
	
	//-------------Coding for delete record from List----------- 
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
	//-------------Coding for delete record from List----------- 
	
	 
	 //-----------Codign for View deleted records List---------
	 
	 public List<student> getInActiveList() {
		String hql ="select * from student where status='0'";
		
		
		 RowMapper<student> rowMapper = new BeanPropertyRowMapper<student>(student.class);
		      return  this.JdbcTemplate.query(hql, rowMapper);	
		
	}
	 
	//-----------Coding for View deleted records List---------
	
	 
	 //-----------coding for Update Record in the list by ID--------
	public void updateStudent(student user) 
	{
		student users=getStudentById(user.getId());
		
		users.setFname(user.getFname());
		users.setLname(user.getLname());
		users.setMobile(user.getMobile());
		users.setCourse(user.getCourse());
		/*users.setCourseid(user.getCourseid());*/
		users.setDob(user.getDob());
		users.setFiles(user.getFiles());
		entityManager.merge(users);
		entityManager.flush();
		
	}

	private student getStudentById(Integer id) {
		
		return entityManager.find(student.class, id);

		}
	 //-----------coding for Update Record in the list by ID--------
	
	
	
	//---------------- Coding for Duplicate Checking on Mobile Number----------
	
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
	
	//---------------- Coding for Duplicate Checking on Mobile Number----------
	
	
	
	//----------------Coding to View list in Drop Down-----------------
	
	public List<student> getAllCourse()
	{
		
		String hql=" select * from course";
		RowMapper<student> rowMapper = new BeanPropertyRowMapper<student>(student.class);
	    return  this.JdbcTemplate.query(hql, rowMapper);	
		
		}
	
	 public Map<Integer,String> getCourseMap(){
		 
		 List<student> list=getAllCourse();
		 Map<Integer,String> map=new HashMap<Integer,String>();
		 for(student  entry:list) {
			 
			 map.put(entry.getId(),entry.getCourse()); 
		 }
		return map;
	}
	 
	 
	//----------------Coding to View list in Drop Down-----------------
	 
	 
}
