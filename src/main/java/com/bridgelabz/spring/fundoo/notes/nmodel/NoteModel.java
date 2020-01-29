package com.bridgelabz.spring.fundoo.notes.nmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import com.bridgelabz.spring.fundoo.label.lmodel.LabelModel;
import com.bridgelabz.spring.fundoo.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component /*
			 * It uses the annotation to create a bean that randomly generates names. It
			 * will be detected during component scan process
			 */
@Entity /*
		 * The @Entity annotation specifies that the class is an entity and is mapped to
		 * a database table.
		 */



@Table(name = "noteDetails") /*
								 * The @Table annotation specifies the name of the database table to be used for
								 * mapping.
								 */
@JsonIgnoreProperties(value = { "user" }) // ignores the user details in the postcard output.
public class NoteModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id // specifies the primary key if the databse table.
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	private String title;
	@NotNull
	private String discription;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties(value = { "user" }) 
	private User user;

	@ManyToMany(mappedBy = "notes")
	List<LabelModel> labels;

	@Temporal(TemporalType.TIMESTAMP) //used for registering the time and date of when the note is being created.
	@CreationTimestamp
	private Date noteregistrationDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date noteUpdateDate;
	
	private boolean archive;
	
	private boolean trash;
	
	private boolean pin;
	
	private Date reminder;

	public NoteModel() {

	}

	public NoteModel(int id, @NotNull String title, @NotNull String discription, User user, List<LabelModel> labels,
			Date noteregistrationDate, Date noteUpdateDate, boolean archive, boolean trash, boolean pin,
			Date reminder) {
		super();
		this.id = id;
		this.title = title;
		this.discription = discription;
		this.user = user;
		this.labels = labels;
		this.noteregistrationDate = noteregistrationDate;
		this.noteUpdateDate = noteUpdateDate;
		this.archive = archive;
		this.trash = trash;
		this.pin = pin;
		this.reminder = reminder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		
		this.discription = discription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<LabelModel> getLabels() {
		return labels;
	}

	public void setLabels(List<LabelModel> labels) {
		this.labels = labels;
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

	public boolean getArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public boolean getTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public boolean getPin() {
		return pin;
	}

	public void setPin(boolean pin) {
		this.pin = pin;
	}

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	@Override
	public String toString() {
		return "NoteModel [id=" + id + ", title=" + title + ", discription=" + discription + ", user=" + user
				+ ", labels=" + labels + ", noteregistrationDate=" + noteregistrationDate + ", noteUpdateDate="
				+ noteUpdateDate + ", archive=" + archive + ", trash=" + trash + ", pin=" + pin + ", reminder="
				+ reminder + "]";
	}
	
	
	
}
