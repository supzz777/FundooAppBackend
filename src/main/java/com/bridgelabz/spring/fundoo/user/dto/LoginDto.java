package com.bridgelabz.spring.fundoo.user.dto;

import javax.validation.constraints.NotNull;

public class LoginDto
{
		//regex pattern.
	
		@NotNull(message = "Please filled field")
		private  String email;
		@NotNull(message = "Please filled field") 	
		private String password;
		
		//Class Constructor.
		public LoginDto()
		{
			
		}
		
		//Class Constructor with feilds.
		public LoginDto(String email, String password) {
			super();
			this.email = email;
			this.password = password;
		}
		
		//getters and setters.
		public  String getEmail() {
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

		
		//to String method.
		@Override
		public String toString() {
			return "LoginDto [email=" + email + ", password=" + password + "]";
		}

	
	
}

