package com.charvikent.issuetracking.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.charvikent.issuetracking.model.RequestLeave;
import com.charvikent.issuetracking.model.User;
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
	public List<Map<String, Object>> getUnreadMailDetailsById(String empId,String rowid)
	{
		String hql="select * from request_leave where emp_id="+empId+" and status = 1 and id="+rowid+"";
		List<Map<String, Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;	
	}
	
	public void updateMailStatusNotifiations(String status,String rowid)
	{
		String hql="update request_leave set status='0' where status='1'and id="+rowid+"";
		jdbcTemplate.execute(hql);
	}
	
	public List<Map<String, Object>> getReadMailDetailsById(String empId,String rowid)
	{
		String hql="select * from request_leave where emp_id="+empId+" and status = '0' and id="+rowid+"";
		List<Map<String, Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;	
	}
	
	
}


