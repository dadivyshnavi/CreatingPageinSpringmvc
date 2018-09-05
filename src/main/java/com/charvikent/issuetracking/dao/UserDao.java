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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.charvikent.issuetracking.config.KptsUtil;
import com.charvikent.issuetracking.model.User;
import com.charvikent.issuetracking.model.UserLogs;
import com.charvikent.issuetracking.model.role;
import com.charvikent.issuetracking.model.shift;

@Repository

public class UserDao {

	@PersistenceContext
	private EntityManager em;

@Autowired JdbcTemplate jdbcTemplate;

@Autowired KptsUtil kptsUtil;

	public void saveUser(User user) {
	  String userid =kptsUtil.randNum();
	  user.setStatus("1");
	  user.setEmpId(userid);
		em.persist(user);

	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers()
	{
		String hql ="select ku.id,ku.status,ku.emp_id,ku.first_name,ku.last_name,ku.email_id,ku.mobile_no,ku.aadhar_no,ku.emergency_no,ku.dob,ku.doj,ku.role_id,ku.shift_id,r.designame as role,s.shift from kpusers ku,roles r,shifts s where ku.role_id=r.id and ku.shift_id=s.id and ku.status='1'";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		 
	      return  this.jdbcTemplate.query(hql, rowMapper);
	}

	@SuppressWarnings("unchecked")
	public List<role> getRoles()
	{

		String hql ="select r.id,r.designame from roles r";
		RowMapper<role> rowMapper = new BeanPropertyRowMapper<role>(role.class);
		 
	      return  this.jdbcTemplate.query(hql, rowMapper);

	}
	@SuppressWarnings("unchecked")
	public List<shift> getShifts()
	{

		String hql ="select s.id,s.shift from shifts s";
		RowMapper<shift> rowMapper = new BeanPropertyRowMapper<shift>(shift.class);
		 
	      return  this.jdbcTemplate.query(hql, rowMapper);

	}


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


	public User getUserById(Integer id) {

		return em.find(User.class, id);
	}
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

/*	public void deleteUser(Integer id,String enabled) {

		User user=getUserById(id);
		User users= new User();

		//users.setId(id);


		users.setPassword(user.getPassword());
		users.setDepartment(user.getDepartment());
		users.setDesignation(user.getDesignation());
		users.setEmail(user.getEmail());

		if(enabled.equals("Active")) {
			users.setEnabled("0");
			//enabled="0";

		}else {
			users.setEnabled("1");
			//enabled="1";
		}
		//users.setEnabled(user.getEnabled());
		users.setFirstname(user.getFirstname());
		users.setLastname(user.getLastname());
		users.setMobilenumber(user.getMobilenumber());
		users.setUsername(user.getUsername());
		users.setReportto(user.getReportto());

		em.merge(users);

		em.flush();

		String sql="update User u set u.enabled='"+enabled+"' where u.id='"+id+"' ";
		users.setEnabled(user.getEnabled());
		//String sql="update User u set u.enabled=? where u.id=?";

		//Query query = em.createQuery(sql).setParameter("enabled",enabled ).setParameter("id", id);
	Query qry = sessionFactory.;
	qry.setParameter(0,enabled);
	qry.setParameter(1, id);
    int res = qry.executeUpdate();

		//query.executeUpdate();
		String hql = "UPDATE Buchung as b set " +
		          "STORNO = :Storno," +
		          "NAME = :Name " +
		           ......
		          "where ID = :BuchungID";

		Query qr = session.createSQLQuery(hql);

		qr.setParameter("Storno","sto_value");

		qr.setParameter("Name","name_value");

		...

		qr.executeUpdate();

		// int result = em.createQuery("UPDATE User u set u.enabled='\"+enabled+\"' where u.id='\"+id+\"' ").executeUpdate();

	 //User result=

		//em.flush();



	}
*/
	public void updateUser(User user) {
		User users=getUserById(user.getId());
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
		em.merge(users);
		em.flush();
	}

	/*public void updatePassword(User user) {
		
		em.merge(user);

		em.flush();


	}*/

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

	
	

	@SuppressWarnings("unchecked")
	public User findByUserName(String userName)
	{
		/*User user= (User) em.createQuery("select user from User user where username=:Custname").setParameter("Custname", userName).getSingleResult();
		System.out.println(user);*/
		
		
		String hql ="From User where username= '"+userName+"' ";
		
		Query query = em.createQuery(hql);
		
		
		List<User> list =query.getResultList();
		
		       if(list.size() >0)
		       {
		    	   return list.get(0);
		       }
		       else
		       return null;
		
		
		
	}
	@SuppressWarnings("unchecked")
	public List<String> findRoleByUserName(String Username)
	{
		//List<String> list= em.createNativeQuery("SELECT d.name FROM  kpusers u,kpdesignation d,kpmultiroles m  where u.designation=d.id  and k.username=:Custname").setParameter("Custname", Username).getResultList();
		//List<String> list= em.createNativeQuery("select m.desigrole from kpusers u,kpdesignation d,kpmultiroles m  where u.designation=d.id and m.designationid=u.designation and u.username =:Custname").setParameter("Custname", Username).getResultList();
		List<String> list= em.createNativeQuery("select m.desigrole from kpusers u,kpmultiroles m  where  m.designationid=u.designation and u.username =:Custname").setParameter("Custname", Username).getResultList();


		System.out.println(list);
		return list;


	}

	public User getUserByObject(User user) {

		String hql ="from User where username =:n";

		Query query =em.createQuery(hql);
		query.setParameter("n", user.getMobileNo());

		List<User> usersList =query.getResultList();
		if(usersList.isEmpty())
               return null;
               else
		return usersList.get(0);
	}

	public List<User> getInActiveList()
	{
		String hql ="select ku.id,ku.status,ku.emp_id,ku.first_name,ku.last_name,ku.email_id,ku.mobile_no,ku.aadhar_no,ku.emergency_no,ku.dob,ku.doj,ku.role_id,ku.shift_id,r.designame as role,s.shift from kpusers ku,roles r,shifts s where ku.role_id=r.id and ku.shift_id=s.id and ku.status='0'";
		
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		 
	      return  this.jdbcTemplate.query(hql, rowMapper);
	}
	

	

	
	 
}
