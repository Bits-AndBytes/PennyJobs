package ca.sheridancollege.pennyjobs.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import org.springframework.format.annotation.DateTimeFormat;

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
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	@Embedded
	private Address address;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	
	private String accountType;
	
	@Transient
	private String[] accountTypes = {"Student","Parent","Poster"};
	
	
	  @OneToOne(cascade=CascadeType.ALL) 
	  private Student student;
	  
	  @OneToOne(cascade=CascadeType.ALL) 
	  private Parent parent;
	  
	  @OneToOne(cascade=CascadeType.ALL)  
	  private JobPoster poster;



	public Account(String email, String password, String firstName, String lastName, Address address,
			Date birthDate, String accountType, Student student, Parent parent, JobPoster poster) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.birthDate = birthDate;

		this.accountType = accountType;
		/*
		 * this.student = student; this.parent = parent; this.poster = poster;
		 */
		enabled=1;
	}
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();
	
}
