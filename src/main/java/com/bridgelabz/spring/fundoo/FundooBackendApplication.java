package com.bridgelabz.spring.fundoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching  //it will search for all the caching annotations throughout the program.
@ComponentScan("com.bridgelabz.spring.fundoo")
public class FundooBackendApplication {

	public static void main(String[] args) 
	{

		SpringApplication.run(FundooBackendApplication.class, args);

	}

}
