package com.bridgelabz.spring.fundoo.user.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.spring.fundoo.user.dto.ForgetPasswordDto;
import com.bridgelabz.spring.fundoo.user.dto.LoginDto;
import com.bridgelabz.spring.fundoo.user.dto.RegistrationDto;
import com.bridgelabz.spring.fundoo.user.dto.SetPasswordDto;
import com.bridgelabz.spring.fundoo.user.response.Response;
import com.bridgelabz.spring.fundoo.user.service.UserServiceImplemented;


@RestController
@RequestMapping(value = "/fundoo")
public class UserController {

	@Autowired
	UserServiceImplemented userserviceimplemented;
	
	//-----------------------------------------------------------------------------------------------//

	@GetMapping("/user/demo")
	public String demo() {
		return "hello";
	}

	//-----------------------------------------------------------------------------------------------------//

	// 1 -->for registering the user into the database.
	@PostMapping("/user/register")
	public ResponseEntity<Response> register(@Valid @RequestBody RegistrationDto registrationDto) {

		return new ResponseEntity<Response>(userserviceimplemented.registerUser(registrationDto), HttpStatus.OK); // give

	}

	// ----------------------------------------------------------------------------------//

	// 2--->for validating the user into the database.
	@PostMapping("/user/validate")
	public ResponseEntity<Response> validate(@RequestParam String token) {

		return new ResponseEntity<Response>(userserviceimplemented.validateUser(token), HttpStatus.OK);
	}

	/*
	 * for validating the user into the database.
	 * 
	 * @GetMapping("/validate/{token}") public ResponseEntity<Response>
	 * validate(@PathVariable("token") String token) { System.out.println(token);
	 * return new
	 * ResponseEntity<Response>(userserviceimplemented.validateUser(token),
	 * HttpStatus.OK); }
	 */

	// ----------------------------------------------------------------------------------//

	// 3--->for login the user .
	@PostMapping("/user/login")
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDto loginDto) {

		return new ResponseEntity<Response>(userserviceimplemented.loginUser(loginDto), HttpStatus.OK);

	}

	// ---------------------------------------------------------------------------------------------//

	// 4--->for forget password
	@PostMapping("/user/forgotpassword")
	// public String findEmail(@RequestBody ForgetPasswordDto forgetPasswordDto)
	public ResponseEntity<Response> findEmail(@RequestBody ForgetPasswordDto forgetPasswordDto) {

		// userserviceimplemented.forgetPassword(forgetPasswordDto);
		// return UserUtility.MAIL_SEND;
		return new ResponseEntity<Response>(userserviceimplemented.forgetPassword(forgetPasswordDto), HttpStatus.OK);
	}
	
	// --------------------------------------------------------------------------------------------//

	// 5--->for resetting the password
	@PostMapping("/user/resetpassword")
	public ResponseEntity<Response> setNewPassword(@RequestParam String token,
			@RequestBody SetPasswordDto setpassworddto) {

		return new ResponseEntity<Response>(userserviceimplemented.setPassword(setpassworddto, token), HttpStatus.OK);
	}

	// ----------------------------------------------------------------------------------//

	// 6 ---> for finding a particular user.
	@GetMapping("/user/finduser")
	public ResponseEntity<Response> findBParticularUser(/* @RequestParam */ @RequestHeader String token) {

		return new ResponseEntity<Response>(userserviceimplemented.findBParticularUser(token), HttpStatus.OK);
	}

	// ----------------------------------------------------------------------------------//

	// 7---> for showing all the users from the DB.
	@GetMapping("/user/showuserz")
	public Response show(@RequestParam String token) {

		return new Response(200, "Show all details", userserviceimplemented.showAllUserz(token));
	}

}
