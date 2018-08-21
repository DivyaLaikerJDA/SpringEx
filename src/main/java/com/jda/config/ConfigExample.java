package com.jda.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.jda")
@EnableWebMvc
public class ConfigExample {

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver ivr = new InternalResourceViewResolver();
		ivr.setPrefix("/WEB-INF/Template/");
		ivr.setSuffix(".jsp");
		return ivr;
	}

	// @Bean
	// public UserService getUserService(){
	// return new UserService();
	// }
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://" + System.getenv("DBHOST") + ":3306/" + System.getenv("DBNAME"));
		dataSource.setUsername(System.getenv("DBUSER"));
		dataSource.setPassword(System.getenv("DBPASSWORD"));
		return dataSource;
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	public JavaMailSender getJavaMailSender(){
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost("smtp.gmail.com");
		javaMailSenderImpl.setPort(587);
		javaMailSenderImpl.setUsername("try.java01@gmail.com");
		javaMailSenderImpl.setPassword("qwertyuiop!@#$%");
		Properties properties = javaMailSenderImpl.getJavaMailProperties();
		properties.put("mail.transport.protocol"," smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.debug", "true");
		return javaMailSenderImpl;
		
	}
}
