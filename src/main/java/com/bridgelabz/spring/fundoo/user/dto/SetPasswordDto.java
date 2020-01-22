package com.bridgelabz.spring.fundoo.user.dto;

public class SetPasswordDto {

	private String email;
	private String password;
	private String confirmPassword;

	// Class Constructor.
	public SetPasswordDto() {

	}

	// Class Constructor with feilds.
	public SetPasswordDto(String email, String password, String confirmPassword) {
		super();
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	// getters and setters.
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// to String method.
	@Override
	public String toString() {
		return "SetPasswordDto [email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ "]";
	}

}
