package com.bridgelabz.spring.fundoo.user.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.spring.fundoo.notes.nutitlity.NoteUtility;
import com.bridgelabz.spring.fundoo.user.config.Passwordconfig;
import com.bridgelabz.spring.fundoo.user.dto.ForgetPasswordDto;
import com.bridgelabz.spring.fundoo.user.dto.LoginDto;
import com.bridgelabz.spring.fundoo.user.dto.RegistrationDto;
import com.bridgelabz.spring.fundoo.user.dto.SetPasswordDto;
import com.bridgelabz.spring.fundoo.user.exception.custom.ForgotpasswordException;
import com.bridgelabz.spring.fundoo.user.exception.custom.ProfileNotSavedException;
import com.bridgelabz.spring.fundoo.user.exception.custom.RegistrationExcepton;
import com.bridgelabz.spring.fundoo.user.exception.custom.TokenException;
import com.bridgelabz.spring.fundoo.user.exception.custom.TokenExpiredException;
import com.bridgelabz.spring.fundoo.user.exception.custom.ValidateuserException;
import com.bridgelabz.spring.fundoo.user.model.RedisModel;
import com.bridgelabz.spring.fundoo.user.model.User;
import com.bridgelabz.spring.fundoo.user.repository.IRedisRepository;
import com.bridgelabz.spring.fundoo.user.repository.RedisRepositoryImplemented;
import com.bridgelabz.spring.fundoo.user.repository.UserRepository;
import com.bridgelabz.spring.fundoo.user.response.Response;
import com.bridgelabz.spring.fundoo.user.utility.MessageUtility;
import com.bridgelabz.spring.fundoo.user.utility.TokenUtility;
import com.bridgelabz.spring.fundoo.user.utility.UserUtility;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

@Service
public class UserServiceImplemented implements IUserService {

	@Autowired
	private UserRepository userRepository; // create object user repo

    @Autowired 
    private RedisRepositoryImplemented redisRepository;
 
	@Autowired
	private JavaMailSender javaMailSender; // use JavaMailSender class

	@Autowired
	private PasswordEncoder passwordEncoder; // create object passwordencoder class

	@Autowired
	private Passwordconfig config;

	@Autowired
	private TokenUtility tokenutility; // create object for tokenutility

	@Autowired
	private ModelMapper mapper; // user modelmapper for store data
	

