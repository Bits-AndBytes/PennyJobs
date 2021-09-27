package ca.sheridancollege.pennyjobs.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.sheridancollege.pennyjobs.beans.Account;
import ca.sheridancollege.pennyjobs.beans.JobPoster;

public interface JobPosterRepository extends CrudRepository<JobPoster, Integer> {
	
	public JobPoster findByAccount(Account account);

}
