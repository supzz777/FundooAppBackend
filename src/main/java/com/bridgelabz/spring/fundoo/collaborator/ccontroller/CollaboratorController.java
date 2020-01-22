package com.bridgelabz.spring.fundoo.collaborator.ccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.spring.fundoo.collaborator.cdto.CollaboratorDto;
import com.bridgelabz.spring.fundoo.collaborator.cservice.CollaboratorServiceImplemented;
import com.bridgelabz.spring.fundoo.user.response.Response;

@RestController
@RequestMapping(value ="/fundoo")
public class CollaboratorController
{
	
	@Autowired
	CollaboratorServiceImplemented collaboratorServiceImplemented;
	
	@GetMapping("/demozzz")
	public String demoz()
	{
		return "hello girl you always rock.";
	}
	
	//--------------------------------------------------------------------------------------------------------//
	
	 @PostMapping("/collaborate")
	 
     public ResponseEntity<Response> Collaborate(@RequestBody CollaboratorDto collaboratorDto, @RequestHeader String token)
     {
		 
		return new ResponseEntity<Response>(collaboratorServiceImplemented.Collaborate(collaboratorDto, token ), HttpStatus.OK); // give

		 
     }
	
	//--------------------------------------------------------------------------------------------------------//

     
}
