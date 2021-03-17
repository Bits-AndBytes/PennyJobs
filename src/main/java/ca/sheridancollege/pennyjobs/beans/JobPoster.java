package ca.sheridancollege.pennyjobs.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class JobPoster {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer posterId;
	
	private Integer accountId;


}
