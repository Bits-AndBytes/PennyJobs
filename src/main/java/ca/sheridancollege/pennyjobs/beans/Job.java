package ca.sheridancollege.pennyjobs.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

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
	
	private String name;
	private String street;
	private String city;
	private String postalCode;
	private String province;
	private String description;
	private String underage;
	
	@Transient
	private String[] underageOptions = {"Yes", "No"};
	
}
