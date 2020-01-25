package com.bridgelabz.spring.fundoo.elasticsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.spring.fundoo.notes.nmodel.NoteModel;
import com.bridgelabz.spring.fundoo.notes.nrepository.NoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElasticSearchImplemented 
{
	
	 @Autowired
    private RestHighLevelClient client;

	 @Autowired
    private ObjectMapper objectMapper;
	 
	 @Autowired
	 NoteRepository noteRepository;
    
    
    @Autowired
    public ElasticSearchImplemented(RestHighLevelClient client, ObjectMapper objectMapper)
    {
        this.client = client;
        this.objectMapper = objectMapper;
    }

     static String INDEX="note_database"; //database
     static String TYPE="note_details"; //table

     //----------------------------------------------------------------------------------------------//
     
	public String createNote(NoteModel note) throws Exception 
	{
	
	    System.out.println("in elastic");
	    
	    @SuppressWarnings("unchecked")
		Map<String, Object> documentMapper = 
	 objectMapper.convertValue(note, Map.class);
	
	    IndexRequest indexRequest = new IndexRequest(INDEX, TYPE,
	String.valueOf(note.getId()))
	    .source(documentMapper); //.index(INDEX).type(TYPE);
	
	    System.out.println("****"+indexRequest);
	    System.out.println("after request");
	    IndexResponse indexResponse = client.index(indexRequest,
	RequestOptions.DEFAULT);
	
	    System.out.println("****"+indexResponse);
	    System.out.println("note is :"+indexResponse.getResult().name());
	    return indexResponse.getResult().name();
	
	}
	
	//----------------------------------------------------------------------------------------------------//
	
	
	   public String updateNote(NoteModel note) throws Exception {

		   NoteModel  resultDocument = noteRepository.findById(note.getId()).orElse(null);
		   
		   
	        Map<String, Object> documentMapper =
	objectMapper.convertValue(note, Map.class);

	        UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE,
	String.valueOf(resultDocument.getId()));

	        updateRequest.doc(documentMapper);

	        UpdateResponse updateResponse = client.update(updateRequest,
	RequestOptions.DEFAULT);

	        return updateResponse.getResult().name();

	    }
}