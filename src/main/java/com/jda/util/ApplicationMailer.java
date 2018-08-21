package com.jda.util;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service("MailService")
public class ApplicationMailer {
	
	//@Autowired
	 //private SimpleMailMessage preConfiguredMessage
	
	  public static  boolean sendMail(String link)
	    {
		  JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
			javaMailSenderImpl.setHost("smtp.gmail.com");
			javaMailSenderImpl.setPort(587);
			javaMailSenderImpl.setUsername("try.java01@gmail.com");
			javaMailSenderImpl.setPassword("qwertyuiop!@#$%");
			Properties properties = javaMailSenderImpl.getJavaMailProperties();
			properties.put("mail.transport.protocol","smtp");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.auth", true);
			properties.put("mail.smtp.starttls.enable", true);
			properties.put("mail.debug", true);
		  
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo("divyalaiker96@gmail.com");
	        message.setSubject("bootcamp");
	        message.setText("click on the link to reset password"+" "+link);
	        javaMailSenderImpl.send(message);
	        return true;
	    }
	
	    /*public void sendPreConfiguredMail(String message)
	    {
	        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
	        mailMessage.setText(message);
	        mailSender.send(mailMessage);
	    }*/
	
}
