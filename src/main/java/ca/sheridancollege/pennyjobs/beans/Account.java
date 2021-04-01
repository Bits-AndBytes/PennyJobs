package ca.sheridancollege.pennyjobs.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Byte enabled;
	
	private String email;
	
	private String encryptedpassword;
	
	private String firstname;
	
	private String lastname;
	
	@Embedded
	private Address address;
	
	private Date birthdate;
	
	private String accountType;
	
	
	  @OneToOne 
	  private Student student;
	  
	  @OneToOne 
	  private Parent parent;
	  
	  @OneToOne 
	  private JobPoster poster;


	public Account(String email, String encryptedpassword, String firstname, String lastname, Address address,
			Date birthdate, String accountType, Student student, Parent parent, JobPoster poster) {
		this.email = email;
		this.encryptedpassword = encryptedpassword;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.birthdate = birthdate;
		this.accountType = accountType;
		/*
		 * this.student = student; this.parent = parent; this.poster = poster;
		 */
		enabled=1;
	}
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();
	
	
	
	
}
