package com.bridgelabz.spring.fundoo.user.utility;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MessageUtility {

	/**
	 * Purpose:this method can be create object of simple mail message and return it
	 * 
	 * @param email user give email for checking
	 * @return simple email message object
	 */
	public static SimpleMailMessage verifyUserMail(String email, String token, String link) {
		/*
		 * SimpleMailMessage is a inbuilt class of java which implements MailMessage
		 * interface. Models a simple mail message, including data such as the from, to,
		 * cc, subject, and text fields. Consider JavaMailSender and JavaMail
		 * MimeMessages for creating more sophisticated messages,
		 */

		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setTo(email); // send mail
		msg.setSubject("test"); // send message for user email account
		msg.setText("hello" + (link + token)); // send token for user email account

		System.out.println("in simple mail :" + email);

		return msg;

	}

}
