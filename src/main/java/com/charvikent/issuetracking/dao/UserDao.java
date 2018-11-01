package com.charvikent.issuetracking.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.charvikent.issuetracking.config.KptsUtil;
import com.charvikent.issuetracking.model.User;
import com.charvikent.issuetracking.model.UserLogs;
import com.charvikent.issuetracking.model.EmployeeAction;
import com.charvikent.issuetracking.model.Role;
import com.charvikent.issuetracking.model.shift;

@Repository
@Transactional
public class UserDao {

	@PersistenceContext
	private EntityManager em;

@Autowired JdbcTemplate jdbcTemplate;

@Autowired KptsUtil kptsUtil;

	/**
	 * @param user
	 * This method is used to save user details in database. 
	 */
	public void saveUser(User user) {
	  
	   /** for random number generation of employeeId 
	   * String userid =kptsUtil.randNum();*/
	  user.setStatus("1");
	  //user.setEmpId(userid);
		em.persist(user);

	}

	/**
	 * @return
	 * This method is used to display all user details 
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers()
	{
		String hql ="select e.id,e.status,e.emp_id,e.first_name,e.last_name,e.email_id,e.mobile_no,e.aadhar_no,e.emergency_no,e.dob,e.doj,e.role_id,e.shift_id,r.designame as role,s.shift from employee e,roles r,shifts s where e.role_id=r.id and e.shift_id=s.id and e.status='1'";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		 
	      return  this.jdbcTemplate.query(hql, rowMapper);
	}

	/**
	 * @return
	 * This method is used to get roles from database
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getRoles()
	{

		String hql ="select r.id,r.designame from roles r";
		RowMapper<Role> rowMapper = new BeanPropertyRowMapper<Role>(Role.class);
		 
	      return  this.jdbcTemplate.query(hql, rowMapper);

	}
	/**
	 * @return
	 * this method is used to get shift from database 
	 */
	@SuppressWarnings("unchecked")
	public List<shift> getShifts()
	{

		String hql ="select s.id,s.shift from shifts s";
		RowMapper<shift> rowMapper = new BeanPropertyRowMapper<shift>(shift.class);
		 
	      return  this.jdbcTemplate.query(hql, rowMapper);

	}


	/**
	 * @param username
	 * @param lpassword
	 * @return
	 * This method is used to check the user authentication by user name and password 
	 */
	public User findWithName(String username,String lpassword) {
		User userbean =null;
		try {
			userbean=  (User) em.createQuery(" select u FROM User u where  enabled='1' and u.username =:custName AND u.password =:custPass ")
					.setParameter("custName", username)
					.setParameter("custPass", lpassword)
					.getSingleResult();

		} catch (Exception e) {
			return userbean;
			//e.printStackTrace();
		}
		return userbean;


	}


