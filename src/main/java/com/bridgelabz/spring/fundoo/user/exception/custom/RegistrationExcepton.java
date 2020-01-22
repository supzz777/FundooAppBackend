package com.bridgelabz.spring.fundoo.user.exception.custom;

public class RegistrationExcepton  extends RuntimeException
{
	
	/**
	 *     Throws new exception for give wrong information
	 */
	private static final long serialVersionUID = 1L;
	
	public RegistrationExcepton(String message)
	{
		super(message);
	}

}
