package ca.sheridancollege.pennyjobs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.sheridancollege.pennyjobs.beans.Job;


public interface JobRepository extends CrudRepository<Job, Integer> {

	public List<Job> findByJobPosterId(int id);
	
	public List<Job> findByStudentId(int id);
	
	public List<Job> findAllByTitleIgnoreCaseContains(String title);
	
	public List<Job> findAllByDescriptionIgnoreCaseContains(String description);
	
}
