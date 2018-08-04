package com.charvikent.issuetracking.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.charvikent.issuetracking.model.student;
@Controller
public class StudentController {
	
	
	@RequestMapping(value = "/student", method = RequestMethod.GET, headers = "Accept=application/json")
	public String showStudentPage(Model model)
	{
		model.addAttribute("studentForm" ,new student());
		return "student";
		
		
		
	}

}
