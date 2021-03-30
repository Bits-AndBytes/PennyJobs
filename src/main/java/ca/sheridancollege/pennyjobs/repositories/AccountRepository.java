package ca.sheridancollege.pennyjobs.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.sheridancollege.pennyjobs.beans.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
