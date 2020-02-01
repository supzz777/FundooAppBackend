package com.bridgelabz.spring.fundoo.notes.nexception.custom;

public  class InputException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;
	
	public InputException (String message)
	{
		super (message);
	}
	
}
