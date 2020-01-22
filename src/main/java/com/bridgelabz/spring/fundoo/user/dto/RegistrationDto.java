package com.bridgelabz.spring.fundoo.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegistrationDto {

		/*
		 * The @NotBlank annotation uses the NotBlankValidator class, which
		 * checks that a character sequence's trimmed length is not empty:
		 */
	@NotNull(message = "First Name is mandatory")
	@Pattern(regexp = "^[a-zA-Z]*$") /**/
	private String firstName;

	@NotNull(message = "Last Name is mandatory")
	@Pattern(regexp = "^[a-zA-Z]*$")
	private String lastName;

	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\. [A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "email format wrong")
	private String email;

	@NotNull(message = "password is mandatory")
	private String password;

	
	private long phoneNumber;

	// class constructor.
	public RegistrationDto() {

	}

	// class constructor with feilds.
	public RegistrationDto(
			@NotNull(message = "First Name is mandatory") @Pattern(regexp = "^[a-zA-Z]*$") String firstName,
			@NotNull(message = "Last Name is mandatory") @Pattern(regexp = "^[a-zA-Z]*$") String lastName,
			@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\. [A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "phone format wrong") String email,
			@NotNull(message = "password is mandatory") String password,
			@Pattern(regexp = "(0/91)?[7-9][0-9]{9}", message = "phone format wrong") @NotNull(message = "phone is mandatory") long phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	// getters and setters.
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	// to string method.
	@Override
	public String toString() {
		return "RegistrationDto [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", phoneNumber=" + phoneNumber + "]";
	}

}
