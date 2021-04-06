package ca.sheridancollege.pennyjobs.repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import ca.sheridancollege.pennyjobs.beans.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
	public Account findByFirstName(String firstName);
	public Account findByEmail(String email);
}
