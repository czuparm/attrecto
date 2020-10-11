package hu.attrecto.czuparm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.attrecto.czuparm.domain.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
	
	List<Todo> findAll();
	
	@Query(value = "select * from todos where user_id = :userId", nativeQuery = true)
	List<Todo> getTodoByUserId(@Param("userId") String userId);

	@Query(value = "select *, dead_line-now() from todos t \r\n" + 
			"where dead_line between now() and now() + interval '1' day\r\n" + 
			"\r\n" + 
			"", nativeQuery = true)
	List<Todo> getCloseDeadLineTodos();
}
