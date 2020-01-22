package com.bridgelabz.spring.fundoo.collaborator.cutility;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class CollaboratorMessageUtility 
{

	/**
	 * Purpose:this method can be create object of simple mail message and return it
	 * 
	 * @param email user give email for checking
	 * @return simple email message object
	 */
	
	
	
	
	
	public static SimpleMailMessage sendMail(String email1, String email2 ,String note) 
	{
		/*
		 * SimpleMailMessage is a inbuilt class of java which implements MailMessage
		 * interface. Models a simple mail message, including data such as the from, to,
		 * cc, subject, and text fields. Consider JavaMailSender and JavaMail
		 * MimeMessages for creating more sophisticated messages,
		 */

		SimpleMailMessage msg = new SimpleMailMessage();
		
	/*	NoteModel note = noteRepository.findById(noteid).
				orElseThrow(() ->new IdNotFoundException(NoteUtility.NOTE_NOT_FOUND)) ;
	*/	
	//	Optional<NoteModel> note = noteRepository.findById(id);
		
		msg.setFrom(email1);
		msg.setTo(email2); // send mail
		msg.setSubject("Note Sharing"); // send message for user email account
		msg.setText("this is a  note " +note); // send token for user email account

		

		return msg;

	}

}
