package com.bridgelabz.spring.fundoo.collaborator.cservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.spring.fundoo.collaborator.cdto.CollaboratorDto;
import com.bridgelabz.spring.fundoo.collaborator.cmodel.CollaboratorModel;
import com.bridgelabz.spring.fundoo.collaborator.crepository.CollaboratorRepository;
import com.bridgelabz.spring.fundoo.collaborator.cutility.CollaboratorMessageUtility;
import com.bridgelabz.spring.fundoo.label.lutility.LabelUtility;
import com.bridgelabz.spring.fundoo.notes.nexception.custom.IdNotFoundException;
import com.bridgelabz.spring.fundoo.notes.nexception.custom.InputException;
import com.bridgelabz.spring.fundoo.notes.nexception.custom.InvalidUserException;
import com.bridgelabz.spring.fundoo.notes.nmodel.NoteModel;
import com.bridgelabz.spring.fundoo.notes.nrepository.NoteRepository;
import com.bridgelabz.spring.fundoo.notes.nutitlity.NoteUtility;
import com.bridgelabz.spring.fundoo.user.response.Response;
import com.bridgelabz.spring.fundoo.user.utility.TokenUtility;

@Service
@PropertySource("message.properties")
public class CollaboratorServiceImplemented implements ICollaboratorService {

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

	@Autowired
	Environment environment;

	// --------------------------------------------------------------------------------------------//

	// 1 --> service implemented to collaborate a note.
	@Override
	public Response Collaborate(CollaboratorDto collaboratorDto, String token) {
		if (collaboratorDto.getNoteID() == 0 || collaboratorDto.getReceiver_mail().isEmpty()
				|| (collaboratorDto.getNoteID() == 0 && collaboratorDto.getReceiver_mail().isEmpty())) {
			throw new InputException(NoteUtility.INPUT_NOT_FOUND);
		}

		CollaboratorModel collaborator = mapper.map(collaboratorDto, CollaboratorModel.class);

		String useremail = tokenUtility.decodeToken(token);

		if (useremail == null) {
			throw new InvalidUserException(LabelUtility.USER_NOT_FOUND);
		}

		collaborator.setSender_mail(useremail);

		NoteModel note = noteRepository.findById(collaboratorDto.getNoteID())
				.orElseThrow(() -> new IdNotFoundException(NoteUtility.NOTE_NOT_FOUND));

		javaMailSender.send(CollaboratorMessageUtility.sendMail(collaborator.getSender_mail(),
				collaboratorDto.getReceiver_mail(), "this is the note" + note));

		cRepository.save(collaborator);

		return new Response(Integer.parseInt(environment.getProperty("HTTP_STATUS_OK")),
				environment.getProperty("LABEL_CREATED"), environment.getProperty("SUCESS"));

	}

	// --------------------------------------------------------------------------------------------//

}
