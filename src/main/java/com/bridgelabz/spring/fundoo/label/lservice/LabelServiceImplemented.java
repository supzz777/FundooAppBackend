package com.bridgelabz.spring.fundoo.label.lservice;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.spring.fundoo.label.ldto.LabelDto;
import com.bridgelabz.spring.fundoo.label.lmodel.LabelModel;
import com.bridgelabz.spring.fundoo.label.lrepository.LabelRepository;
import com.bridgelabz.spring.fundoo.label.lutility.LabelUtility;
import com.bridgelabz.spring.fundoo.notes.ndto.NoteDto;
import com.bridgelabz.spring.fundoo.notes.nexception.custom.IdNotFoundException;
import com.bridgelabz.spring.fundoo.notes.nexception.custom.InvalidUserException;
import com.bridgelabz.spring.fundoo.notes.nmodel.NoteModel;
import com.bridgelabz.spring.fundoo.notes.nrepository.NoteRepository;
import com.bridgelabz.spring.fundoo.notes.nutitlity.NoteUtility;
import com.bridgelabz.spring.fundoo.user.model.User;
import com.bridgelabz.spring.fundoo.user.repository.UserRepository;
import com.bridgelabz.spring.fundoo.user.response.Response;
import com.bridgelabz.spring.fundoo.user.utility.TokenUtility;
@Service
public class LabelServiceImplemented  implements ILabelService
{
	@Autowired
	private LabelRepository lrepository;
	
	@Autowired
	private ModelMapper mapper;
		
	@Autowired
	TokenUtility tokenUtility;
	
	@Autowired
	private UserRepository repository;
	
	//------------------------------------------------------------------------------------------------//
	
	@Override
	public Response createLabel(@Valid LabelDto labelDto, String token)
	{
		LabelModel label =mapper.map(labelDto, LabelModel.class);
		
		String useremail = tokenUtility.decodeToken(token);
		
		User user = repository.findByEmail(useremail);
		
		if(user == null)
		{
			
			throw new InvalidUserException(LabelUtility.USER_NOT_FOUND);
		}
		
		label.setUser(user);
		
		label.setLabelName(labelDto.getLabelName());
		
		label = lrepository.save(label);
		
		
		return new Response(200,"label created","saved successfully");
	}
	
	//-----------------------------------------------------------------------------------------------//



		@Override
		public Response updateLabel( @Valid int labelId ,  LabelDto labelDto , String token)
		{
			//NoteModel note =mapper.map(noteDto, NoteModel.class);
			
			String useremail = tokenUtility.decodeToken(token);
			User user = repository.findByEmail(useremail);

			if(user == null)
			{

				System.out.println("error exception thrown.");
				throw new InvalidUserException(LabelUtility.USER_NOT_FOUND);
			}
			
			
			LabelModel labelupdate = lrepository.findById(labelId).
					orElseThrow(() ->new IdNotFoundException(LabelUtility.LABEL_NOT_FOUND)) ;
			/*
			  //LabelModel labelupdate = lrepository.findById(labelId);
			  
			  if(labelupdate == null)
			{
				throw new IdNotFoundException(NoteUtility.NOTE_NOT_FOUND);
			}
			*/
			
		
			
			labelupdate.setLabelName(labelDto.getLabelName());
			
			lrepository.save(labelupdate);
			
			return new Response(200,"label updated","updated successfully");
			
		}

		
		
	//-----------------------------------------------------------------------------------------------//
		
		@Override
		public Response deleteLabel(int id, String token)
		{
			
			String useremail = tokenUtility.decodeToken(token);
			User user = repository.findByEmail(useremail);
			
			if(user == null)
			{

				System.out.println("error exception thrown.");
				throw new InvalidUserException(LabelUtility.USER_NOT_FOUND);
			}
			
			LabelModel label = lrepository.findById(id).
					orElseThrow(() ->new IdNotFoundException(LabelUtility.LABEL_NOT_FOUND)) ;

			//NoteModel note = nrepository.findById(id);
			label.setUser(user);
			
			lrepository.delete(label);
			
			return  new Response(200,"Label deleted","deleted successfully");
			
			
		}

		public  List<LabelModel> showAllLabels(String token)
		{
			
			String useremail = tokenUtility.decodeToken(token);
			User user = repository.findByEmail(useremail);

			if(user == null)
			{

				System.out.println("error exception thrown.");
				throw new InvalidUserException(LabelUtility.USER_NOT_FOUND);
			}
			
			List<LabelModel> label = lrepository.findAll().stream().filter(data-> data.getUser().getId() == user.getId()).collect(Collectors.toList());
			
			return label ; // show all user details in mysql.
			
		}
		
}
