package com.bridgelabz.spring.fundoo.collaborator.crepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.spring.fundoo.collaborator.cmodel.CollaboratorModel;

public interface CollaboratorRepository  extends JpaRepository <CollaboratorModel, Object>
{
	
	
}
