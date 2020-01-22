package com.bridgelabz.spring.fundoo.notes.ndto;

import javax.validation.constraints.NotNull;

public class NoteDto 
{
	@NotNull
	private String title;
	@NotNull
	private String discription;
	
	
	
	public NoteDto(@NotNull String title, @NotNull String discription) {
		super();
		this.title = title;
		this.discription = discription;
	}
	
	
	//getters and setters
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	
	@Override
	public String toString() {
		return "NoteDto [title=" + title + ", discription=" + discription + "]";
	}
}
