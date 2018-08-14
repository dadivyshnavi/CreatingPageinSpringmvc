package com.charvikent.issuetracking.config;

import java.io.File;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;




@Component
public class SendingMail {
	
	//private static final String SUBJECT_MAIL_TICKET_ISSUED = "Ticket Issued";
	
	@Autowired  
	private VelocityEngine velocityEngine; 

	@Autowired  
	private JavaMailSender javaMailSender; 
	
	@Autowired
	HttpServletRequest request;
	
	
	public void sendConfirmationEmail() throws MessagingException {  
		try {
			
			

			
			String email = "dhaadhivyshnavi@gmail.com";
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			
			
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("name", "Vyshnavi");
			velocityContext.put("mobilenumber","9999999999");
			velocityContext.put("email","vyshu@gmail.com");
			velocityContext.put("password","123456");
			
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("emailtemplate.vm", "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( email);
			helper.setSubject("Registration Successfully");  
			javaMailSender.send(message);
			
			
			
			
			
				
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}
	
	//sending with attachment
	
	/*public void sendAttachmentEmail() throws MessagingException {  
		try {
			
			JavaMailSenderImpl sender = new JavaMailSenderImpl();  
			 sender.setHost("mail.host.com");  
			MimeMessage message = sender.createMimeMessage();  
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			 // use the true flag to indicate you need a multipart message  
			 helper.setTo("email");  
			 helper.setText("Check out this image!");  
			 // let's attach the infamous windows Sample file (this time copied to c:/)  
			 FileSystemResource file = new FileSystemResource(new File("c:/sample.jpeg"));  
			 helper.addAttachment("CoolImage.jpeg", file);  
			 javaMailSender.send(message); 
			
			
			
	}catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}
		
		*/
		
		
		
		
		
			
}
