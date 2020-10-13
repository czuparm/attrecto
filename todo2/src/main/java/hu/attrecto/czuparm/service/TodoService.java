package hu.attrecto.czuparm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.attrecto.czuparm.domain.Todo;
import hu.attrecto.czuparm.repository.TodoRepository;

@Service
public class TodoService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private TodoRepository todoRepository;
	private BaseService baseService;
	
	@Autowired
	public void setTodoRepository(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;		
	}
	
	@Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	
	public List<Todo> getAllTodo(){
		logger.info("Összes todo lekérése");
		return todoRepository.findAll();
	}

	public List<Todo> getTodoByUserId(String userId) {
		logger.info("A(z) " + userId + " userId-hoz tartozó feladatok lekérése");
		return todoRepository.getTodoByUserId(userId);
	}

	public Todo saveTodo(Todo todo) {
		if(todo.getId() == null){
			logger.info("A(z) " + todo.getTitle() + "című todo letétrehozásra került");
		} else {
			logger.info("A(z) " + todo.getTitle() + "című todo módosításra került");
		}
		baseService.setAuditable(todo);
		return todoRepository.save(todo);		
	}

	public void deleteTodoById(Long todoId) {
		logger.info("A(z) " + todoId + "azonosítójú todo törlésre került");
		todoRepository.deleteById(todoId);
	}
	
	public List<Todo> getCloseDeadLineTodos(){
		logger.info("A közeli határidős todo-k lekérése");
		return todoRepository.getCloseDeadLineTodos();
	}

	public void saveTodos(List<Todo> todoList) {
		todoRepository.saveAll(todoList);		
	}
	
	
}
