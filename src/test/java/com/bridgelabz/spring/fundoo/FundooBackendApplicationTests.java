package com.bridgelabz.spring.fundoo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bridgelabz.spring.fundoo.notes.ncontroller.NoteController;
import com.bridgelabz.spring.fundoo.notes.ndto.NoteDto;
import com.bridgelabz.spring.fundoo.notes.nservice.NoteServiceImplemented;

@RunWith(SpringRunner.class)
@WebMvcTest(value = NoteController.class)
@SpringBootTest
class FundooBackendApplicationTests {
	
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NoteServiceImplemented noteServiceImplemented;
	
	NoteDto note = new NoteDto("note title ", "note discription");
	
	
			

	String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

	
//	@Test
//	public void retrieveDetailsForCourse() throws Exception {
//
//		Mockito.when(
//				((OngoingStubbing) noteServiceImplemented.showAllNotesOfAllUserz()).thenReturn(note) );
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//				"/note/showAllNotes").accept(
//				MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		System.out.println(result.getResponse());
//		
//		String expected = "{title:note title ,discription:note discription}";
//
//		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}
//
//		JSONAssert.assertEquals(expected, result.getResponse()
//				.getContentAsString(), false);
//	}
	
	@Test
	public void getValue()
	{
		Assert.assertEquals(12, 12);
	}


}
