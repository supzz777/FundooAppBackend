package com.bridgelabz.spring.fundoo.user.exception.custom;

public class ProfileNotSavedException extends RuntimeException{
	
	public ProfileNotSavedException (String message)
	{
		super(message);
	}

}
