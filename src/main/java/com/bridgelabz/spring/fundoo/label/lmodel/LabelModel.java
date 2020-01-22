package com.bridgelabz.spring.fundoo.label.lmodel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.bridgelabz.spring.fundoo.notes.nmodel.NoteModel;
import com.bridgelabz.spring.fundoo.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@Entity
@Table(name = "labelDetails")
@JsonIgnoreProperties(value = { "user" })
public class LabelModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int labelId;

	@NotNull
	private String labelName;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToMany
	@JoinTable(name = "labeled_notes", joinColumns = @JoinColumn(name = "label_id"))
	List<NoteModel> notes;

	public LabelModel() {

	}
	
	//constructor using the feilds.
	public LabelModel(int labelId, @NotNull String labelName, User user, List<NoteModel> notes) {
		super();
		this.labelId = labelId;
		this.labelName = labelName;
		this.user = user;
		this.notes = notes;
	}
	
	//getters and setters
	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public List<NoteModel> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteModel> notes) {
		this.notes = notes;
	}
	
	//to String method.
	@Override
	public String toString() {
		return "LabelModel [labelId=" + labelId + ", labelName=" + labelName + ", user=" + user + ", notes=" + notes
				+ "]";
	}

}
