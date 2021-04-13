package ca.sheridancollege.pennyjobs.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.sheridancollege.pennyjobs.beans.Account;
import ca.sheridancollege.pennyjobs.beans.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
	
	public Student findByAccount(Account account);

}
