package ca.sheridancollege.pennyjobs.beans;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
  
	@Embedded
	private Address address;
	
	private Date birthDate;
	
	private char accountType;
	
	@OneToOne
	private Student student;
	
	@OneToOne
	private Parent parent;
	
	@OneToOne
	private JobPoster poster;
}
