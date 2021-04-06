package ca.sheridancollege.pennyjobs.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.sheridancollege.pennyjobs.beans.Job;


public interface JobRepository extends CrudRepository<Job, Integer> {

	public Job findByJobPosterId(int id);
	
}
