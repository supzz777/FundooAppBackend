package com.bridgelabz.spring.fundoo.user.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

public class RedisModel implements Serializable
{
	private String email;
	
	private String token;
	
	private LocalDateTime login;

	public RedisModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RedisModel(String email, String token, LocalDateTime login) {
		super();
		this.email = email;
		this.token = token;
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getLogin() {
		return login;
	}

	public void setLogin(LocalDateTime localDateTime) {
		this.login = localDateTime;
	}

	@Override
	public String toString() {
		return "RedisModel [email=" + email + ", token=" + token + ", login=" + login + "]";
	}
	
	
	
	
	

}
