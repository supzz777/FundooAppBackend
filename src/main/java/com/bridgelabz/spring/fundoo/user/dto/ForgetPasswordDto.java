package com.bridgelabz.spring.fundoo.user.dto;

public class ForgetPasswordDto {

	private String email;

	// Class constructor.
	public ForgetPasswordDto() {

	}

	// Class constructor with feilds.
	public ForgetPasswordDto(String email) {
		super();
		this.email = email;
	}

	// getters and setters.
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// to String method.
	@Override
	public String toString() {
		return "ForgetPasswordDto [email=" + email + "]";
	}

}
