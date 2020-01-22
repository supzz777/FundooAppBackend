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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.spring.fundoo.notes.ndto.NoteDto;
import com.bridgelabz.spring.fundoo.notes.nmodel.NoteModel;
import com.bridgelabz.spring.fundoo.notes.nservice.NoteServiceImplemented;
import com.bridgelabz.spring.fundoo.user.dto.RegistrationDto;
import com.bridgelabz.spring.fundoo.user.response.Response;
@RestController
@RequestMapping(value ="/fundoo")
public class NoteController 
{	
	@Autowired
	NoteServiceImplemented noteServiceImplemented;


	@GetMapping("/demoz")
	public String demoz()
	{
		return "hello supzz";
	}
	
	//---------------------------------------------------------------------------------------------//
	@PostMapping("/note/create")
	public ResponseEntity<Response> createNote( @Valid @RequestBody NoteDto noteDto ,@RequestHeader String token) {

		return new ResponseEntity<Response>(noteServiceImplemented.createNote(noteDto, token), HttpStatus.OK); // give
		
	}
	
	
	
	//---------------------------------------------------------------------------------------------//

	/*@PutMapping("/update")
	public ResponseEntity<Response> updateNote( @Valid @RequestBody UpdateDto updateDto ,@RequestHeader String token) {

		return new ResponseEntity<Response>(noteServiceImplemented.updateNote(updateDto, token), HttpStatus.OK); // give
		
	}
	*/
	@PutMapping("/note/update/{id}")
	public ResponseEntity<Response> updateNote( @Valid @PathVariable("id") int id , @RequestBody NoteDto noteDto ,@RequestHeader String token) {

		return new ResponseEntity<Response>(noteServiceImplemented.updateNote(id , noteDto, token), HttpStatus.OK); // give
		
	}
	
	
	
	//---------------------------------------------------------------------------------------------------------//
	
	
  	@DeleteMapping("/note/{id}")
    public HttpStatus deleteNote(@PathVariable("id") int id , @RequestHeader String token) 
    {                                             
  		noteServiceImplemented.deleteNote(id ,token);
        return HttpStatus.ACCEPTED;
    }
	
  	
  	//------------------------------------------------------------------------------------------------//
  	
  	@GetMapping("/note/showNotes")
	public Response showAllNotes( @RequestHeader String token) {

		return new Response( 200, "Show all details",noteServiceImplemented.showAllNotes( token)); // give
		
	}
  	
  	//------------------------------------------------------------------------------------------------//

  	@GetMapping("/note/showAllNotes")
	public Response showAllNotesOfAllUserz() {

		return new Response( 200, "Show all details",noteServiceImplemented.showAllNotesOfAllUserz()); // give
		
	}
  	
  //------------------------------------------------------------------------------------------------//

  	@GetMapping("/note/sortNotesByTitle")
	public Response SortNotesByTitle(@RequestHeader String token) {

		return new Response( 200, "Show all details",noteServiceImplemented.SortNotesByTitle(token)); // give
		
	}
  	
  	
  //-------------------------------------------------------------------------------------------------------------//
  	
  	@GetMapping("/note/sortNotesByDescription")
	public Response sortByDescription(@RequestHeader String token) {

		return new Response( 200, "Show all details",noteServiceImplemented.sortByDescription(token)); // give
		
	}
  	
  //-------------------------------------------------------------------------------------------------------------//	
  	
	@GetMapping("/note/sortNotesByDate")
	public Response sortByDate(@RequestHeader String token) {

		return new Response( 200, "Show all details",noteServiceImplemented.sortByDate(token)); // give
		
	}
  	
  //-------------------------------------------------------------------------------------------------------------//	
  	
}
