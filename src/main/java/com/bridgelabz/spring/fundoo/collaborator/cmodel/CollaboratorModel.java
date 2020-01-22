package com.bridgelabz.spring.fundoo.collaborator.cmodel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class CollaboratorModel implements Serializable
{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int collaborator_id;;
	@NotNull
	private String receiver_mail;
	@NotNull
	private String sender_mail;
	@NotNull
	private int noteID;
	
	public CollaboratorModel()
	{
		
	}

	public CollaboratorModel(int collaborator_id, String receiver_mail, String sender_mail, int noteID) {
		super();
		this.collaborator_id = collaborator_id;
		this.receiver_mail = receiver_mail;
		this.sender_mail = sender_mail;
		this.noteID = noteID;
	}

	public int getCollaborator_id() {
		return collaborator_id;
	}

	public void setCollaborator_id(int collaborator_id) {
		this.collaborator_id = collaborator_id;
	}

	public String getReceiver_mail() {
		return receiver_mail;
	}

	public void setReceiver_mail(String receiver_mail) {
		this.receiver_mail = receiver_mail;
	}

	public String getSender_mail() {
		return sender_mail;
	}

	public void setSender_mail(String sender_mail) {
		this.sender_mail = sender_mail;
	}

	public int getNoteID() {
		return noteID;
	}

	public void setNoteID(int noteID) {
		this.noteID = noteID;
	}

	
	@Override
	public String toString() {
		return "CollaboratorModel [collaborator_id=" + collaborator_id + ", receiver_mail=" + receiver_mail
				+ ", sender_mail=" + sender_mail + ", noteID=" + noteID + "]";
	}
	
	
	
	
}
