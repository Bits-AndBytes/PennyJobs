package ca.sheridancollege.pennyjobs.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Account account;
	
	@OneToOne(cascade=CascadeType.ALL) 
	private Parent parent;
	
	private double rating;
	
	private String bio;
	
	@Column(nullable=true)
	private String transferEmail;
	
	@Column(nullable=true)
	private Integer requestedToLink;
}
