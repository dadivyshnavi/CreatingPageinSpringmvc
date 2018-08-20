package com.charvikent.issuetracking.config;

import java.io.File;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

@Component
public class SendingMail {
	
	//private static final String SUBJECT_MAIL_TICKET_ISSUED = "Ticket Issued";
	
	@Autowired  
	private VelocityEngine velocityEngine; 

	@Autowired  
	private JavaMailSender javaMailSender; 
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired FilesStuff filePath;
	@SuppressWarnings("unused")
	public void sendConfirmationEmail() throws MessagingException {  
		try {
			
			
			//sending mail statically code
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
			
			//sending mail(means image save in resources with in current project) with file/picture attachment code by inline
			
			/*String path = request.getServletContext().getRealPath("/");
			File  moveFile = new File(path +"reportDocuments","1.jpg");
			helper.addInline("id101",moveFile );
			*/
			//sending mail(means image save in resources with in current project) with file/picture attachment code by inline
			
			
			//sending mail(statically uploading) with file/picture attachment any where from system
			
			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			
			FileSystemResource file = new FileSystemResource(new File("C:/Users/Administrator/Desktop/download.jpg"));
			helper.addAttachment("birdImage.jpg", file);
			//sender.send(message);
			
			//sending mail(statically uploading) with file/picture attachment any where from system
			
			javaMailSender.send(message);
			
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}
	
	
	 
	//Sending mail Dynamically
	
	
	public void sendFilesWithMultipleAttachment(String emailId,MultipartFile[] files) throws MessagingException {  
		try {
			
			
			String email =  emailId;
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			
			VelocityContext velocityContext = new VelocityContext();
			//velocityContext.put("name",description);
			
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("emailtemplate.vm", "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( email);
		    helper.setSubject("sending mail with multiple attachment");
		  		   
		   for(MultipartFile multipartFile : files) {
				String fileName = multipartFile.getOriginalFilename();
				if(!multipartFile.isEmpty())
				{
					FileSystemResource file = new FileSystemResource(filePath.makeDirectory() + File.separator + fileName);
					helper.addAttachment(file.getFilename(), file);
				}
				
			}
			javaMailSender.send(message);
				
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}
	
	
	//Sending mail Dynamically
	
 }
