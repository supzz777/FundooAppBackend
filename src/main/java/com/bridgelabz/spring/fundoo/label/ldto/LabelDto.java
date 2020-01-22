package com.bridgelabz.spring.fundoo.label.ldto;

import javax.validation.constraints.NotNull;

public class LabelDto 
{
	 @NotNull
	 private String labelName;
	 
	 public LabelDto()
	 {
		 
	 }
	
	public LabelDto( String labelName) 
	{
		super();
		
		this.labelName = labelName;
	}
	
	
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	 
	 
	 
	@Override
	public String toString() {
		return "LabelModel [ labelName=" + labelName + "]";
	}

}
