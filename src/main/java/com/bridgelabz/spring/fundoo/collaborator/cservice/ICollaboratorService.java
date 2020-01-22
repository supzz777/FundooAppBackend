package com.bridgelabz.spring.fundoo.collaborator.cservice;

import com.bridgelabz.spring.fundoo.collaborator.cdto.CollaboratorDto;
import com.bridgelabz.spring.fundoo.user.response.Response;

public interface ICollaboratorService 
{
	
	public Response Collaborate(CollaboratorDto collaboratorDto, String token );
}
