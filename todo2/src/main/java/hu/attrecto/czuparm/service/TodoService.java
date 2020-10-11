package hu.attrecto.czuparm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.attrecto.czuparm.domain.Todo;
import hu.attrecto.czuparm.repository.TodoRepository;

@Service
public class TodoService {
	
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
		return todoRepository.findAll();
	}

	public List<Todo> getTodoByUserId(String userId) {
		return todoRepository.getTodoByUserId(userId);
	}

	public Todo saveTodo(Todo todo) {
		baseService.setAuditable(todo);
		return todoRepository.save(todo);		
	}

	public void deleteTodoById(Long todoId) {
		todoRepository.deleteById(todoId);
	}
	
}
