package com.bridgelabz.spring.fundoo.label.ldto;

import javax.validation.constraints.NotNull;

public class LabelDto {
	@NotNull
	private String labelName;

	public LabelDto() {

	}
	
	//constructor with the feilds.
	public LabelDto(String labelName) {
		super();

		this.labelName = labelName;
	}
	
	//getters and setters
	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	//to String method.
	@Override
	public String toString() {
		return "LabelModel [ labelName=" + labelName + "]";
	}

}
