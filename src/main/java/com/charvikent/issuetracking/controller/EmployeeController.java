package com.charvikent.issuetracking.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charvikent.issuetracking.config.MailTemplate;
import com.charvikent.issuetracking.config.SendSMS;
import com.charvikent.issuetracking.dao.EmployeeActionDao;
import com.charvikent.issuetracking.dao.UserDao;
import com.charvikent.issuetracking.model.EmployeeAction;
import com.charvikent.issuetracking.model.User;
import com.charvikent.issuetracking.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EmployeeController {


	@Autowired
	private UserService userService;
	@Autowired
	EmployeeActionDao employeeActionDao;


	@Autowired
	UserDao userDao;

	@Autowired
	HttpSession session;
	
	
	private String password;
	
	
	@Autowired
	private SendSMS smsTemplate;
	@Autowired MailTemplate mailTemplate;

	@RequestMapping(value = "/employee", method = RequestMethod.GET, headers = "Accept=application/json")
	public String showEmployeePage(Model model,HttpServletRequest request) throws MessagingException
	{
		//mailTemplate.sendConfirmationEmail();
		
		
		model.addAttribute("userForm", new User());
		model.addAttribute("roles" ,userService.getRoles());
		model.addAttribute("shifts" ,userService.getShifts());
		List<User> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;

		try {
			listOrderBeans = userService.getAllUsers();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}

		return "employee";
		
		
	}

	@RequestMapping(value = "/employee" ,method = RequestMethod.POST)
	public String saveAdmin(@Valid @ModelAttribute  User user, BindingResult bindingresults,
			RedirectAttributes redir) throws IOException {

		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}

		int id = 0;
		try
		{
			//User userBean=null;
			
			 User userBean= userService.getUserByObject(user);

			
			int dummyId =0;

			if(userBean != null){
				dummyId = userBean.getId();
			}

			if(user.getId()==null)
			{
				if(dummyId ==0)
				{


					user.setStatus("1");
					Random rnd = new Random();
					int n = 100000 + rnd.nextInt(900000);
					String strRandomNumber =String.valueOf(n);
					user.setPassword(strRandomNumber);
					userService.saveUser(user);

					redir.addFlashAttribute("msg", "Record Inserted Successfully");
					redir.addFlashAttribute("cssMsg", "success");

				} else
				{
					redir.addFlashAttribute("msg", "Already Record Exist");
					redir.addFlashAttribute("cssMsg", "danger");

				}


			}

			else
			{
				id=user.getId();
				if(id == dummyId || userBean == null)
				{
					userService.updateUser(user);
					redir.addFlashAttribute("msg", "Record Updated Successfully");
					redir.addFlashAttribute("cssMsg", "warning");

				} else
				{
					redir.addFlashAttribute("msg", "Already Record Exist");
					redir.addFlashAttribute("cssMsg", "danger");
				}

			}

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return "redirect:employee";
	}


@RequestMapping(value = "/deleteUser")
	public @ResponseBody String deleteUser(User  objUser,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<User> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(objUser.getId() != 0){
 				delete =userService.deleteUser(objUser.getId(),objUser.getStatus());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}

			listOrderBeans = userService.getAllUsers();
			 objectMapper = new ObjectMapper();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {

				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				jsonObj.put("allOrders1", listOrderBeans);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
				jsonObj.put("allOrders1", listOrderBeans);
			}
		}catch(Exception e){
			e.printStackTrace();
	System.out.println(e);
			return String.valueOf(jsonObj);

		}
		return String.valueOf(jsonObj);
	}


	@RequestMapping("/changePassword")
	public String changePasswordHome(@ModelAttribute("changePassword") User user){

		return "changePassword";

	}
	@RequestMapping(value="/changePassword", method= RequestMethod.POST )
	public  @ResponseBody String changePassword(@ModelAttribute("changePassword") User user,RedirectAttributes redir,HttpServletRequest request){

		//User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//String id=String.valueOf(objuserBean.getId());

		//User objuserBean = (User) session.getAttribute("cacheUserBean");

		User users = userService.getUserById(user.getId());
		/*if(!(users.getPassword().equals(user.getNpassword()))) 
		{
			if(users.getPassword().equals(user.getPassword())) {*/
				users.setPassword(user.getNpassword());
				userService.updatePassword(users);
				
				return "Password Updated successfully";
			/*}
			else 
			{
				request.setAttribute("msg", "You Entered Wrong Password");
				request.setAttribute("cssMsg", "danger");
				return "redirect:changePassword";
			}
		}
		else 
		{
			redir.addFlashAttribute("msg", "Please don't use previous password");
			redir.addFlashAttribute("cssMsg", "warning");
			return "redirect:changePassword";	
		}*/
	}

	/*@RequestMapping("/editProfile")
	public String editProfileHome(@ModelAttribute("editProfile") User user,Model model){

		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//String id=String.valueOf(objuserBean.getId());


		model.addAttribute("editProfile", userService.getUserById(objuserBean.getId()));

		return "editProfile";

	}
	@RequestMapping(value="/editProfile", method= RequestMethod.POST )
	public String editProfile(@ModelAttribute("editProfile") User user,RedirectAttributes redir,HttpServletRequest request){

		User emp = userService.getUserById(user.getId());
		emp.setFirstName(user.getFirstName());
		emp.setLastName(user.getLastName());
		emp.setMobileNo(user.getMobileNo());
		emp.setEmailId(user.getEmailId());
		userService.updateUser(emp);

		redir.addFlashAttribute("msg", "You Details Updated Successfully");
		redir.addFlashAttribute("cssMsg", "warning");

			return "editProfile";
}*/


	/*@RequestMapping("/getUserName")
	public  @ResponseBody  Boolean getUserName(HttpServletRequest request, HttpSession session)
	{
		String username=request.getParameter("username");

		username = username.replaceAll("\\s+","");
		return userService.checkUserExist(username);
	}
*/
	@RequestMapping(value = "/inActiveEmp")
	public @ResponseBody String getAllActiveOrInactiveOrgnizations(User  objdept,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<User> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			if(objdept.getStatus().equals("0"))
				listOrderBeans = userService.getInActiveList();
				else
					listOrderBeans = userService.getAllUsers();



			 objectMapper = new ObjectMapper();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {

				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				jsonObj.put("allOrders1", listOrderBeans);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
				jsonObj.put("allOrders1", listOrderBeans);
			}
		}catch(Exception e){
			e.printStackTrace();
	System.out.println(e);
			return String.valueOf(jsonObj);

		}
		return String.valueOf(jsonObj);
	
	}
	/*@RequestMapping(value = "/getPwd", method = RequestMethod.POST)
	public @ResponseBody  Boolean getPassword(Model model,HttpServletRequest request) throws IOException 
	{
		LOGGER.debug("Calling  getPwd at controller");
		System.out.println("enter to getPwd");
		
		String custMobile=request.getParameter("mobile_no");
		Random random = new Random();
		 password = String.format("%06d", random.nextInt(1000000));
		
		SendSMS.sendSMS(password,custMobile);
		PasswordDetails pwddetails=new PasswordDetails();
		
				 pwddetails.setMobileNo(custMobile);
		 pwddetails.setPWDnumber( password);
		
		
		
		
		
		
		return true;
}*/
	
	@RequestMapping(value = "/getresetpassword" ,method = RequestMethod.POST)
	public @ResponseBody Boolean getResetUserPassword(Model model,HttpServletRequest request) throws IOException, MessagingException 
				{
				//LOGGER.debug("Calling getresetpassword at controller");
					System.out.println("enter to getresetpassword");
					String emailId=request.getParameter("emailId");
					System.out.println(emailId);
					/*Random rnd = new Random();
					int n = 100000 + rnd.nextInt(900000);
					String strRandomNumber =String.valueOf(n);
					PasswordDetails  pd =new PasswordDetails();
					pd.setPWDnumber(strRandomNumber);*/
					User custbean2=userService.checkUserExistOrNotbyEmailId(emailId);
					if(custbean2 != null)
					{
						//sendSMS.sendSMS(custbean2.getPassword(),emailid);
						 mailTemplate.resetPassword(custbean2);
						return true;	
					}
					else
						return false;
				} 

	@RequestMapping(value = "/checkUserExst", method = RequestMethod.POST)
	public @ResponseBody  Boolean checkCustomerExistence(@Validated @ModelAttribute  User user,Model model,HttpServletRequest request) throws IOException 
	{
		/*LOGGER.debug("Calling  checkCustExst at controller");*/
		System.out.println("enter to checkUserExst");
		
		String mobileNo=request.getParameter("mobileNo");
		
		String editFieldsId=request.getParameter("editFields");
		User custbean1 =null;
		if(editFieldsId.equals("0"))
		{
		
			custbean1 = userService.checkUserExistOrNotbyMobile(mobileNo);
		}
		else
		{
			custbean1 =userService.checkUserExistOrNotbyMobileOnEdit(mobileNo,editFieldsId);
			
		}
		
		if(custbean1 != null)
		{
			return true;
		}
		else
		
		return false;
		
	}	
	
	
	
	@RequestMapping(value = "/checkinout" ,method = RequestMethod.POST)
	public @ResponseBody String saveCheckInAndOutAction(HttpServletRequest request) throws IOException, MessagingException 
				{
		
		String msg="";
		
		          String actionId = request.getParameter("actionid");
		          
		         User currentUser = userService.getCurrentUser();
		        String currentShiftId =getCurrentShiftId();
		         
		         EmployeeAction empAction = new EmployeeAction();
		         
		         empAction.setActionId(actionId);
		         empAction.setEmpId(currentUser.getEmpId());
		         empAction.setShiftId(currentShiftId);
		         
		         Object actionTime=employeeActionDao.getTimeDiffereneOnCheckOut(empAction);
		         
		         System.out.println(actionTime);
		         
		        int checkinaction=Integer.parseInt((String) actionTime);
		         
		         
		         if(actionId.equals("1"))
		        	 
		         {
		        	 employeeActionDao.saveEmployeeAction(empAction);
		        	 msg ="checkin successfully";
		         }
		         else
		         {
		        	 if(checkinaction >8)
		        	 {
		        		 employeeActionDao.saveEmployeeAction(empAction);
		        		 msg ="checkout successfully";
		        	 }
		        	 else
		        		 msg ="8 hours not completed";
		        	 
		         }
		         
		         return msg;
		         
		         
		         
				 
				}
				
	
	private String getCurrentShiftId() {
		
		String shiftid ="";
			/*LocalTime ldt = java.time.LocalTime.now();

		    ldt = ldt.truncatedTo(ChronoUnit.HOURS);
		    System.out.println(ldt);
		    */
		    Calendar now = Calendar.getInstance();
		    System.out.println(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
		    
		    int hour =now.get(Calendar.HOUR_OF_DAY);
		    
		    
		    if(hour >= 7  &&  hour <=9)
		    {
		    	System.out.println("A");
		    	shiftid ="1";
		    }
		    else if(hour >= 9 && hour <= 14)
		    {
		    	System.out.println("C");
		    	shiftid ="3";
		    }
		    else 
		    {
		    	System.out.println("B");
		    	shiftid ="2";
		    }
		return  shiftid;
	}
 
	/*@RequestMapping(value = "/workingtime" ,method = RequestMethod.POST)
	public @ResponseBody String checkShiftTimeInHours(EmployeeAction employeeAction,HttpServletRequest request) throws IOException, MessagingException 
	{
		//String actionTime = request.getParameter("actiontime");
        
		Object actionTime=employeeActionDao.getTimeDiffereneOnCheckOut(employeeAction);
		
		System.out.println(actionTime);
		
		
		
		
		
		
		
		return null;
		
	}*/
		
		
	}
	

	

	

