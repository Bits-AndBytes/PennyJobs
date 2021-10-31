package ca.sheridancollege.pennyjobs.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.sheridancollege.pennyjobs.beans.Account;
import ca.sheridancollege.pennyjobs.beans.Parent;

public interface ParentRepository extends CrudRepository<Parent, Integer> {
	
	public Parent findByAccount(Account account);

}
