package com.bridgelabz.spring.fundoo.notes.nservice;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.bridgelabz.spring.fundoo.notes.ndto.NoteDto;
import com.bridgelabz.spring.fundoo.notes.nmodel.NoteModel;
import com.bridgelabz.spring.fundoo.user.response.Response;

@Service
public interface INoteService 
{
	
	public Response createNote(@Valid NoteDto noteDto , String token ) throws Exception ;
	
	public Response updateNote( @Valid int id , NoteDto notedto, String token);
	
	public Response deleteNote(int id, String token);
	
	public List<NoteModel> showAllNotes(String token);
	
	public Object showAllNotesOfAllUserz() ;
	
	public List<NoteModel> SortNotesByTitle(String token);
	
	public List<NoteModel> sortByDescription(String token) ;
	
	public List<NoteModel> sortByDate(String token);
	
	public Response notePin(int id, String token) ;
	
	public Response noteArchive(int id, String token) ;
	
	public Response noteTrash(int id, String token) ;
	
}
