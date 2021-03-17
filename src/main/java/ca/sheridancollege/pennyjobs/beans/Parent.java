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
public class Parent extends Account{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer parentId;
	private Integer studentId;
	private Integer accountId;

}
