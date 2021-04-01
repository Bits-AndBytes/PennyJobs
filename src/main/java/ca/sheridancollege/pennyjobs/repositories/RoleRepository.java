package ca.sheridancollege.pennyjobs.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.sheridancollege.pennyjobs.beans.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	public Role findByRolename(String rolename);
}
