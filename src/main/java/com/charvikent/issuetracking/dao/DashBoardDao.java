package com.charvikent.issuetracking.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.charvikent.issuetracking.model.RequestLeave;
import com.charvikent.issuetracking.model.User;
import com.charvikent.issuetracking.model.UserLogs;

@Repository
public class DashBoardDao 
{	
	@Autowired JdbcTemplate jdbcTemplate;
	@PersistenceContext private EntityManager em;
	@Autowired UserDao userDao;
	/**
	 * @return
	 */
	public Object getLastloginTime() 
	{
	User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
	
	String hql ="select logintime from userslogs where sessionname='login' and userid=:uid order by logintime desc limit 1,1 ";
	@SuppressWarnings("unchecked")
	List<UserLogs> rows = (List<UserLogs>) em.createNativeQuery(hql).setParameter("uid",id).getResultList();
	 if(rows.size()>0)
	 {
		 return rows.get(0);
	 }
	 else
		 
	return "";
	}  
	
	@SuppressWarnings("unused")
	public List<RequestLeave> showMailsNotification()
	{
		//User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String hql="select r.emp_id,r.subject from request_leave r";
		
		System.out.println(hql);
		
		/*List<RequestLeave>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;*/
		RowMapper<RequestLeave> rowMapper = new BeanPropertyRowMapper<RequestLeave>(RequestLeave.class);
		return  this.jdbcTemplate.query(hql, rowMapper);
	}
	
	
}


