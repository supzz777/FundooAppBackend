package com.bridgelabz.spring.fundoo.label.lcontroller;

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

import com.bridgelabz.spring.fundoo.label.ldto.LabelDto;
import com.bridgelabz.spring.fundoo.label.lservice.LabelServiceImplemented;
import com.bridgelabz.spring.fundoo.notes.ndto.NoteDto;
import com.bridgelabz.spring.fundoo.user.response.Response;

@RestController
@RequestMapping(value ="/fundoo")
public class LabelController 
{
	@Autowired
	LabelServiceImplemented labelServiceImplemented;
	
	@GetMapping("/demozz")
	public String demoz()
	{
		return "hello supzz rock";
	}
	
	
	//---------------------------------------------------------------------------------------------//
	
		@PostMapping("/label/create")
		public ResponseEntity<Response> createLabel( @Valid @RequestBody LabelDto labelDto ,@RequestHeader String token) 
		{

			return new ResponseEntity<Response>(labelServiceImplemented.createLabel(labelDto, token), HttpStatus.OK); // give
			
		}
		
	//---------------------------------------------------------------------------------------------//	
		
		
		@PutMapping("/label/update/{id}")
		public ResponseEntity<Response> updateLabel( @Valid @PathVariable("id") int id , @RequestBody LabelDto labelDto ,@RequestHeader String token) {
		
			return new ResponseEntity<Response>(labelServiceImplemented.updateLabel(id , labelDto, token), HttpStatus.OK); // give
			
		}
		
		
		
	//---------------------------------------------------------------------------------------------------------//
		
		
		@DeleteMapping("/label/{id}")
	    public HttpStatus deleteLabel(@PathVariable("id") int id , @RequestHeader String token) 
	    {                                             
			labelServiceImplemented.deleteLabel(id ,token);
	        return HttpStatus.ACCEPTED;
	    }
		
		
	//---------------------------------------------------------------------------------------------------------//

		@GetMapping("/label/showNotes")
		public Response showAllLabels( @RequestHeader String token) {

			return new Response( 200, "Show all details",labelServiceImplemented.showAllLabels( token)); // give
			
		}
		
	//---------------------------------------------------------------------------------------------------------//
}
