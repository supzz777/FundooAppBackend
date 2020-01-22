package com.bridgelabz.spring.fundoo.user.exception.custom;

public class TokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenException(String message) {

		super(message);
	}

}