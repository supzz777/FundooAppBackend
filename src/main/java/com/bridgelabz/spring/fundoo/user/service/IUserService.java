package com.bridgelabz.spring.fundoo.user.service;

import java.util.List;

import com.bridgelabz.spring.fundoo.user.dto.ForgetPasswordDto;
import com.bridgelabz.spring.fundoo.user.dto.LoginDto;
import com.bridgelabz.spring.fundoo.user.dto.RegistrationDto;
import com.bridgelabz.spring.fundoo.user.dto.SetPasswordDto;
import com.bridgelabz.spring.fundoo.user.model.User;
import com.bridgelabz.spring.fundoo.user.response.Response;

public interface IUserService {

	public Response registerUser(RegistrationDto regdto); // create addNewUser() method for add new user
	
	public Response validateUser(String token);
	
	public Response loginUser(LoginDto logindto); // create loginUser() method for login user

	public Response forgetPassword(ForgetPasswordDto forgetPasswordDto);

	public Response setPassword(SetPasswordDto setPasswordDto, String token);
	
	public String updateuser(User user, String email);
	
	public Response findBParticularUser(String token);
	
	public List<User> showAllUserz(String token);
	
	public Response logout(String token) ;

}
