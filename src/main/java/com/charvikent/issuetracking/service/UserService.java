package com.charvikent.issuetracking.service;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.charvikent.issuetracking.config.MailTemplate;
import com.charvikent.issuetracking.config.SendSMS;
import com.charvikent.issuetracking.dao.DashBoardDao;
import com.charvikent.issuetracking.dao.UserDao;
import com.charvikent.issuetracking.dao.passwordDao;
import com.charvikent.issuetracking.model.EmployeeAction;
import com.charvikent.issuetracking.model.PasswordDetails;
import com.charvikent.issuetracking.model.RequestLeave;
import com.charvikent.issuetracking.model.User;
import com.charvikent.issuetracking.model.Role;
import com.charvikent.issuetracking.model.shift;

@Service
@Transactional
public class UserService {

	private final static Logger logger = Logger.getLogger(AdminService.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private passwordDao passworddao;
	@Autowired
	private SendSMS smsTemplate;
	@Autowired MailTemplate mailTemplate;
	@Autowired DashBoardDao dashBoardDao;
    //SendSMS smstemplate =new SendSMS();

	public void saveUser(User user) throws IOException
		{
		/*if(user.getEmpId() ==null)
		{
			User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			user.setEmpId(objuserBean.getEmpId());
			
		}*/
		
		String msg =user.getFirstName()+" "+user.getLastName()+",  Successfully registered with CharvikentEmployee. \n You can login using \n Username:  "+user.getMobileNo()+"\n password: "+user.getPassword();
		String mbnum=user.getMobileNo();
		userDao.saveUser(user);
		logger.info("Sending message.......");
		smsTemplate.sendSMS(msg,mbnum);	
	}
	
	public List<User> getAllUsers()
	{
    User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		List<User> usersListForAdmin =new ArrayList<>();
		List<User> usersListForMaster= userDao.getAllUsers();
		
		if(authorities.contains(new SimpleGrantedAuthority("ROLE_MASTERADMIN")))
			   return usersListForMaster;
		 else 
		 {
			 
			 for(User entry :usersListForMaster)
			 {  
				 if(entry.getEmpId().equals(objuserBean.getEmpId()))
				 {
					 if(entry.getId()!=(objuserBean.getId()))
					 usersListForAdmin.add(entry);
				 }
			 }
			 return usersListForAdmin;
		 }

	}


	

	public Map<Integer, String> getRoles()
	{
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<Role> rolesList= userDao.getRoles();
		for(Role bean: rolesList){
			rolesMap.put(bean.getId(), bean.getDesigname());
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;


	}
	public Map<Integer, String> getShifts()
	{
		Map<Integer, String> shiftMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<shift> shiftsList= userDao.getShifts();
		for(shift bean: shiftsList){
			shiftMap.put(bean.getId(), bean.getShift());
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
		return shiftMap;


	}

		public User  findWithName(String username, String lpassword)
	    {

		 User userdao=null;

		try {
			userdao= userDao.findWithName(username, lpassword);
		} catch (Exception e) {
			System.out.println("error occured service");


			e.printStackTrace();
		}

				return userdao;

	}

public boolean deleteUser(Integer id,String status) {

		return userDao.deleteUser(id,status);

	}

	public User getUserById(Integer id) {

		User obj=userDao.getUserById(id);
		return obj;
	}

	public void updateUser(User user) {

		userDao.updateUser(user);

	}

	public void updatePassword(User user) {

		userDao.updatePassword(user);

	}

	
	public void setLoginRecord(Integer id,String str) {

		userDao.setLoginRecord(id,str);
	}

	/*public boolean checkUserExist(String username) {

		List<User> usersList= userDao.getAllUsers();

		for(User bean: usersList){
			  if(username.equalsIgnoreCase(bean.getMobileNo()));
			  {

				  return true;
		       }
	}
		return false;
	}*/

	public User getUserByObject(User user) {
	return userDao.getUserByObject(user);
	}

	public List<User> getInActiveList() {

		return userDao.getInActiveList();
	}

	public User checkUserExistOrNotbyEmailId(String emailid) {
		
		return userDao.checkUserExistOrNotbyEmailId(emailid);
	}

	public User checkUserExistOrNotbyMobile(String mobileNo) {
		return userDao.checkUserExistOrNotbyMobile(mobileNo);
	}

	public User checkUserExistOrNotbyMobileOnEdit(String mobileNo, String editFieldsId) {
		return  userDao.checkUserExistOrNotbyMobileOnEdit(editFieldsId,mobileNo);
	}
	public User checkUserExistOrNotbyEmailOnEdit(String emailId, String editFieldsId) {
		return  userDao.checkUserExistOrNotbyEmailOnEdit(editFieldsId,emailId);
	}
	public User checkUserExistOrNotbyAadhar(String aadharNo) {
		return userDao.checkUserExistOrNotbyAadhar(aadharNo);
	}

	public User checkUserExistOrNotbyAadharOnEdit(String aadharNo, String editFieldsId) {
		return  userDao.checkUserExistOrNotbyAadharOnEdit(editFieldsId,aadharNo);
	}
	
	
	
	
	public User getCurrentUser() {
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return objuserBean;
		
	}

	
	public User getUserDesignationById(Integer id) 
	{
		User obj=userDao.getUserDesignationById(id);
		return obj;
	}
	
	
/*public List<Map<String, Object>> getMailsStatus(){
	return dashBoardDao.showMailsNotification();

}*/
}
