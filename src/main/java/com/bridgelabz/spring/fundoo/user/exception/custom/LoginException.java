package com.bridgelabz.spring.fundoo.user.exception.custom;

public class LoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoginException(String message) {
		super(message);
	}

}