package ca.sheridancollege.pennyjobs.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.sheridancollege.pennyjobs.beans.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
