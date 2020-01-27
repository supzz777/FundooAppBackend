package com.bridgelabz.spring.fundoo.notes.nservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bridgelabz.spring.fundoo.elasticsearch.ElasticSearchImplemented;
import com.bridgelabz.spring.fundoo.notes.ndto.NoteDto;
import com.bridgelabz.spring.fundoo.notes.nexception.custom.IdNotFoundException;
import com.bridgelabz.spring.fundoo.notes.nexception.custom.InputException;
import com.bridgelabz.spring.fundoo.notes.nexception.custom.InvalidUserException;
import com.bridgelabz.spring.fundoo.notes.nmodel.NoteModel;
import com.bridgelabz.spring.fundoo.notes.nrepository.NoteRepository;
import com.bridgelabz.spring.fundoo.notes.nutitlity.NoteUtility;
import com.bridgelabz.spring.fundoo.user.model.User;
import com.bridgelabz.spring.fundoo.user.repository.UserRepository;
import com.bridgelabz.spring.fundoo.user.response.Response;
import com.bridgelabz.spring.fundoo.user.utility.TokenUtility;

@Service
@PropertySource("message.properties")
public class NoteServiceImplemented implements INoteService {
	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	TokenUtility tokenUtility;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	Environment environment;
	@Autowired
	ElasticSearchImplemented elasticSerach;

	//--------------------------------------------------------------------------------------------------//
	
	//1 --> service implemented to create a note.
	@Override
	public Response createNote(@Valid NoteDto noteDto, String token) throws Exception 
	{	
		
		if( noteDto.getTitle().isEmpty() || noteDto.getDiscription().isEmpty() || ( noteDto.getTitle().isEmpty() && noteDto.getDiscription().isEmpty() ) )
		{
			throw new InputException(NoteUtility.INPUT_NOT_FOUND);
		}
	
		NoteModel note = mapper.map(noteDto, NoteModel.class);
	
		String useremail = tokenUtility.decodeToken(token);
	
		User user = repository.findByEmail(useremail);
	
		if (user == null) {
			
			throw new InvalidUserException(NoteUtility.USER_NOT_FOUND);
		}
		
		
		note.setUser(user);
		noteRepository.save(note);
		elasticSerach.createNote(note);
	
		return new Response( Integer.parseInt( environment.getProperty("HTTP_STATUS_OK") ) , 
				 environment.getProperty("NOTE_CREATED") ,  environment.getProperty("SUCESS") );
	}

	// -----------------------------------------------------------------------------------------------//
	
	
	//2 --> service implemented to update a note.
	@Override
	public Response updateNote(@Valid int id, NoteDto noteDto, String token) 
	{
		if( noteDto.getTitle().isEmpty() || noteDto.getDiscription().isEmpty() || ( noteDto.getTitle().isEmpty() && noteDto.getDiscription().isEmpty() ) )
		{
			throw new InputException(NoteUtility.INPUT_NOT_FOUND);
		}
		
		NoteModel note =mapper.map(noteDto, NoteModel.class);

		String useremail = tokenUtility.decodeToken(token);
		User user = repository.findByEmail(useremail);

		if (user == null) {

			System.out.println("error exception thrown.");
			throw new InvalidUserException(NoteUtility.USER_NOT_FOUND);
		}

		note = noteRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException(NoteUtility.NOTE_NOT_FOUND));
		/*
		 * //NoteModel noteupdate = nrepository.findById(id);
		 * 
		 * if(noteupdate == null) { throw new
		 * IdNotFoundException(NoteUtility.NOTE_NOT_FOUND); }
		 */

		// noteupdate.setUser(user); //not needed.
		note.setTitle(noteDto.getTitle());
		note.setDiscription(noteDto.getDiscription());

		noteRepository.save(note);
		
