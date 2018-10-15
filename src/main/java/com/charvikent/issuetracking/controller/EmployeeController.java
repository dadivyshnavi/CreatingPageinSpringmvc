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
import com.charvikent.issuetracking.dao.RequestLeaveDao;
import com.charvikent.issuetracking.dao.UserDao;
import com.charvikent.issuetracking.model.EmployeeAction;
import com.charvikent.issuetracking.model.RequestLeave;
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
	@Autowired
	MailTemplate mailTemplate;
@Autowired RequestLeaveDao requestleaveDao;
	/**
	 * @param model
	 * @param request
	 * @return
	 * @throws MessagingException
	 * This method is used to show the Employee page when we request the employee url
	 * we can see registered employees list 
	 */
	@RequestMapping(value = "/employee", method = RequestMethod.GET, headers = "Accept=application/json")
	public String showEmployeePage(Model model, HttpServletRequest request) throws MessagingException {
		// mailTemplate.sendConfirmationEmail();

		model.addAttribute("userForm", new User());
		model.addAttribute("roles", userService.getRoles());
		model.addAttribute("shifts", userService.getShifts());
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

	/**
	 * @param user
	 * @param bindingresults
	 * @param redir
	 * @return
	 * @throws IOException
	 * This method is used to create,save and update the employee details (with duplicate checking) in database
	 */
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public String saveAdmin(@Valid @ModelAttribute User user, BindingResult bindingresults, RedirectAttributes redir) throws IOException {

		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}

		int id = 0;
		try {
			// User userBean=null;

			User userBean = userService.getUserByObject(user);

			int dummyId = 0;

			if (userBean != null) {
				dummyId = userBean.getId();
			}

			if (user.getId() == null) {
				if (dummyId == 0) {

					user.setStatus("1");
					Random rnd = new Random();
					int n = 100000 + rnd.nextInt(900000);
					String strRandomNumber = String.valueOf(n);
					user.setPassword(strRandomNumber);
					userService.saveUser(user);

					redir.addFlashAttribute("msg", "Record Inserted Successfully");
					redir.addFlashAttribute("cssMsg", "success");

				} else {
					redir.addFlashAttribute("msg", "Already Record Exist");
					redir.addFlashAttribute("cssMsg", "danger");

				}

			}

			else {
				id = user.getId();
				if (id == dummyId || userBean == null) {
					userService.updateUser(user);
					redir.addFlashAttribute("msg", "Record Updated Successfully");
					redir.addFlashAttribute("cssMsg", "warning");

				} else {
					redir.addFlashAttribute("msg", "Already Record Exist");
					redir.addFlashAttribute("cssMsg", "danger");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return "redirect:employee";
	}

	/**
	 * @param objUser
	 * @param model
	 * @param request
	 * @param session
	 * @param objBindingResult
	 * This method is used to delete employee details from database
	 * @return
	 */
	@RequestMapping(value = "/deleteUser")
	public @ResponseBody String deleteUser(User objUser, ModelMap model, HttpServletRequest request,
			HttpSession session, BindingResult objBindingResult) {
		List<User> listOrderBeans = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson = null;
		boolean delete = false;
		try {
			if (objUser.getId() != 0) {
				delete = userService.deleteUser(objUser.getId(), objUser.getStatus());
				if (delete) {
					jsonObj.put("message", "deleted");
				} else {
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
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return String.valueOf(jsonObj);

		}
		return String.valueOf(jsonObj);
	}

	@RequestMapping("/changePassword")
	public String changePasswordHome(@ModelAttribute("changePassword") User user) {

		return "changePassword";

	}

	/**
	 * @param user
	 * @param redir
	 * @param request
	 * this method is used to change the password from old password to new password for selected employee by ID
	 * @return
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public @ResponseBody String changePassword(@ModelAttribute("changePassword") User user, RedirectAttributes redir,
			HttpServletRequest request) {

		// User objuserBean =
		// (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// String id=String.valueOf(objuserBean.getId());

		// User objuserBean = (User) session.getAttribute("cacheUserBean");

		User users = userService.getUserById(user.getId());
		/*
		 * if(!(users.getPassword().equals(user.getNpassword()))) {
		 * if(users.getPassword().equals(user.getPassword())) {
		 */
		users.setPassword(user.getNpassword());
		userService.updatePassword(users);

		return "Password Updated successfully";
		/*
		 * } else { request.setAttribute("msg", "You Entered Wrong Password");
		 * request.setAttribute("cssMsg", "danger"); return "redirect:changePassword"; }
		 * } else { redir.addFlashAttribute("msg",
		 * "Please don't use previous password"); redir.addFlashAttribute("cssMsg",
		 * "warning"); return "redirect:changePassword"; }
		 */
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 *this method is used to get the forgot password to  registered email 
	 */
	@RequestMapping(value = "/getresetpassword", method = RequestMethod.POST)
	public @ResponseBody Boolean getResetUserPassword(Model model, HttpServletRequest request)
			throws IOException, MessagingException {
		System.out.println("enter to getresetpassword");
		String emailId = request.getParameter("emailId");
		System.out.println(emailId);
		/*
		 * Random rnd = new Random(); int n = 100000 + rnd.nextInt(900000); String
		 * strRandomNumber =String.valueOf(n); PasswordDetails pd =new
		 * PasswordDetails(); pd.setPWDnumber(strRandomNumber);
		 */
		User custbean2 = userService.checkUserExistOrNotbyEmailId(emailId);
		if (custbean2 != null) {
			// sendSMS.sendSMS(custbean2.getPassword(),emailid);
			mailTemplate.resetPassword(custbean2);
			return true;
		} else
			return false;
	}

	/**
	 * this method is used to check the employee is exist or not by mobile number
	 * @param user
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	
	 */
	@RequestMapping(value = "/checkUserExst", method = RequestMethod.POST)
	public @ResponseBody Boolean checkCustomerExistence(@Validated @ModelAttribute User user, Model model,
			HttpServletRequest request) throws IOException {
		/* LOGGER.debug("Calling  checkCustExst at controller"); */
		System.out.println("enter to checkUserExst");

		String mobileNo = request.getParameter("mobileNo");

		String editFieldsId = request.getParameter("editFields");
		User custbean1 = null;
		if (editFieldsId.equals("0")) {

			custbean1 = userService.checkUserExistOrNotbyMobile(mobileNo);
		} else {
			custbean1 = userService.checkUserExistOrNotbyMobileOnEdit(mobileNo, editFieldsId);

		}

		if (custbean1 != null) {
			return true;
		} else

			return false;

	}

	/**
	 * this method is used to save the Employee Action in database by checkin and checkout
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 */
	@RequestMapping(value = "/checkinout", method = RequestMethod.POST)
	public @ResponseBody String saveCheckInAndOutAction(HttpServletRequest request)
			throws IOException, MessagingException {

		String msg = "";

		String actionId = request.getParameter("actionid");

		User currentUser = userService.getCurrentUser();
		String currentShiftId = getCurrentShiftId();

		EmployeeAction empAction = new EmployeeAction();

		empAction.setActionId(actionId);
		empAction.setEmpId(currentUser.getEmpId());
		empAction.setShiftId(currentShiftId);

		if (actionId.equals("1"))

		{
			employeeActionDao.saveEmployeeAction(empAction);
			msg = "checkin successfully";
		} 
		else {
			
			int actionTime = employeeActionDao.getTimeDiffereneOnCheckOut(empAction);
			System.out.println(actionTime);
			
			int checkinaction = (Integer) actionTime;

			if (checkinaction >8)
			{

				employeeActionDao.saveEmployeeAction(empAction);
				msg = "checkout successfully";
			} 
			else 
			{
				msg = "8 hours not completed";

			     
			}
		}

		return msg;

	}

	private String getCurrentShiftId() {

		String shiftid = "";
		/*
		 * LocalTime ldt = java.time.LocalTime.now();
		 * 
		 * ldt = ldt.truncatedTo(ChronoUnit.HOURS); System.out.println(ldt);
		 */
		Calendar now = Calendar.getInstance();
		System.out.println(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));

		int hour = now.get(Calendar.HOUR_OF_DAY);

		if (hour >= 7 && hour < 9) {
			System.out.println("A");
			shiftid = "1";
		} else if (hour >= 9 && hour < 14) {
			System.out.println("C");
			shiftid = "3";
		} else {
			System.out.println("B");
			shiftid = "2";
		}
		return shiftid;
	}

	/**
	 * this method is used to the  inActive Employees list
	 * @param objdept
	 * @param model
	 * @param request
	 * @param session
	 * @param objBindingResult
	 * @return
	 */
	@RequestMapping(value = "/inActiveUser")
	public @ResponseBody String getAllActiveOrInactiveUser(User  objdept,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		//LOGGER.debug("Calling inActiveEmp at controller");
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

	
	/**
	 * this method is used to sending email for requestForLeave 
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 */
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/getRequestforleave", method = RequestMethod.POST)
	public  String getRequestforleave(@ModelAttribute("requestLeave") RequestLeave rl,HttpServletRequest request,RedirectAttributes redir)
			throws IOException, MessagingException {
		
		
		System.out.println("enter to getRequestforleave");
		User currentUser = userService.getCurrentUser();
		rl.setEmpId(currentUser.getEmpId());
		String emailId = request.getParameter("emailId");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String description = request.getParameter("description");
		requestleaveDao.saveRequestLeave(rl);
		System.out.println(rl);
		RequestLeave requestleave = requestleaveDao.checkUserExistOrNotbyEmailId(emailId);
		if (rl != null) {
		mailTemplate.requestForLeave(rl);
		
		redir.addFlashAttribute("msg", "Request Send Succesfully");
		redir.addFlashAttribute("cssMsg", "success");
			return "redirect:dashBoard";
		}  else
			return "";
		
	}
}
