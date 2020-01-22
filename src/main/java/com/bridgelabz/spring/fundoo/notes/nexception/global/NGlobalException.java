package com.bridgelabz.spring.fundoo.notes.nexception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.spring.fundoo.notes.nexception.custom.InvalidUserException;
import com.bridgelabz.spring.fundoo.notes.nutitlity.NoteUtility;
import com.bridgelabz.spring.fundoo.user.response.Response;

@ControllerAdvice /*
					 * @ControllerAdvice. @ControllerAdvice used for global error handling in the
					 * Spring MVC application. It also has full control over the body of the
					 * response and the status code.
					 */
public class NGlobalException
{
	//------------------------------------------------------------------------------------------//
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<Response> InvalidUserException(Exception e) {

		return new ResponseEntity<Response>(new Response(NoteUtility.HTTP_STATUS_UNAUTHORIZED_CLIENT, e.getMessage(),
				"user not found in the databse."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//------------------------------------------------------------------------------------------------//

	@ExceptionHandler(com.bridgelabz.spring.fundoo.notes.nexception.custom.IdNotFoundException.class)
	public ResponseEntity<Response> IdNotFoundException(Exception e) {

		return new ResponseEntity<Response>(new Response(NoteUtility.HTTP_STATUS_UNAUTHORIZED_CLIENT, e.getMessage(),
				"user not found in the databse."), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
