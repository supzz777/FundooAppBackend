package com.bridgelabz.spring.fundoo.user.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import com.bridgelabz.spring.fundoo.notes.nmodel.NoteModel;

@Component /*
			 * It uses the annotation to create a bean that randomly generates names. It
			 * will be detected during component scan process
			 */
@Entity /*
		 * The @Entity annotation specifies that the class is an entity and is mapped to
		 * a database table.
		 */

 /*
								 * The @Table annotation specifies the name of the database table to be used for
								 * mapping.
								 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // specifies the primary key if the databse table.
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull // @NotNull annotations let you check nullability of a variable, parameter,
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String email;
	@NotNull
	private String password;
	@NotNull
	private long phoneNumber;
	@NotNull
	private boolean validate;
	
	
	
	

	@OneToMany(mappedBy = "user")
	private List<NoteModel> notes;
	
	private boolean login; //1 for login 0 for logout
	
	private String profilePic;

	/*
	 * @OneToMany private List<LabelModel> labels;
	 */

	// Class constructor.
	public User() {

	}

	
	public User(int id, @NotNull String firstName, @NotNull String lastName, @NotNull String email,
			@NotNull String password, @NotNull long phoneNumber, @NotNull boolean validate, List<NoteModel> notes,
			boolean login, String profilePic) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.validate = validate;
		this.notes = notes;
		this.login = login;
		this.profilePic = profilePic;
	}

	// Class constructor with feilds.
	public User(int id, @NotNull String firstName, @NotNull String lastName, @NotNull String email,
			@NotNull String password, @NotNull long phoneNumber, @NotNull boolean validate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.validate = validate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public boolean isValidate() {
		return validate;
	}


	public void setValidate(boolean validate) {
		this.validate = validate;
	}


	public List<NoteModel> getNotes() {
		return notes;
	}


	public void setNotes(List<NoteModel> notes) {
		this.notes = notes;
	}


	public boolean isLogin() {
		return login;
	}


	public void setLogin(boolean login) {
		this.login = login;
	}


	public String getProfilePic() {
		return profilePic;
	}


	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", phoneNumber=" + phoneNumber + ", validate=" + validate + ", notes="
				+ notes + ", login=" + login + ", profilePic=" + profilePic + "]";
	}


	
	
}
