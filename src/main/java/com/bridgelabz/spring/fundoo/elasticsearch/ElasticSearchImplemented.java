package com.bridgelabz.spring.fundoo.elasticsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.spring.fundoo.notes.nexception.custom.InvalidUserException;
import com.bridgelabz.spring.fundoo.notes.nmodel.NoteModel;
import com.bridgelabz.spring.fundoo.notes.nrepository.NoteRepository;
import com.bridgelabz.spring.fundoo.notes.nutitlity.NoteUtility;
import com.bridgelabz.spring.fundoo.user.model.User;
import com.bridgelabz.spring.fundoo.user.repository.UserRepository;
import com.bridgelabz.spring.fundoo.user.utility.TokenUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElasticSearchImplemented implements IElasticSearchInterface
{
	
	 @Autowired
    private RestHighLevelClient client;

	 @Autowired
	 private ObjectMapper objectMapper;
	 
	 @Autowired
	 NoteRepository noteRepository;
    
	 @Autowired
	 UserRepository userRepository;
	 
	 @Autowired
	 TokenUtility tokenUtility;
    
    @Autowired
    public ElasticSearchImplemented(RestHighLevelClient client, ObjectMapper objectMapper)
    {
        this.client = client;
        this.objectMapper = objectMapper;
    }

     static String INDEX="note_database"; //database
     static String TYPE="note_details"; //table

     //----------------------------------------------------------------------------------------------//
     
     @Override
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
	
     @Override
	   public String updateNote(NoteModel note) throws Exception {

		   NoteModel  resultDocument = noteRepository.findById(note.getId()).orElse(null);
		   
		   
	        @SuppressWarnings("unchecked")
			Map<String, Object> documentMapper =
	objectMapper.convertValue(note, Map.class);

	        UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE,
	String.valueOf(resultDocument.getId()));

	        updateRequest.doc(documentMapper);

	        UpdateResponse updateResponse = client.update(updateRequest,
	RequestOptions.DEFAULT);

	        return updateResponse.getResult().name();

	    }
	   
   //-------------------------------------------------------------------------------------------------------//
	   
     @Override
	   public String deleteNote(int noteId) throws Exception {

	        System.out.println("delete elastic");
	        
	        DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE,
	String.valueOf(noteId));//.index(INDEX).type(TYPE);
	        
	        DeleteResponse response = client.delete(deleteRequest,
	RequestOptions.DEFAULT);

	        return response.getResult().name();

	    }
     
     //----------------------------------------------------------------------------------------------------//
     
     public List<NoteModel> searchByTitle(String token ,String title) throws Exception 
     {
    	 
	    String useremail = tokenUtility.decodeToken(token);
		User user = userRepository.findByEmail(useremail);

 		if (user == null) {

 			System.out.println("error exception thrown.");
 			throw new InvalidUserException(NoteUtility.USER_NOT_FOUND);
 		}


         SearchRequest searchRequest = new SearchRequest();
         
         SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


         QueryBuilder queryBuilder =
        		 QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("title",
        				 "*"+title+"*"));

         searchSourceBuilder.query(queryBuilder);

         searchRequest.source(searchSourceBuilder);

         SearchResponse response = client.search(searchRequest,
        		 RequestOptions.DEFAULT);
         
         System.out.println(response);

         return getSearchResult(response);

     }
     
     //--------------------------------------------------------------------------------------------//
     
    
     public NoteModel searchById(String token, int noteId) throws Exception {
    	 
    	 String useremail = tokenUtility.decodeToken(token);
  		User user = userRepository.findByEmail(useremail);

   		if (user == null) {

   			System.out.println("error exception thrown.");
   			throw new InvalidUserException(NoteUtility.USER_NOT_FOUND);
   		}


         GetRequest getRequest = new GetRequest(INDEX, TYPE, Integer.toString(noteId) );

         GetResponse getResponse = client.get(getRequest,
        		 RequestOptions.DEFAULT);
         
         Map<String, Object> resultMap = getResponse.getSource();

         return objectMapper.convertValue(resultMap, NoteModel.class);

     }
     
     
     //-------------------------------------------------------------------------------------------------//
     
     public List<NoteModel> searchByWord(String token ,String discription) throws Exception {
    	 
    	 String useremail = tokenUtility.decodeToken(token);
  		User user = userRepository.findByEmail(useremail);

   		if (user == null) {

   			System.out.println("error exception thrown.");
   			throw new InvalidUserException(NoteUtility.USER_NOT_FOUND);
   		}


         SearchRequest searchRequest = new SearchRequest();
         
         SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

         QueryBuilder queryBuilder =
		 QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("discription",
		 "*"+discription+"*"));

         searchSourceBuilder.query(queryBuilder);

         searchRequest.source(searchSourceBuilder);

         SearchResponse response = client.search(searchRequest,
        		 RequestOptions.DEFAULT);

         return getSearchResult(response);


     }
     
     //-------------------------------------------------------------------------------------------------//
     
     private List<NoteModel> getSearchResult(SearchResponse response) 
     {
    	 
         SearchHit[] searchHit = response.getHits().getHits();

         List<NoteModel> note = new ArrayList<>();

         if (searchHit.length > 0) {

         Arrays.stream(searchHit)
         .forEach(hit ->
         note.add(objectMapper.convertValue(hit.getSourceAsMap(),
		 NoteModel.class)));
         	}
		return note;

     }
     
     //-------------------------------------------------------------------------------------------------//
     
}
         