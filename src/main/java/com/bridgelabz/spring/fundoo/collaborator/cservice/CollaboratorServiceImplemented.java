package com.bridgelabz.spring.fundoo.collaborator.cservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.spring.fundoo.collaborator.cdto.CollaboratorDto;
import com.bridgelabz.spring.fundoo.collaborator.cmodel.CollaboratorModel;
import com.bridgelabz.spring.fundoo.collaborator.crepository.CollaboratorRepository;
import com.bridgelabz.spring.fundoo.collaborator.cutility.CollaboratorUtility;
import com.bridgelabz.spring.fundoo.collaborator.cutility.CollaboratorMessageUtility;
import com.bridgelabz.spring.fundoo.label.lutility.LabelUtility;
import com.bridgelabz.spring.fundoo.notes.nexception.custom.IdNotFoundException;
import com.bridgelabz.spring.fundoo.notes.nexception.custom.InvalidUserException;
import com.bridgelabz.spring.fundoo.notes.nmodel.NoteModel;
import com.bridgelabz.spring.fundoo.notes.nrepository.NoteRepository;
import com.bridgelabz.spring.fundoo.notes.nutitlity.NoteUtility;
import com.bridgelabz.spring.fundoo.user.response.Response;
import com.bridgelabz.spring.fundoo.user.utility.TokenUtility;


@Service
public class CollaboratorServiceImplemented implements ICollaboratorService
{
	
	@Autowired
	CollaboratorRepository cRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	TokenUtility tokenUtility;

	@Autowired
	JavaMailSender javaMailSender;
	
	
	@Autowired
	NoteRepository noteRepository;
	
	
	
	@Override
	public Response Collaborate(CollaboratorDto collaboratorDto , String token )
	{
		System.out.println("1");
		CollaboratorModel collaborator = mapper.map(collaboratorDto , CollaboratorModel.class);
		System.out.println("2");
		String useremail = tokenUtility.decodeToken(token);
		System.out.println("3");
		if(useremail == null)
		{
			System.out.println("4");
			throw new InvalidUserException(LabelUtility.USER_NOT_FOUND);
		}
		System.out.println("5");
		collaborator.setSender_mail(useremail);
		System.out.println("6");
		
		NoteModel note = noteRepository.findById(collaboratorDto.getNoteID()).
				orElseThrow(() ->new IdNotFoundException(NoteUtility.NOTE_NOT_FOUND)) ;
		
		
		javaMailSender.send(CollaboratorMessageUtility.sendMail(collaborator.getSender_mail(), collaboratorDto.getReceiver_mail(), "this is the note"+note ));
		
		System.out.println("7");
		cRepository.save(collaborator);
		System.out.println("8");
		
		
		return new Response(CollaboratorUtility.HTTP_STATUS_OK ,CollaboratorUtility.COLLABORATOR_ADDED, null);
	}
	
	
	
	
}
