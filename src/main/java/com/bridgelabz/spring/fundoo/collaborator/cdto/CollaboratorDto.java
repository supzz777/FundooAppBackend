package com.bridgelabz.spring.fundoo.collaborator.cdto;

import javax.validation.constraints.NotNull;

public class CollaboratorDto
{
	
	@NotNull
	private String receiver_mail;
	
	@NotNull
	private int noteID;
	

	public CollaboratorDto()
	{
		
	}
			
	public CollaboratorDto(@NotNull String receiver_mail, @NotNull int noteID) {
		super();
		this.receiver_mail = receiver_mail;
		
		this.noteID = noteID;
	}

	
	public String getReceiver_mail() {
		return receiver_mail;
	}

	public void setReceiver_mail(String receiver_mail) {
		this.receiver_mail = receiver_mail;
	}

	

	public int getNoteID() {
		return noteID;
	}

	public void setNoteID(int noteID) {
		this.noteID = noteID;
	}

	@Override
	public String toString() {
		return "CollaboratorDto [receiver_mail=" + receiver_mail +  ", noteID=" + noteID
				+ "]";
	}
	
	
	

}
