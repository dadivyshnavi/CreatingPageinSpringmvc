package com.charvikent.issuetracking.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.issuetracking.model.customer;


@Repository
@Transactional
public class customerDao {

	@Autowired
	JdbcTemplate JdbcTemplate;
	
	 @Autowired
	    private EntityManager entityManager;
	 
	 public void addCustomer(customer customer) {
		 
		 entityManager.persist(customer);
		 
		 }

	public List<customer> getAllCustomers() {
	   String hql="select c.id,c.city as cityid ,t.city as city,c.dob,c.lname,c.files,c.fname,c.lname,c.mobile,c.status from customer c,city t where c.city=t.id and c.status='1'";
	   //String hql ="select * from customer";
	   
	   
	   System.out.println(hql);
	   RowMapper<customer> rowMapper = new BeanPropertyRowMapper<customer>(customer.class);
		  return  this.JdbcTemplate.query(hql, rowMapper);
	}
	@SuppressWarnings("unchecked")
	public customer getCustomerByObject(customer customer) {
		// TODO Auto-generated method stub
		String hql ="from customer where mobile='"+customer.getMobile()+"'";

		Query query =entityManager.createQuery(hql);

		List<customer>customerList =query.getResultList();
		if(customerList.isEmpty())
               return null;
               else
		return customerList.get(0);
	}
	
	//-------------Coding for delete record from List----------- 
		/* public boolean deleteStudent(Integer id, String status) {
			
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
		}*/
		//-------------Coding for delete record from List----------- 
		
		 
		 //-----------Codign for View deleted records List---------
		 
		/* public List<student> getInActiveList() {
			String hql ="select * from student where status='0'";
			
			
			 RowMapper<student> rowMapper = new BeanPropertyRowMapper<student>(student.class);
			      return  this.JdbcTemplate.query(hql, rowMapper);	
			
		}*/
		 
		//-----------Coding for View deleted records List---------
		
		 
		 //-----------coding for Update Record in the list by ID--------
		public void updateCustomer(customer customer) 
		{
			customer users=getCustomerById(customer.getId());
			
			users.setFname(customer.getFname());
			users.setLname(customer.getLname());
			users.setMobile(customer.getMobile());
			users.setCity(customer.getCity());
			/*users.setCourseid(user.getCourseid());*/
			users.setDob(customer.getDob());
			users.setFiles(customer.getFiles());
			entityManager.merge(users);
			entityManager.flush();
			
		}

		private customer getCustomerById(Integer id) {
			
			return entityManager.find(customer.class, id);

			}
		 //-----------coding for Update Record in the list by ID--------
		
		
		public List<customer> getAllCity()
		{
			
			String hql=" select * from city";
			RowMapper<customer> rowMapper = new BeanPropertyRowMapper<customer>(customer.class);
		    return  this.JdbcTemplate.query(hql, rowMapper);	
			
			}
		
		 public Map<Integer,String> getCityMap(){
			 
			 List<customer> list=getAllCity();
			 Map<Integer,String> map=new HashMap<Integer,String>();
			 for(customer  entry:list) {
				 
				 map.put(entry.getId(),entry.getCity()); 
			 }
			return map;
		}

		public boolean deleteCustomer(Integer id, String status) {
			Boolean delete=false;
			try{

				customer design= entityManager.find(customer.class ,id);
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
		 
			
}
