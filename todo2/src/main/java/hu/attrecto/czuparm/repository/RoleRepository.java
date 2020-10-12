package hu.attrecto.czuparm.repository;

import org.springframework.data.repository.CrudRepository;

import hu.attrecto.czuparm.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByRole(String role);
}
