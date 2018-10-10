package com.charvikent.issuetracking.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.charvikent.issuetracking.config.MailTemplate;
import com.charvikent.issuetracking.config.SendSMS;
import com.charvikent.issuetracking.dao.UserDao;
import com.charvikent.issuetracking.model.User;
import com.charvikent.issuetracking.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
public class EmployeeRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRestController.class);
	@Autowired UserDao userDao;
	@Autowired UserService userService;
	@Autowired
	MailTemplate mailTemplate;
	
	@RequestMapping("/Customer")
	public String showDashboardPage(Model model,HttpServletRequest request) throws JsonProcessingException
	{
		LOGGER.debug("Calling dashBoard at controller");
		return null;	
	}
	@PostMapping(value="/saveRestEmployee", consumes = "application/json", produces = "application/json")
	public String saveEmployee( @RequestBody User user,HttpServletRequest request) throws JSONException 
	{
	LOGGER.debug("Calling saveEmployee at controller");
	JSONObject json =new JSONObject();
	String code ="";
	int id = 0;
	try
	{
		User orgBean= userDao.getUserByObject(user);
		int dummyId =0;
		if(orgBean != null){
			dummyId = orgBean.getId();
		}
		if(user.getId()==null)
		{
			if(dummyId ==0)
			{
				
				user.setStatus("1");
				Random rnd = new Random();
				int n = 100000 + rnd.nextInt(900000);
				String strRandomNumber = String.valueOf(n);
				user.setPassword(strRandomNumber);
				userService.saveUser(user);
				code ="success";
			} else
			{
				code ="exists";	
			}
		}
		else
		{
			id=user.getId();
			if(id == dummyId || orgBean == null)
			{
				
				userDao.updateUser(user);
				code ="updated";	
			} else
			{
				code ="exists";
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
		System.out.println(e);
	}
	json.put("status", code);
	
	return String.valueOf(json);
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/logincredentials", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  checkingLogincredentials( @RequestBody User user) throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling logincrentails at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		JSONObject json =new JSONObject();
		System.out.println("rest call user called at end");
		User userBean =userDao.getUserByObject(user);
		System.out.println("rest call user called at staring"+userBean);
		ObjectMapper objectMapper = new ObjectMapper();
		String userjson = objectMapper.writeValueAsString(userBean);
			if(null != userBean)
			{
				code =userBean.getFirstName()+" "+userBean.getLastName();
				json.put("status", userBean);	
			}
			else
			{
				json.put("status", "NOT_FOUND");
			System.out.println("rest call user called at end");
			}
		return userjson;
	}
	@SuppressWarnings("unused")
	@RequestMapping(value = "/restForgotPassword", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getForgotPassword(@RequestBody User user,HttpServletRequest request) throws IOException, MessagingException 
	{
		LOGGER.debug("Calling  restForgotPassword at controller");
		System.out.println("enter to restForgotPassword");
		String code =null;
		JSONObject json =new JSONObject();
		String emailid=user.getEmailId();
		String name=user.getFirstName();
		String password=user.getPassword();
		User custbean2 =userDao.checkUserExistOrNotbyEmailId(emailid);
		mailTemplate.resetPassword(user);
		if (custbean2 != null) {
			code="sucess";
			//code=HttpStatus.OK;
		}
		else {
			//code=HttpStatus.NOT_FOUND;
			code="failed";
		}
	json.put("password", custbean2.getPassword());
		return String.valueOf(json);
		/*ResponseEntity<String> response = new ResponseEntity<String>(custbean2.getPassword(),code);
		return response;*/
	}
	@RequestMapping(value="/employeelist",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String  getServiceRequestList()
	{
		LOGGER.debug("Calling employeelist at controller");
		JSONObject json =new JSONObject();
		List<User> listOrderBeans = null;
		try 
		{
			listOrderBeans = userDao.getAllUsers();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				json.put("employeelist", listOrderBeans);
			} else 
			{
				json.put("employeelist", "NOT_FOUND");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}
	
	
	@PostMapping(value="/deactiveOractiveEmployee", consumes = "application/json", produces = "application/json")
    public String deleteEmployee( @RequestBody User user,HttpServletRequest request) throws JSONException       
	{
		LOGGER.debug("Calling deactiveOractiveEmployee at controller");
		//int result = categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		userDao.deactiveEmployee(user.getStatus(),user.getId());
		JSONObject jsonObj = new JSONObject();
		if(!user.getStatus().equals("0"))
		{
			jsonObj .put("status", "Not Deactivated");
		}
		else
			jsonObj .put("status", "Deactivated");	
            return String.valueOf(jsonObj);
	}
	
	@RequestMapping(value = "/inActiveUsers",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAllActiveOrInactiveTasks(User  objdept,HttpServletRequest request) throws JsonProcessingException,JSONException
	{
		LOGGER.debug("Calling inActiveUsers at controller");
		String code =null;
		List<User> listOrderBeans  = userDao.getInActiveList();
		JSONObject jsonObj = new JSONObject();
			if (null != listOrderBeans) 
				jsonObj.put("inactiveuserslist", listOrderBeans);
			else 
				jsonObj.put("inactiveuserslist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);	
		return String.valueOf(jsonObj);
	}
	
	@RequestMapping(value="/restChangePassword", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getPasswordInfo( @RequestBody User user) throws JsonProcessingException, JSONException
	{
		LOGGER.debug("Calling restChangePassword at controller");
		int result = userDao.getPassword(user);
		JSONObject json =new JSONObject();
			if(result==1)
			{
				json.put("password", "Updated");
			}
			else
				json.put("password", "Not Updated");
		return String.valueOf(json);
	}
	
}
