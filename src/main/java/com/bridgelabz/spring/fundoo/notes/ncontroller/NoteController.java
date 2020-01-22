package com.bridgelabz.spring.fundoo.notes.ncontroller;

import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.spring.fundoo.notes.ndto.NoteDto;
import com.bridgelabz.spring.fundoo.notes.nservice.NoteServiceImplemented;
import com.bridgelabz.spring.fundoo.user.response.Response;

@RestController
@RequestMapping(value = "/fundoo")
public class NoteController {
	@Autowired
	NoteServiceImplemented noteServiceImplemented;
	
	// ---------------------------------------------------------------------------------------------//
	@GetMapping("/demoz")
	public String demoz() {
		return "hello supzz";
	}

	// ---------------------------------------------------------------------------------------------//
	
	//1 --> mapping for creating the note
	@PostMapping("/note/create")
	public ResponseEntity<Response> createNote(@Valid @RequestBody NoteDto noteDto, @RequestHeader String token) {

		return new ResponseEntity<Response>(noteServiceImplemented.createNote(noteDto, token), HttpStatus.OK); // give

	}

	// ---------------------------------------------------------------------------------------------//

	/*
	 * @PutMapping("/update") public ResponseEntity<Response>
	 * updateNote( @Valid @RequestBody UpdateDto updateDto ,@RequestHeader String
	 * token) {
	 * 
	 * return new
	 * ResponseEntity<Response>(noteServiceImplemented.updateNote(updateDto, token),
	 * HttpStatus.OK); // give
	 * 
	 * }
	 */
	
	//2 --> mapping for updating the note
	@PutMapping("/note/update/{id}")
	public ResponseEntity<Response> updateNote(@Valid @PathVariable("id") int id, @RequestBody NoteDto noteDto,
			@RequestHeader String token) {

		return new ResponseEntity<Response>(noteServiceImplemented.updateNote(id, noteDto, token), HttpStatus.OK); // give

	}

	// ---------------------------------------------------------------------------------------------------------//
	
	//3 --> mapping for deleting the note
	@DeleteMapping("/note/{id}")
	public HttpStatus deleteNote(@PathVariable("id") int id, @RequestHeader String token) {
		noteServiceImplemented.deleteNote(id, token);
		return HttpStatus.ACCEPTED;
	}

	// ------------------------------------------------------------------------------------------------//
	
	//4 --> mapping for showing all the notes present inside the database.
	@GetMapping("/note/showNotes")
	public Response showAllNotes(@RequestHeader String token) {

		return new Response(200, "Show all details", noteServiceImplemented.showAllNotes(token)); // give

	}

	// ------------------------------------------------------------------------------------------------//
	
	//4 --> mapping for showing all the notes of a particular user present inside the database.
	@GetMapping("/note/showAllNotes")
	public Response showAllNotesOfAllUserz() {

		return new Response(200, "Show all details", noteServiceImplemented.showAllNotesOfAllUserz()); // give

	}

	// ------------------------------------------------------------------------------------------------//
	
	//5 --> mapping for showing all the notes in the sorted form by title.
	@GetMapping("/note/sortNotesByTitle")
	public Response SortNotesByTitle(@RequestHeader String token) {

		return new Response(200, "Show all details", noteServiceImplemented.SortNotesByTitle(token)); // give

	}

	// -------------------------------------------------------------------------------------------------------------//
	
	//6 --> mapping for showing all the notes in the sorted form by Description.
	@GetMapping("/note/sortNotesByDescription")
	public Response sortByDescription(@RequestHeader String token) {

		return new Response(200, "Show all details", noteServiceImplemented.sortByDescription(token)); // give

	}

	// -------------------------------------------------------------------------------------------------------------//
	
	//6 --> mapping for showing all the notes in the sorted form by date.
	@GetMapping("/note/sortNotesByDate")
	public Response sortByDate(@RequestHeader String token) {

		return new Response(200, "Show all details", noteServiceImplemented.sortByDate(token)); // give

	}

	// -------------------------------------------------------------------------------------------------------------//

}
