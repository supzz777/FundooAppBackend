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
	/*it is a library.it is used above low level REST client.it accepts the request objects and returns the result 
	 * in the form of response object. so request marshaling and response un marshaling is handled by client itself.*/
	@Autowired
    private RestHighLevelClient client;

	 @Autowired
	 private ObjectMapper objectMapper; /*ObjectMapper provides functionality for
 										reading and writing JSON, either to and from  POJOs (Plain Old Java Objects)*/
	 
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
		Map<String, Object> document = 
		objectMapper.convertValue(note, Map.class);
	    
	    
	    //.source() is used to map the object inside the index.
	    
	    IndexRequest indexRequest = new IndexRequest(INDEX, TYPE,
	    		String.valueOf(note.getId()))
	    .source(document); //.index(INDEX).type(TYPE);
	
	    //making the response object
	    IndexResponse indexResponse = client.index(indexRequest,
	    		RequestOptions.DEFAULT);
	
	    
	    return indexResponse.getResult().name();
	
	}
	
	//----------------------------------------------------------------------------------------------------//
	
     @Override
	   public String updateNote(NoteModel note) throws Exception {
    	 
    	 	//stores the note by id.
		   NoteModel  document = noteRepository.findById(note.getId()).orElse(null);
		   
		   //for mapping the note object value inside the map
	        @SuppressWarnings("unchecked")
			Map<String, Object> documentMapper =
			objectMapper.convertValue(note, Map.class);
	        
	        //it stores the int id into the string value inside the map.
	        UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE,
	        		String.valueOf(document.getId()));
	        
	        /*Javadoc is a tool which comes with JDK and it is used for generating 
	        Java code documentation in HTML/JSON format from Java source code, */
	        //CONVERTS THE JAVA CODE INTO THE JSON FORMAT when script is not specified.
	        updateRequest.doc(documentMapper);
	        
	        //simply stores the data in elastic search database.
	        //RequestOptions.DEFAULT --> it is used to store the chnages without disturbing the natural behaviour of elastic search
	        UpdateResponse updateResponse = client.update(updateRequest,
	        		RequestOptions.DEFAULT);
	        
	        //returns the result.
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

 		//predefined  method for searching operation provided by elasticsearch.
         SearchRequest searchRequest = new SearchRequest();
         
         	

		/*	Most search parameters are added to the SearchSourceBuilder. 
			It offers setters for everything that goes into the search request body. */
         SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

         //builds the query automatically and matches boolean combination of other queries.
         QueryBuilder queryBuilder =
        		 QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("title",
        				 "*"+title+"*"));
         
         
         searchSourceBuilder.query(queryBuilder);

         searchRequest.source(searchSourceBuilder);
         
         //making the response objects and returning it later.
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

   			//getting the request from the user
         GetRequest getRequest = new GetRequest(INDEX, TYPE, Integer.toString(noteId) );
         
         //making the response object
         GetResponse getResponse = client.get(getRequest,
        		 RequestOptions.DEFAULT);
         
         //storing the response inside the map.s
         Map<String, Object> responseMap = getResponse.getSource();

         return objectMapper.convertValue(responseMap, NoteModel.class);
        

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
         
         //boolQuery() --> it is used to pass the restrictions on the search results.
         //Querybuilder builts the query automatically.
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
         