package ca.sheridancollege.pennyjobs.beans;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.sun.istack.Nullable;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Job {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	
	@Embedded
	private Address address;

	private String description;
	
	private double price;
	
	private String underage;

	@ManyToOne
	private JobPoster jobPoster;
	
	@ManyToOne
	private Student student;
	
	@Column(nullable=true)
	private Boolean proofSubmitted;
	
	@Transient
	private String[] underageOptions = {"Yes", "No"};
	
}