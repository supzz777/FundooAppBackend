package com.bridgelabz.spring.fundoo.user.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bridgelabz.spring.fundoo.user.exception.custom.ForgotpasswordException;
import com.bridgelabz.spring.fundoo.user.exception.custom.LoginException;
import com.bridgelabz.spring.fundoo.user.exception.custom.RegistrationExcepton;
import com.bridgelabz.spring.fundoo.user.exception.custom.TokenException;
import com.bridgelabz.spring.fundoo.user.response.Response;
import com.bridgelabz.spring.fundoo.user.utility.UserUtility;
@ControllerAdvice /*@ControllerAdvice. @ControllerAdvice used for global error handling in the Spring MVC application.
										It also has full control over the body of the response and the status code. */
public class GlobalException 
{
	//----------------------------------------------------------------------------------------------//

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Response> loginException(Exception e) {

		/*
		 * The HTTP 401 Unauthorized client error status response code indicates that
		 * the request has not been applied because it lacks valid authentication
		 * credentials for the target resource. ... This status is similar to 403 , but
		 * in this case, authentication is possible.
		 */
		return new ResponseEntity<Response>(
				new Response(UserUtility.HTTP_STATUS_UNAUTHORIZED_CLIENT, e.getMessage(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//----------------------------------------------------------------------------------------------//

	@ExceptionHandler(RegistrationExcepton.class)
	public ResponseEntity<Response> registrationExcepton(Exception e) {

		return new ResponseEntity<Response>(
				new Response(UserUtility.HTTP_STATUS_UNAUTHORIZED_CLIENT, e.getMessage(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//----------------------------------------------------------------------------------------------//

	@ExceptionHandler(TokenException.class)
	public ResponseEntity<Response> tokenException(Exception e) {

		return new ResponseEntity<Response>(
				new Response(UserUtility.HTTP_STATUS_UNAUTHORIZED_CLIENT, e.getMessage(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//----------------------------------------------------------------------------------------------//

	@ExceptionHandler(ForgotpasswordException.class)
	public ResponseEntity<Response> forgotPasswordException(Exception e) {

		return new ResponseEntity<Response>(
				new Response(UserUtility.HTTP_STATUS_UNAUTHORIZED_CLIENT, e.getMessage(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//----------------------------------------------------------------------------------------------//
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> methodArgumentNotValidException(MethodArgumentNotValidException e){

		return new ResponseEntity<Response>(
				new Response(UserUtility.HTTP_STATUS_UNAUTHORIZED_CLIENT, "Email not proper", null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//----------------------------------------------------------------------------------------------//
	
	@ExceptionHandler(com.bridgelabz.spring.fundoo.user.exception.custom.TokenExpiredException.class)
	public ResponseEntity<Response> TokenExpiredException(Exception e){

		return new ResponseEntity<Response>(
				new Response(UserUtility.HTTP_STATUS_BAD_REQUEST, "token expired", "please login to continue"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//----------------------------------------------------------------------------------------------//
		
	@ExceptionHandler(com.bridgelabz.spring.fundoo.user.exception.custom.ProfileNotSavedException.class)
	public ResponseEntity<Response> ProfileNotSavedException(Exception e){

		return new ResponseEntity<Response>(
				new Response(UserUtility.HTTP_STATUS_BAD_REQUEST, "profile pic not uploaded ", "please ty again"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//----------------------------------------------------------------------------------------------//

}
