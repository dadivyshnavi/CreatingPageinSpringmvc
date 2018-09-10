package com.charvikent.issuetracking.config;

import java.io.StringWriter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.charvikent.issuetracking.model.User;

@Component
public class MailTemplate {
	
	@Autowired  
	private VelocityEngine velocityEngine; 

	@Autowired  
	private JavaMailSender javaMailSender; 

	
	public void sendEmail() {  
		try {
			MimeMessagePreparator preparator = new MimeMessagePreparator() {  
				@Override  
				public void prepare(MimeMessage mimeMessage) throws Exception {  
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage);  
					message.setTo("dhaadhivyshnavi@gmail.com");

					message.setSubject("vyshu");  
					//message.addAttachment("MyTestFile.txt", new ByteArrayResource(content));
					VelocityContext velocityContext = new VelocityContext();
				
					
					velocityContext.put("name","usename");
					velocityContext.put("issueId","hi krishna");
					StringWriter stringWriter = new StringWriter();
					velocityEngine.mergeTemplate("emailtemplate.vm", "UTF-8", velocityContext, stringWriter);
					
					//velocityContext.put("user",user);
					/*message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine  
							, "emailtemplate.vm", CHARSET_UTF8, model), true);*/  
					
					message.setText(stringWriter.toString(), true);
					
				}  
			};  
			this.javaMailSender.send(preparator);
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}


	public void resetPassword(User custbean2)throws MessagingException {
		try {
			String email =  custbean2.getEmailId();
			String password=custbean2.getPassword();
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("name",custbean2.getFirstName());
			velocityContext.put("password", password);
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("passwordtemplate.vm",    "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( email);
			helper.setSubject("Your password sent successfully");
			javaMailSender.send(message);	
			} catch (MailException e) {
				e.printStackTrace();
				System.out.println(e);
			}  
		}

		
	}  