	static org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImplemented.class);
	

	// -------------------------------------------------------------------------//
	
	//1 --> service implementation for user registration.
	@Override
	public Response registerUser(RegistrationDto registrationDto) {

		User user = mapper.map(registrationDto, User.class); // store new user data in mapper

		System.out.println(user + "user mapper");
		System.out.println(user.getEmail());
		// check if user is already present in the DB or not.
		// User user1 =repository.findByEmail(registrationDto.getEmail());

		// if( user.getEmail().equals( registrationDto.getEmail() ) )
		if (userRepository.findAll().stream().anyMatch(i -> i.getEmail().equals(registrationDto.getEmail()))) {
			System.out.println(user.getEmail() + "*****************");
			System.out.println(registrationDto.getEmail() + "in register");
			throw new RegistrationExcepton(UserUtility.EMAIL_REGISTERED_ALREADY);
		}

		// setting the password in the encoded form for storing inside the DB.
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

		// password Strored inside the DB.
		user = userRepository.save(user); // store user all detail in db

		// if (user.getEmail() == null)
		if (user.getEmail().isEmpty()) {
			throw new RegistrationExcepton(UserUtility.INVALID_USER);
		}

		String token = tokenutility.createToken(user.getEmail());
		logger.info(token);

		javaMailSender
				.send(MessageUtility.verifyUserMail(registrationDto.getEmail(), token, UserUtility.VALIDATE_MAIL_TEXT));

		logger.info("Mail sent successfully.");

		return new Response(UserUtility.HTTP_STATUS_OK, "User Registration Completed ", UserUtility.USER_ADDED);

	}

	//-----------------------------------------------------------------------------------------------------//
	
		//2 --> service implementation for validating the user registration.
		@Override
		public Response validateUser(String token) {
		// extraxcting userz email id from the given token.
		String email = tokenutility.decodeToken(token); // get user id from user token.

		if (email.isEmpty()) {
			throw new TokenException(UserUtility.INVALID_TOKEN);
		}

		// finding the user details in the DB, from the obtained email id of the user.
		User user = userRepository.findByEmail(email);

		if (user != null) {
			// if userid is found , set the validate to true
			user.setValidate(true);
			// save the changes in the DB also.
			userRepository.save(user);
			logger.info("user saved to database.");
			return new Response(UserUtility.HTTP_STATUS_OK, "account verified ", UserUtility.EMAIL_VERFIED);
		} else {
			return new Response(UserUtility.HTTP_STATUS_BAD_REQUEST, "account not verfied",
					UserUtility.EMAIL_NOT_VERFIED);

		}

	}

	// ----------------------------------------------------------------------------------//
		
		//3 --> service implementation for login  user after the registration.
		@Override
		public Response loginUser(LoginDto logindto) {
		// finding the user details from the provided email id.
		User user = userRepository.findByEmail(logindto.getEmail()); // find email present or not

	
		System.out.println("1");
		// checking if user details are null.
		if (user == null) {
			System.out.println("2");
			return new Response(UserUtility.HTTP_STATUS_NOT_FOUND, "User LOGIN Failed. ", UserUtility.INVALID_USER);

		}
		System.out.println("3");
		// else generating the token using email of the user.
		String token = tokenutility.createToken(user.getEmail());
		System.out.println("4");
		// if user is not validated in the DB.
		if (!user.isValidate()) {
			System.out.println("5");
			new ValidateuserException(UserUtility.NOT_ACTIVE);
		} else {
			System.out.println("6");
			// matches() method of PasswordEncoder interface matches the raw password
			// entered by the user with the encoded password present in the DB.
			if (user.getEmail().equals(logindto.getEmail())
					&& config.encoder().matches(logindto.getPassword()/* raw password entered inside the DTO. */,
							user.getPassword()/* this is encoded password */ ))
			{
				System.out.println("7");
				RedisModel redisModel = new RedisModel();
				System.out.println("8");
				redisModel.setEmail(user.getEmail());
				redisModel.setToken(token);
				redisModel.setLogin(LocalDateTime.now());
				System.out.println(redisModel.toString());
				redisRepository.save(redisModel);
				// encode the user
				System.out.println("10");
				user.setLogin(true);
				System.out.println("11");
				logger.info("Login successful");
				System.out.println("12");
				return new Response(UserUtility.HTTP_STATUS_OK, UserUtility.LOGIN_SUCCESSFUL, token); // password

			}
		}

		return new Response(UserUtility.HTTP_STATUS_BAD_REQUEST, "User LOGIN failed. ",
				UserUtility.LOGIN_NOT_SUCCESSFULLY);

	}

	// ---------------------------------------------------------------------------------------//
		
		//4 --> service implementation forgetPassword of the user
		@Override
		// public Response forgetPassword(String email, String token)
		public Response forgetPassword(ForgetPasswordDto fgdto) {
		// Finding the user details from the provided email in DTO.
		User user = userRepository.findByEmail(fgdto.getEmail());

		// printing the user details on console.
		System.out.println(user);

		if (user == null) {
			// if user Details are null throwing Forgotpassword exception.
			throw new ForgotpasswordException(UserUtility.USER_DOESNT_EXIST);

		} else {
			// if user exists generate the token using its email.

			String token1 = tokenutility.createToken(user.getEmail());

			// send the token inside the mail for further verification.
			javaMailSender
					.send(MessageUtility.verifyUserMail(fgdto.getEmail(), token1, UserUtility.VALIDATE_MAIL_TEXT));

		}

		return new Response(UserUtility.HTTP_STATUS_OK, "Token generated Successfully.", UserUtility.EMAIL_SEND);

	}

	// ---------------------------------------------------------------------------------------//
		
		
		//5 --> service implementation set new Password of the user
		@Override
		public Response setPassword(SetPasswordDto setPasswordDto, String token) {
			
			if(redisRepository.findUser(token)== null)
			{
				throw new TokenExpiredException(NoteUtility.LOGIN_FIRST);
			}
			
		// extracting the email id of the user from the given token and storing it into
		// a variable.
			
		String userEmail = tokenutility.decodeToken(token);

		// finding the user in the database with the help of itz emailid.
		User updateuser = userRepository.findByEmail(userEmail);

		// if provided password and confirm password are entered equal by the user then
		// enter inside the loop.
		if (setPasswordDto.getPassword().equals(setPasswordDto.getConfirmPassword())) {

			// setting userz new password.
			// using PasswordEncoder.encode() method to store the password in the encrypted
			// form in our DB.
			updateuser.setPassword(passwordEncoder.encode(setPasswordDto.getPassword())); // new password encode it

			// updating the user with itz new password.
			// updateuser(updateuser, userEmail); //method called to update the user, as
			// changes have been made in the password.

			// returning the response class .
			return new Response(UserUtility.HTTP_STATUS_OK, "Dear User Your password has been successfully changed.",
					UserUtility.PASSWORD_CHANGED);

		} else {

			return new Response(UserUtility.HTTP_STATUS_BAD_REQUEST, "Sorry some error occured. Password not Changed.",
					UserUtility.PASSWORD_NOT_MATCHING);

		}

	}

	// ----------------------------------------------------------------------------------------//

		// method to update the user with the done changes.
		@Override
		public String updateuser(User user, String email) {
	
			User userupdate = userRepository.findByEmail(email);
			userupdate = user;
			userRepository.save(userupdate);
			return UserUtility.USER_UPDATED;
		}

	// ----------------------------------------------------------------------------------------//
		
	//6 --> find the particular user from the database with the given token.	
	@Override
	public Response findBParticularUser(String token) {
		if(redisRepository.findUser(token)== null)
		{
			throw new TokenExpiredException(NoteUtility.LOGIN_FIRST);
		}
		String useremail = tokenutility.decodeToken(token);
		if (useremail.isEmpty()) {
			throw new TokenException(UserUtility.INVALID_TOKEN);
		}

		return new Response(UserUtility.HTTP_STATUS_OK, "User Registrtion ", userRepository.findByEmail(useremail));
	}

	// ----------------------------------------------------------------------------------------//
	
	//7 --> find all the  users from the database 
	@Override
	public List<User> showAllUserz(String token) {
		
		if(redisRepository.findUser(token)== null)
		{
			throw new TokenExpiredException(NoteUtility.LOGIN_FIRST);
		}
		
		System.out.println("check");
		return userRepository.findAll(); // show all user details in mysql.
	}
	
	// ----------------------------------------------------------------------------------------//

	//8 --> method to logout the  user
	@Override
	public Response logout(String token) 
	{
		
		if(redisRepository.findUser(token)== null)
		{
			throw new TokenExpiredException(NoteUtility.LOGIN_FIRST);
		}
		
		String useremail = tokenutility.decodeToken(token);
		
		if (useremail.isEmpty()) {
			throw new TokenException(UserUtility.INVALID_TOKEN);
		}
		else
		{
			redisRepository.delete(token);
			return new Response(UserUtility.HTTP_STATUS_OK, "User logged out ", " Logout successful ");
		}
		
	}	
	// ---------------------------------------------------------------------------------------------------------//
	

		//9 --> method to upload the profile pic.
		@Override
		public Response uploadProfile(MultipartFile file, String token) 
		{	System.out.println("1");
			if(redisRepository.findUser(token)== null)
			{	System.out.println("2");
				throw new TokenExpiredException(NoteUtility.LOGIN_FIRST);
			}
			System.out.println("3");
			String useremail = tokenutility.decodeToken(token);
			System.out.println("4");
			User user = userRepository.findByEmail(useremail);
			System.out.println("5");
			if (useremail.isEmpty()) {
				System.out.println("6");
				throw new TokenException(UserUtility.INVALID_TOKEN);
			}
			System.out.println("7");
			File fileobj = new File(file.getOriginalFilename()); // get file name from file object(Multipart file)
			System.out.println("8");
//			Map<String, String> map = new HashMap<>();
//			map.put("cloud_name", "dm7gxgahq"); // create map objec and set cloud name,api key, api secret key
//			map.put("api_key", "493727798585473");
//			map.put("api_secret", "gCKnTn3HPZxyDHUJA6yuSX5BY6Y");
			Map map = ObjectUtils.asMap(
					  "cloud_name", "dm7gxgahq",
					  "api_key", "493727798585473",
					  "api_secret", "gCKnTn3HPZxyDHUJA6yuSX5BY6Y");
			
			System.out.println("9");
			Cloudinary cloudinary = new Cloudinary(map); // set map object in cloudinary constructor
			System.out.println("10");
			Map<?, ?> uploadedResult = null;
			System.out.println("11");
			try {
				System.out.println("12");
			uploadedResult = cloudinary.uploader().upload(fileobj, ObjectUtils.emptyMap());
			} catch (Exception e) {
				System.out.println("13");
			throw new ProfileNotSavedException(UserUtility.PROFILE_PIC_NOT_UPLOADED);
			}
		
			System.out.println("14");
			user.setProfilePic(uploadedResult.get("secure_url").toString()); // get secure url from uploaded result and
			// set
			// into user object
			System.out.println("15");
			userRepository.save(user);
			System.out.println("16");
			return new Response(UserUtility.HTTP_STATUS_OK, "PROFILE_SAVE_SUCCESSFULLY ", null);

				//cloudinary.uploader.destroy('zombie', function(result) { console.log(result) });
		}
	// ------------------------------------------------------------------------------------------
		
}
