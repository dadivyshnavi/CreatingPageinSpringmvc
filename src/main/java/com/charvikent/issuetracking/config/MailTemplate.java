package com.charvikent.issuetracking.config;

import java.io.StringWriter;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

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

	
	
}