	/**
	 * @param id
	 * @return
	 * this method is used to get user by ID
	 */
	public User getUserById(Integer id) {

		return em.find(User.class, id);
	}
	/**
	 * @param id
	 * @param status
	 * @return
	 * This method id used to delete the user from list
	 */
	public boolean deleteUser(Integer id, String status) {
			
			Boolean delete=false;
			try{

				User design= em.find(User.class ,id);
				   design.setStatus(status);
				   em.merge(design);
				if(!status.equals(design.getStatus()))
				{
					delete=true;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return delete;
		}


	/**
	 * @param user
	 * This method is used to update the user details 
	 */
	public void updateUser(User user) {
		User users=getUserById(user.getId());
		users.setEmpId(user.getEmpId());
		users.setFirstName(user.getFirstName());
		users.setLastName(user.getLastName());
		users.setEmailId(user.getEmailId());
		users.setMobileNo(user.getMobileNo());
		users.setAadharNo(user.getAadharNo());
		users.setEmergencyNo(user.getEmergencyNo());
		users.setDob(user.getDob());
		users.setDoj(user.getDoj());
		users.setRoleId(user.getRoleId());
		users.setShiftId(user.getShiftId());
		users.setPassword(user.getPassword());
		em.merge(users);
		em.flush();
	}

	public void updatePassword(User user) {
		
		em.merge(user);

		em.flush();


	}

	@SuppressWarnings("unchecked")
	public List<User> getUserNames()
	{

		return em.createQuery("SELECT user FROM User user").getResultList();

	}


	public User find(Integer id) {

		return em.find(User.class, id);

	}

	public void setLoginRecord(Integer id,String str) {
		UserLogs logs =new UserLogs();
		logs.setUserid(String.valueOf(id));
		logs.setSessionname(str);
		em.merge(logs);

	}

	
	

	/*@SuppressWarnings("unchecked")
	public User findByUserName(String username)
	{
		User user= (User) em.createQuery("select user from User user where username=:Custname").setParameter("Custname", username).getSingleResult();
		System.out.println(user);
		
		
		String hql ="From User where username= '"+username+"' ";
		
		Query query = em.createQuery(hql);
		
		
		List<User> list =query.getResultList();
		
		       if(list.size() >0)
		       {
		    	   return list.get(0);
		       }
		       else
		       return null;
		
	}*/
	@SuppressWarnings("unchecked")
	public User findByUserName(String mobileno)
	{
		/*User user= (User) em.createQuery("select user from User user where username=:Custname").setParameter("Custname", mobileno).getSingleResult();
		System.out.println(user);*/
		
		
		String hql ="From User where mobileNo= '"+mobileno+"' ";
		
		Query query = em.createQuery(hql);
		
		
		List<User> list =query.getResultList();
		
		       if(list.size() >0)
		       {
		    	   return list.get(0);
		       }
		       else
		       return null;
		
		
		
	}
	public List<String> findRoleByUserName(String Username)
	{
		
		String hql ="select mr.desigrole from User as u,Role as r,MultiRoles as mr where mobile_no='"+Username+"' and u.roleId=r.id and r.id=mr.id ";
		
		//Session session=this.sessionFactory.getCurrentSession();
		 
		 Query query=em.createQuery(hql);
		 List<String> results = query.getResultList();
		return results;
		
		//List<String> list= em.createNativeQuery("SELECT d.name FROM  kpusers u,kpdesignation d,kpmultiroles m  where u.designation=d.id  and k.username=:Custname").setParameter("Custname", Username).getResultList();
		//List<String> list= em.createNativeQuery("select m.desigrole from kpusers u,kpdesignation d,kpmultiroles m  where u.designation=d.id and m.designationid=u.designation and u.username =:Custname").setParameter("Custname", Username).getResultList();
		 // List<String> list= em.createNativeQuery("select m.desigrole from kpusers u,kpmultiroles m  where  m.designationid=u.designation and u.username =:Custname").setParameter("Custname", Username).getResultList();

		//List<String> list= em.createNativeQuery("select m.desigrole from kpusers u,kpmultiroles m where  m.designationid=u.role_id and u.username =:Custname").setParameter("Custname", Username).getResultList();

		  
		  
		  
		/*  
		System.out.println(list);
		return list;
		*/

	}

	@SuppressWarnings("unchecked")
	public User getUserByObject(User user) {

		String hql ="from User where mobileNo='"+user.getMobileNo()+"'";

		Query query =em.createQuery(hql);
		//query.setParameter("n", user.getMobileNo());

		List<User> usersList =query.getResultList();
		if(usersList.isEmpty())
               return null;
               else
		return usersList.get(0);
	}

	public List<User> getInActiveList()
	{
		String hql ="select e.id,e.status,e.emp_id,e.first_name,e.last_name,e.email_id,e.mobile_no,e.aadhar_no,e.emergency_no,e.dob,e.doj,e.role_id,e.shift_id,r.designame as role,s.shift from employee e,roles r,shifts s where e.role_id=r.id and e.shift_id=s.id and e.status='0'";
		
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		 
	      return  this.jdbcTemplate.query(hql, rowMapper);
	}

	@SuppressWarnings("unchecked")
	public User checkUserExistOrNotbyEmailId(String emailid) {
		
		String hql ="from User where emailId='"+emailid+"'";
		
		
		List<User> custlist =	em.createQuery(hql).getResultList();
		    
		if(custlist.size()>0)
		return custlist.get(0);
		else
		return null;
			
		}

	@SuppressWarnings("unchecked")
	public User checkUserExistOrNotbyMobile(String mobileNo) {
		String hql ="from User where mobileNo='"+mobileNo+"'";
		Query query =em.createQuery(hql);
		List<User> usersList =query.getResultList();
		if(usersList.isEmpty())
               return null;
               else
		return usersList.get(0);
	}

	@SuppressWarnings("unchecked")
	public User checkUserExistOrNotbyMobileOnEdit(String editFieldsId, String mobileNo) {
		String hql ="from User where id <>'"+editFieldsId+"' and  mobileNo='"+mobileNo+"'";
		Query query =em.createQuery(hql);
		List<User> usersList =query.getResultList();
		if(usersList.isEmpty())
               return null;
               else
		return usersList.get(0);

	}
	@SuppressWarnings("unchecked")
	public User checkUserExistOrNotbyEmailOnEdit(String editFieldsId, String emailid) {
		String hql ="from User where id <>'"+editFieldsId+"' and  emailId='"+emailid+"'";
		Query query =em.createQuery(hql);
		List<User> usersList =query.getResultList();
		if(usersList.isEmpty())
               return null;
               else
		return usersList.get(0);

	}
	
	public void deactiveEmployee(String status,Integer id) 
	{
		String sql="update employee set status='"+status+"'where id='"+id+"'";
		jdbcTemplate.execute(sql);
		
	}

	public int getPassword(User user) {

		String sql =" update employee set password ='"+user.getPassword()+"' where id='"+user.getId()+"' order by updated_time desc";	
		int  result = jdbcTemplate.update(sql);
		return result; 
		
	}
	
	@SuppressWarnings("unchecked")
	public User getUserDesignationById(Integer id) 
	{
		String hql ="select  e.mobile_no, r.designame from roles r,employee e where e.role_id=r.id and e.id=:id ";
		User users =new User();
		try{
			List<Object[]> rows = em.createNativeQuery(hql).setParameter("id", id).getResultList();	
		for (Object[] row : rows) {
			users.setMobileNo((String) row[0]);
			users.setRole((String) row[1]);		
		}
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	return  users;

	}
	
	
	
	@SuppressWarnings("unchecked")
	public User checkUserExistOrNotbyAadhar(String aadharNo) {
		String hql ="from User where aadharNo='"+aadharNo+"'";
		Query query =em.createQuery(hql);
		List<User> usersList =query.getResultList();
		if(usersList.isEmpty())
               return null;
               else
		return usersList.get(0);
	}

	@SuppressWarnings("unchecked")
	public User checkUserExistOrNotbyAadharOnEdit(String editFieldsId, String aadharNo) {
		String hql ="from User where id <>'"+editFieldsId+"' and  aadharNo='"+aadharNo+"'";
		Query query =em.createQuery(hql);
		List<User> usersList =query.getResultList();
		if(usersList.isEmpty())
               return null;
               else
		return usersList.get(0);

	}
	
	
	
	
	
	
}

	
	

	

	

	
 

