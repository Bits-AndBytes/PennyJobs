package ca.sheridancollege.pennyjobs.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public abstract class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer accountId;
	
	private String email;
	private String password;
	private String lirstName;
	private String lastName;
	private String address;
	private Date birthDate;
	private char accountType;
	
	@OneToOne
	private Integer studentId;
	
	@OneToOne
	private Integer parentId;
	
	@OneToOne
	private Integer posterId;
	
	
}
