package com.bridgelabz.spring.fundoo.notes.ncontroller;


import java.sql.Date;

import javax.validation.Valid;

import org.aspectj.weaver.Dump.INode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.spring.fundoo.elasticsearch.ElasticSearchImplemented;
import com.bridgelabz.spring.fundoo.notes.ndto.NoteDto;
import com.bridgelabz.spring.fundoo.notes.nservice.INoteService;
import com.bridgelabz.spring.fundoo.notes.nservice.NoteServiceImplemented;
import com.bridgelabz.spring.fundoo.user.response.Response;
@RestController
@RequestMapping(value = "/fundoo")
public class NoteController {
	
	
	@Autowired
	INoteService noteServiceImplemented;
	
	@Autowired
	ElasticSearchImplemented  elasticSearchImplemented;
	
	// ---------------------------------------------------------------------------------------------//
	@GetMapping("/demoz")
	public String demoz() {
		return "hello supzz";
	}

	// ---------------------------------------------------------------------------------------------//
	
	//1 --> mapping for creating the note
	@PostMapping("/note/create")
	public ResponseEntity<Response> createNote(@Valid @RequestBody NoteDto noteDto, @RequestHeader String token) throws Exception {

		return new ResponseEntity<Response>(noteServiceImplemented.createNote(noteDto, token), HttpStatus.OK); 

	}
	// ---------------------------------------------------------------------------------------------//

	
	//2 --> mapping for updating the note
	@PutMapping("/note/update/{id}")
	public ResponseEntity<Response> updateNote(@Valid @PathVariable("id") int id, @RequestBody NoteDto noteDto,
			@RequestHeader String token) {

		return new ResponseEntity<Response>(noteServiceImplemented.updateNote(id, noteDto, token), HttpStatus.OK); 

	}

	// ---------------------------------------------------------------------------------------------------------//
	
	//3 --> mapping for deleting the note
	@DeleteMapping("/note/{id}")
	public HttpStatus deleteNote(@PathVariable("id") int id, @RequestHeader String token) throws Exception {
		noteServiceImplemented.deleteNote(id, token);
		return HttpStatus.ACCEPTED;
	}

	// ------------------------------------------------------------------------------------------------//
	
	//4 --> mapping for showing all the notes present inside the database.
	@GetMapping("/note/showNotes")
	public Response showAllNotes(@RequestHeader String token) {

		return new Response(200, "Show all details", noteServiceImplemented.showAllNotes(token)); 

	}

	// ------------------------------------------------------------------------------------------------//
	
	//4 --> mapping for showing all the notes of a particular user present inside the database.
	@GetMapping("/note/showAllNotes")
	public Response showAllNotesOfAllUserz() {

		return new Response(200, "Show all details", noteServiceImplemented.showAllNotesOfAllUserz()); 

	}

	// ------------------------------------------------------------------------------------------------//
	
	//5 --> mapping for showing all the notes in the sorted form by title.
	@GetMapping("/note/sortNotesByTitle")
	public Response SortNotesByTitle(@RequestHeader String token) {

		return new Response(200, "Show all details", noteServiceImplemented.SortNotesByTitle(token));

	}

	// -------------------------------------------------------------------------------------------------------------//
	
	//6 --> mapping for showing all the notes in the sorted form by Description.
	@GetMapping("/note/sortNotesByDescription")
	public Response sortByDescription(@RequestHeader String token) {

		return new Response(200, "Show all details", noteServiceImplemented.sortByDescription(token));

	}

	// -------------------------------------------------------------------------------------------------------------//
	
	//7 --> mapping for showing all the notes in the sorted form by date.
	@GetMapping("/note/sortNotesByDate")
	public Response sortByDate(@RequestHeader String token) {

		return new Response(200, "Show all details", noteServiceImplemented.sortByDate(token)); 

	}

	// -------------------------------------------------------------------------------------------------------------//
	
	//8 --> mapping for pinning and un pinning all the notes .
	@PutMapping("/note/pinUnpin/{id}")
	public ResponseEntity<Response> notePin(@Valid @PathVariable("id") int id,
			@RequestHeader String token) {

		return new ResponseEntity<Response>(noteServiceImplemented.notePin(id,token), HttpStatus.OK); 

	}
	
	//---------------------------------------------------------------------------------------------------//
	
	//9 --> mapping for archiving and un archiving all the notes .
	@PutMapping("/note/archiveUnarchive/{id}")
	public ResponseEntity<Response> noteArchive(@Valid @PathVariable("id") int id,
			@RequestHeader String token) {

		return new ResponseEntity<Response>(noteServiceImplemented.noteArchive(id,token), HttpStatus.OK); 

	}
	
	//---------------------------------------------------------------------------------------------------//
	
	//10 --> mapping for trashing and un trashing all the notes .
	@PutMapping("/note/trashUntrash/{id}")
	public ResponseEntity<Response> noteTrash(@Valid @PathVariable("id") int id,
			@RequestHeader String token) {

		return new ResponseEntity<Response>(noteServiceImplemented.noteTrash(id,token), HttpStatus.OK);

	}
	
	//---------------------------------------------------------------------------------------------------//
	
	//11 --> mapping for adding the remainder the note/notes .
	@PutMapping("/note/addRemainder/{id}")
	public ResponseEntity<Response> addRemainder(@Valid @PathVariable("id") int id,
			@RequestHeader String token , @RequestParam Date date) {

		return new ResponseEntity<Response>(noteServiceImplemented.addRemainder(id,token,date), HttpStatus.OK); 

	}
	
	//------------------------------------------------------------------------------------------------------------//
	
	//12 --> mapping for updating the remainder the note/notes .
	@PutMapping("/note/updateRemainder/{id}")
	public ResponseEntity<Response> updateRemainder(@Valid @PathVariable("id") int id,
			@RequestHeader String token  , @RequestParam Date date ) {

		return new ResponseEntity<Response>(noteServiceImplemented.updateRemainder(id,token ,date), HttpStatus.OK); 

	}
	
	//------------------------------------------------------------------------------------------------------------//
	
	//13 --> mapping for deleting the remainder the note/notes .
	@PutMapping("/note/deleteRemainder/{id}")
	public ResponseEntity<Response> deleteRemainder(@Valid @PathVariable("id") int id,
			@RequestHeader String token) {

		return new ResponseEntity<Response>(noteServiceImplemented.deleteRemainder(id,token), HttpStatus.OK); 

	}
	
	//------------------------------------------------------------------------------------------------------------//
}
