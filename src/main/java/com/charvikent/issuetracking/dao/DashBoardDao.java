package com.charvikent.issuetracking.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.charvikent.issuetracking.model.User;
import com.charvikent.issuetracking.model.UserLogs;

@Repository
public class DashBoardDao 
{
	@PersistenceContext private EntityManager em;
	@Autowired UserDao userDao;
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
}


