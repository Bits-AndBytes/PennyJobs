package ca.sheridancollege.pennyjobs.beans;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity

public class Role {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String rolename;
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="roles")
	private List<Account> accounts = new ArrayList<Account>();

	public Role(String rolename) {
		this.rolename = rolename;
	}
	
}
