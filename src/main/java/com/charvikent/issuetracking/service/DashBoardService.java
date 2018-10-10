package com.charvikent.issuetracking.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.charvikent.issuetracking.dao.DashBoardDao;
import com.charvikent.issuetracking.dao.UserDao;
@Service
public class DashBoardService {
	@Autowired DashBoardDao dashBoardDao;
	@Autowired UserDao userDao;

public Object getLastLoginTime() 
{
	return dashBoardDao.getLastloginTime();
}
	

}