		try {
			//elastic search implementation saved 
			elasticSerach.updateNote(note);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return new Response( Integer.parseInt( environment.getProperty("HTTP_STATUS_OK") ) , 
				 environment.getProperty("NOTE_UPDATED") ,  environment.getProperty("SUCESS") );

	}

	// -----------------------------------------------------------------------------------------------//
	
	//3 --> service implemented to delete a note.
	@Override
	public Response deleteNote(int id, String token) {

		String useremail = tokenUtility.decodeToken(token);
		User user = repository.findByEmail(useremail);

		if (user == null) {

			System.out.println("error exception thrown.");
			throw new InvalidUserException(NoteUtility.USER_NOT_FOUND);
		}

		NoteModel note = noteRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException(NoteUtility.NOTE_NOT_FOUND));

		// NoteModel note = nrepository.findById(id);
		note.setUser(user);

		noteRepository.delete(note);
		
		try {
			elasticSerach.deleteNote(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Response( Integer.parseInt( environment.getProperty("HTTP_STATUS_OK") ) , 
				 environment.getProperty("NOTE_DELETED") ,  environment.getProperty("SUCESS") );
	}

	// ----------------------------------------------------------------------------------------------------//
	
	//4 --> service implemented to show all the notes.
	@Override
	public List<NoteModel> showAllNotes(String token)
	{
		String useremail = tokenUtility.decodeToken(token);
		User user = repository.findByEmail(useremail);

		if (user == null) {

			System.out.println("error exception thrown.");
			throw new InvalidUserException(NoteUtility.USER_NOT_FOUND);
		}

		List<NoteModel> note = noteRepository.findAll().stream().filter(data -> data.getUser().getId() == user.getId())
				.collect(Collectors.toList());

		return note; // show all user details in mysql.
	}

	// -----------------------------------------------------------------------------------------------//
	
	//5 --> service implemented to show all the notes of a particular user.
	@Override
	public Object showAllNotesOfAllUserz() {

		List<NoteModel> note = noteRepository.findAll().stream().collect(Collectors.toList());// show notes of all userz

		return note;
	}

	// -----------------------------------------------------------------------------------------------//
	
	//6 --> service implemented to sort all the notes by title.
	@Override
	public List<NoteModel> SortNotesByTitle(String token)
	{
		String useremail = tokenUtility.decodeToken(token);
		User user = repository.findByEmail(useremail);

		if (user == null) {

			System.out.println("error exception thrown.");
			throw new InvalidUserException(NoteUtility.USER_NOT_FOUND);
		}

		List<NoteModel> note = noteRepository.findAll().stream().filter(data -> data.getUser().getId() == user.getId())
				.collect(Collectors.toList());

		note = note.stream().sorted((note1, note2) -> note1.getTitle().compareTo(note2.getTitle()))
				.collect(Collectors.toList());
		
		
		// Arrays.parallelSort(note);

		return note; // show all user details in mysql.
	}

	// ------------------------------------------------------------------------------------------------------//
	
	//7 --> service implemented to sort all the notes by Description.
	@Override
	public List<NoteModel> sortByDescription(String token)
	{
		String useremail = tokenUtility.decodeToken(token);
		User user = repository.findByEmail(useremail);

		if (user == null) {

			System.out.println("error exception thrown.");
			throw new InvalidUserException(NoteUtility.USER_NOT_FOUND);
		}

		List<NoteModel> note = noteRepository.findAll().stream().filter(data -> data.getUser().getId() == user.getId())
				.collect(Collectors.toList());

		return note = note.stream().sorted((note1, note2) -> note1.getDiscription().compareTo(note2.getDiscription()))
				.collect(Collectors.toList());

	}

	// --------------------------------------------------------------------------------------------------------//
	
	//8 --> service implemented to sort all the notes by Date.
	@Override
	public List<NoteModel> sortByDate(String token) 
	{
		String useremail = tokenUtility.decodeToken(token);
		User user = repository.findByEmail(useremail);

		if (user == null) {

			System.out.println("error exception thrown.");
			throw new InvalidUserException(NoteUtility.USER_NOT_FOUND);
		}

		List<NoteModel> note = noteRepository.findAll().stream().filter(data -> data.getUser().getId() == user.getId())
				.collect(Collectors.toList());

		note = note.stream()
				.sorted((note1, note2) -> note1.getNoteregistrationDate().compareTo(note2.getNoteregistrationDate()))
				.collect(Collectors.toList());

		return note;

	}

	// ----------------------------------------------------------------------------------------------------------//
	
	//9 --> service implemented to pin or unpin  the note/notes
	@Override
	public Response notePin(int id, String token) 
	{
		

		String useremail = tokenUtility.decodeToken(token);
		User user = repository.findByEmail(useremail);

		if (user == null) {

			System.out.println("error exception thrown.");
			throw new InvalidUserException(NoteUtility.USER_NOT_FOUND);
		}
		
		NoteModel note = noteRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException(NoteUtility.NOTE_NOT_FOUND));
		
		
		if (note.getPin())
		{
			note.setPin(false);
			noteRepository.save(note);
			return new Response( Integer.parseInt( environment.getProperty("HTTP_STATUS_OK") ) , 
					 environment.getProperty("NOTE_PIN") ,  environment.getProperty("SUCESS") );
		} else {
			note.setPin(true);
			noteRepository.save(note);
			return new Response( Integer.parseInt( environment.getProperty("HTTP_STATUS_OK") ) , 
					 environment.getProperty("NOTE_UNPIN") ,  environment.getProperty("SUCESS") );
		}
	}
	
