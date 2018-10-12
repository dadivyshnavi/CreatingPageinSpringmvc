package com.charvikent.issuetracking.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.charvikent.issuetracking.config.KptsUtil;
import com.charvikent.issuetracking.model.RequestLeave;
@Repository
@Transactional
public class RequestLeaveDao {
	@PersistenceContext
	private EntityManager em;

@Autowired JdbcTemplate jdbcTemplate;

@Autowired KptsUtil kptsUtil;

	public void saveRequestLeave(RequestLeave requestLeave) {
	  
	   /** for random number generation of employeeId 
	   * String userid =kptsUtil.randNum();*/
	  //user.setStatus("1");
	  //user.setEmpId(userid);
		em.persist(requestLeave);

	}
	@SuppressWarnings("unchecked")
	public RequestLeave checkUserExistOrNotbyEmailId(String emailid) {
		
		String hql ="from RequestLeave where emailId='"+emailid+"'";
		
		
		List<RequestLeave> custlist =	em.createQuery(hql).getResultList();
		    
		if(custlist.size()>0)
		return custlist.get(0);
		else
		return null;
			
		}

}
