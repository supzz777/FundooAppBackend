package com.bridgelabz.spring.fundoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import javax.annotation.Resource;
import org.springframework.beans.factory.*;  
import org.springframework.beans.factory.xml.XmlBeanFactory;  
import org.springframework.core.io.*;  


@SpringBootApplication
@ComponentScan("com.bridgelabz.spring.fundoo") 
public class FundooBackendApplication {

	public static void main(String[] args) 
	{
		
		
		SpringApplication.run(FundooBackendApplication.class, args);
		
		
		
		
	}

}
