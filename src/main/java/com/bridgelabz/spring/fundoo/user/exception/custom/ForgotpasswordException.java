package com.bridgelabz.spring.fundoo.user.exception.custom;

public class ForgotpasswordException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ForgotpasswordException(String message) {
		super(message);
	}

}