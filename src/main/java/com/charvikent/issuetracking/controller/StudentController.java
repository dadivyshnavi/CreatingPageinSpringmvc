package com.charvikent.issuetracking.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.charvikent.issuetracking.dao.studentDao;
import com.charvikent.issuetracking.model.student;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
public class StudentController {
	
	@Autowired studentDao studentdao; 
	
	
	@RequestMapping(value = "/student", method = RequestMethod.GET, headers = "Accept=application/json")
	public String showStudentPage(Model model,HttpServletRequest request)
	{
		model.addAttribute("studentForm" ,new student());
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
	public String saveStudent(@ModelAttribute student student)
	{
			
		System.out.println(student);
		studentdao.addStudent(student);
		return "redirect:student";
	
	}
	
	
	
	
	

}
