package com.bridgelabz.spring.fundoo.label.lmodel;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
	
	@Temporal(TemporalType.TIMESTAMP) //used for registering the time and date of when the note is being created.
	@CreationTimestamp
	private Date noteregistrationDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date noteUpdateDate;


	public LabelModel() {

	}

	//constructor with feilds.
	public LabelModel(int labelId, @NotNull String labelName, User user, List<NoteModel> notes,
			Date noteregistrationDate, Date noteUpdateDate) {
		super();
		this.labelId = labelId;
		this.labelName = labelName;
		this.user = user;
		this.notes = notes;
		this.noteregistrationDate = noteregistrationDate;
		this.noteUpdateDate = noteUpdateDate;
	}

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

	public Date getNoteregistrationDate() {
		return noteregistrationDate;
	}

	public void setNoteregistrationDate(Date noteregistrationDate) {
		this.noteregistrationDate = noteregistrationDate;
	}

	public Date getNoteUpdateDate() {
		return noteUpdateDate;
	}

	public void setNoteUpdateDate(Date noteUpdateDate) {
		this.noteUpdateDate = noteUpdateDate;
	}

	@Override
	public String toString() {
		return "LabelModel [labelId=" + labelId + ", labelName=" + labelName + ", user=" + user + ", notes=" + notes
				+ ", noteregistrationDate=" + noteregistrationDate + ", noteUpdateDate=" + noteUpdateDate + "]";
	}
	
	
	
	
}