	//---------------------------------------------------------------------------------------------//
	
	//10 --> service implemented to archive or unarchive  the note/notes
	@Override
	public Response noteArchive(int id, String token) 
	{
		

		String useremail = tokenUtility.decodeToken(token);
		User user = repository.findByEmail(useremail);

		if (user == null) {

			System.out.println("error exception thrown.");
			throw new InvalidUserException(NoteUtility.USER_NOT_FOUND);
		}
		
		NoteModel note = noteRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException(NoteUtility.NOTE_NOT_FOUND));
		
		
		if (note.getArchive())
		{
			note.setArchive(false);
			noteRepository.save(note);
			return new Response( Integer.parseInt( environment.getProperty("HTTP_STATUS_OK") ) , 
					 environment.getProperty("NOTE_ARCHIVE") ,  environment.getProperty("SUCESS") );
		} else {
			note.setArchive(true);
			noteRepository.save(note);
			return new Response( Integer.parseInt( environment.getProperty("HTTP_STATUS_OK") ) , 
					 environment.getProperty("NOTE_UNARCHIVE") ,  environment.getProperty("SUCESS") );
		}
	}
	
	//---------------------------------------------------------------------------------------------//
	
	//11 --> service implemented to trash or untrash  the note/notes
	@Override
	public Response noteTrash(int id, String token) 
	{
		

		String useremail = tokenUtility.decodeToken(token);
		User user = repository.findByEmail(useremail);

		if (user == null) {

			System.out.println("error exception thrown.");
			throw new InvalidUserException(NoteUtility.USER_NOT_FOUND);
		}
		
		NoteModel note = noteRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException(NoteUtility.NOTE_NOT_FOUND));
		
		
		if (note.getTrash())
		{
			note.setTrash(false);
			noteRepository.save(note);
			return new Response( Integer.parseInt( environment.getProperty("HTTP_STATUS_OK") ) , 
					 environment.getProperty("NOTE_TRASH") ,  environment.getProperty("SUCESS") );
		} else {
			note.setTrash(true);
			return new Response( Integer.parseInt( environment.getProperty("HTTP_STATUS_OK") ) , 
					 environment.getProperty("NOTE_UNTRASH") ,  environment.getProperty("SUCESS") );
		}
	}
	
	//---------------------------------------------------------------------------------------------//
	
	
}






