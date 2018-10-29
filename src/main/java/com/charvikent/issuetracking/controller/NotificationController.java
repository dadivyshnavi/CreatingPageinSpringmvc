package com.charvikent.issuetracking.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charvikent.issuetracking.dao.NotificationDao;
import com.charvikent.issuetracking.model.RequestLeave;
import com.charvikent.issuetracking.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class NotificationController {
	 
	@Autowired NotificationDao notificationDao;
	@RequestMapping(value = "/notification", method = RequestMethod.GET, headers = "Accept=application/json")
	public String showNotificationPage(@ModelAttribute("requestForm")  RequestLeave req,Model model,HttpServletRequest request) throws MessagingException {
		// mailTemplate.sendConfirmationEmail();

		List<RequestLeave> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		model.addAttribute("requestForm", new RequestLeave());

		try {
			listOrderBeans = notificationDao.getAllMails();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				 System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}

		return "notification";

	}
	
	
}
