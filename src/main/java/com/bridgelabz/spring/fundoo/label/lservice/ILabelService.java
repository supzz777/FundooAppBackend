package com.bridgelabz.spring.fundoo.label.lservice;

import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.spring.fundoo.label.ldto.LabelDto;
import com.bridgelabz.spring.fundoo.label.lmodel.LabelModel;
import com.bridgelabz.spring.fundoo.user.response.Response;

public interface ILabelService 
{
	public Response createLabel(@Valid LabelDto labelDto, String token);
	
	public Response updateLabel( @Valid int labelId ,  LabelDto labelDto , String token);
	
	public Response deleteLabel(int id, String token);
	
	public  List<LabelModel> showAllLabels(String token);
	
}
