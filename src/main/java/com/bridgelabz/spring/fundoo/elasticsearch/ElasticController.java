package com.bridgelabz.spring.fundoo.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.spring.fundoo.user.response.Response;

@RestController
@Controller
public class ElasticController
{
	
	@Autowired
	ElasticSearchImplemented elasticSearchImplemented;
	
	
	// -------------------------------------------------------------------------------------------------------------//

		//1 --> mapping for showing all the notes in the sorted form by title.
		@GetMapping("/note/searchByTitle/{word}")
		public Response SortByTitle(@RequestHeader String token , @PathVariable ("word")  String word ) throws Exception {

			return new Response(200, "Show all details", elasticSearchImplemented.searchByTitle(token , word) ); // give

		}

	// -------------------------------------------------------------------------------------------------------------//
		
		//1 --> mapping for showing all the notes in the sorted form by title.
		@GetMapping("/note/searchById/{id}")
		public Response SortById(@RequestHeader String token , @PathVariable ("id")  int id ) throws Exception {

			return new Response(200, "Show all details", elasticSearchImplemented.searchById(token , id) ); // give

		}

	// -------------------------------------------------------------------------------------------------------------//
		

		//1 --> mapping for showing all the notes in the sorted form by title.
		@GetMapping("/note/searchByWord/{word}")
		public Response SortById(@RequestHeader String token , @PathVariable ("word")  String word ) throws Exception {

			return new Response(200, "Show all details", elasticSearchImplemented.searchByWord(token , word) ); // give

		}

	// -------------------------------------------------------------------------------------------------------------//
		

}
