package com.bridgelabz.spring.fundoo.user.utility;

public class UserUtility {
	
	// user related message
	public static final Object SERVICE_REGISTER_METHOD = "please register to continue.";
	public final static String SUCCESS_LOGIN = "login successful";
	public final static String SUCCESS_REGISTER = "registration successful please verify in email verification";
	public final static String SUCCESS_VERIFY = "User updated and verify successful";
	public final static String SUCCESS_SET_PASSWORD = "User updated and password set successful";
	public final static String FAILED_LOGIN = "Invalid username and password and/or account is not active";
	public final static String FAILED_REGISTER = "registration failed";
	public final static String FAILED_TO_VERIFY = "failed to verify..!";
	public final static String FAILED_TO_SET_PASSWORD = "unable to set new password due to there's not email associated with your token";
	public final static String REGISTER_EMAIL_FOUND = "not found record ith this email cannot able to create new entry with this email";
	public final static String EMAIL_NOT_FOUND = " email not found in database";
	public final static String EMAIL_NULL= "email feild cannot be null,please provide your email id." ;
	public final static String UPLOAD_SUCCESS = "upload pic successfull";
	public final static String DELETE_PROFILE_SUCCESS = "delete profile pic successfull";
	public final static String GET_IMAGES_RESPONSE = "get images response";
	public final static String IMAGE_FORMAT_EXCEPTION = "Please upload proper image";
	public final static String IMAGE_UPDATE_FAILED = "Please upload pic first to update pic";
	public final static String GET_USER = "User details";
	public final static String PROFILE_PIC_NOT_UPLOADED ="your profile pic has not been uploaded , please try again.";

	// token key
	public final static String KEY_LOGIN = "loginkey";
	public final static String KEY_REGISTER_VERIFY = "verifykey";
	public final static String KEY_SET_PASSWORD = "secretkey";
	public final static String SECRET_KEY = "secret";

	// email related
	public final static String EMAIL_SEND = "email is send to recipient";
	public final static String EMAIL_SEND_FOR_FORGOT_PASSWORD = "Email is send for forget password click on link to send new password";
	public final static String EMAIL_SUBJECT_VERIFY = "Verify your email in UserService Application";
	public final static String EMAIL_SUBJECT_SETPASSWORD = "Setup new Password of UserService Application";
	public static final String EMAIL_REGISTERED_ALREADY = "Email id already Registered";
	public static final String EMAIL_NOTFOUND = "Email is not found";
	public static final String USER_PROFILE_REMOVE = "user profile remove successfully..";
	public static final String USER_PROFILE_ADD = "user profile add successfully..";
	public static final String VALIDATE_MAIL_TEXT = "\t validate your email\n"
			+ "http://localhost:8080/validate?token=";

	public static final String INVALID_USER = "Invalid username ";
	public static final String NOT_ACTIVE = "Kindly active your account by clicking on the sent link on your email id";
	public static final String USER_ADDED = "new User Registered Successfully ";
	public static final String EMAIL_NOT_VERFIED = "kindly verify your email by clicking on the link provided.";
	public static final String EMAIL_VERFIED = "User Email verfiy successfully";
	public static final String LOGIN_SUCCESSFUL = "Login successful";
	public static final String LOGIN_NOT_SUCCESSFULLY = "Login failed";
	public static final String USER_DELETE_SUCCESSFULLY = "User delete Succssfully";
	public static final String USER_UPDATED = "User update Succssfully";
	public static final String USER_ID_NOT_FOUND = "Person could not be found with id ";
	public static final String PROFILE_ADD_SUCCESSFYLLY = " profile add successfully";
	public static final String USER_DOESNT_EXIST = " user does not exist in your database. ";
	public static final String PASSWORD_CHANGED = "password Changed successfully";
	public static final String PASSWORD_NOT_CHANGE_SUCCESSFULLY = "password not Change  successfully";
	public static final String MAIL_SEND = " Mail send ";
	public static final String PASSWORD_NOT_MATCHING = " Both password are not matching";
	public static final String INVALID_TOKEN = " invalid token ";

	// validation related
	public final static String VALIDATE_EMAIL = "Please provide an email";
	public final static String VALIDATE_PROPER_EMAIL = "Please provide a proper email";
	public final static String VALIDATE_PASSWORD = "Please provide a password";
	public final static String VALIDATE_FNAME = "Please provide the first name";
	public final static String VALIDATE_LNAME = "Please provide the last name";

	// Http Requests
	public final static int HTTP_STATUS_OK = 200;
	public final static int HTTP_STATUS_BAD_REQUEST = 400;
	public final static int HTTP_STATUS_UNAUTHORIZED_CLIENT = 401;
	public final static int HTTP_STATUS_NOT_FOUND = 404;
	public final static int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;

}
