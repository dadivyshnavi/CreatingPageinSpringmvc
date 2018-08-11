package com.charvikent.issuetracking.controller;


import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charvikent.issuetracking.config.FilesStuff;
import com.charvikent.issuetracking.config.SendSMS;
import com.charvikent.issuetracking.config.SendingMail;
import com.charvikent.issuetracking.dao.studentDao;
import com.charvikent.issuetracking.model.student;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
public class StudentController {
	
	@Autowired studentDao studentdao; 
	@Autowired FilesStuff fileTemplate;
	
	@Autowired 
	private SendingMail mailTemplate;
	@Autowired
	private Environment env;  
	
	@RequestMapping(value = "/student", method = RequestMethod.GET, headers = "Accept=application/json")
	public String showStudentPage(Model model,HttpServletRequest request) throws MessagingException
	{
		mailTemplate.sendConfirmationEmail();
		
		
		model.addAttribute("studentForm" ,new student());
		model.addAttribute("roles" ,studentdao.getCourseMap());
		List<student> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;

		try {
			listOrderBeans = studentdao.getAllStudent();
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

		return "student";
		
		
	}
	
	
	@RequestMapping(value="/student" , method=RequestMethod.POST,headers = "Accept=application/json")
	public String saveAdmin(@Valid @ModelAttribute  student user, BindingResult bindingresults,
			RedirectAttributes redir,@RequestParam("file1") MultipartFile[] uploadedFiles, Model model) throws IOException {

		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}

		int id = 0;
		try
		{
			
			 student userBean= studentdao.getStudentByObject(user);

			int dummyId =0;

			if(userBean != null){
				dummyId = userBean.getId();
			}

			if(user.getId()==null)
			{
				if(dummyId ==0)
				{
					int filecount =0;
		        	 
		        	 for(MultipartFile multipartFile : uploadedFiles) {
		    				String fileName = multipartFile.getOriginalFilename();
		    				if(!multipartFile.isEmpty())
		    				{
		    					filecount++;
		    				 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
		    				}
		    			}
		        	 
		        	 if(filecount>0)
		        	 {
		        		 user.setFiles(fileTemplate.concurrentFileNames());
		        		 fileTemplate.clearFiles();
		        		 
		        	 }
				

					user.setStatus("1");

					studentdao.addStudent(user);
					String message= user.getFname()+"registered successfully";
					
					String str = env.getProperty("app.msg");
					
	                System.out.println(str);
	                 str = str.replace("_user_", user.getFname());
	                 str = str.replace("_address_", user.getLname());
	                 str = str.replace("_course_", user.getCourse());
	                 
	                 System.out.println(str);
	 				
					SendSMS.sendSMS(str,user.getMobile());
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
					
					int filecount =0;
		        	 
		        	 for(MultipartFile multipartFile : uploadedFiles) {
		    				String fileName = multipartFile.getOriginalFilename();
		    				if(!multipartFile.isEmpty())
		    				{
		    					filecount++;
		    				 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
		    				}
		    			}
		        	 
		        	 if(filecount>0)
		        	 {
		        		 user.setFiles(fileTemplate.concurrentFileNames());
		        		 fileTemplate.clearFiles();
		        		 
		        	 }
					
		        	
		        	studentdao.updateStudent(user);
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
		return "redirect:student";
	}
	
	
	@RequestMapping(value = "/deleteStudent")
	public @ResponseBody String deleteStudent(student  objUser,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<student> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(objUser.getId() != 0){
 				delete = studentdao.deleteStudent(objUser.getId(),objUser.getStatus());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}

			listOrderBeans =studentdao.getAllStudent();
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
	

	@RequestMapping(value = "/inActiveStudents")
	public @ResponseBody String getAllActiveOrInactiveStudents(student  objdept,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<student> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			if(objdept.getStatus().equals("0"))
				listOrderBeans = studentdao.getInActiveList();
				else
					listOrderBeans = studentdao.getAllStudent();



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
	
	
	
	@RequestMapping(value="/mail" , method=RequestMethod.POST,headers = "Accept=application/json")
	public void sendMail() throws MessagingException
	
	{
		System.out.println("mail block called");
		mailTemplate.sendConfirmationEmail();
		
		//mailSender.sendConfirmationEmail();
	}
	

}
