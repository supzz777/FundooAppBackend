package com.bridgelabz.spring.fundoo.elasticsearch;

import com.bridgelabz.spring.fundoo.notes.nmodel.NoteModel;

public interface IElasticSearchInterface {
	
	public String createNote(NoteModel note)  throws Exception;
	
	 public String updateNote(NoteModel note) throws Exception;
	 
	 public String deleteNote(int noteId) throws Exception;

}
