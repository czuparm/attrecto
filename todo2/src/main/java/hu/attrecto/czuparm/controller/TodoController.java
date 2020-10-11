package hu.attrecto.czuparm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.attrecto.czuparm.domain.Todo;
import hu.attrecto.czuparm.service.TodoService;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
	
	private TodoService todoService;
	
	@Autowired
	public void setTodoService(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping("/getAllTodo")
	public List<Todo> getAllTodo(){
		return todoService.getAllTodo();
	}
	
	@PostMapping("/getTodoByUserId")
	public List<Todo> getTodoByUserId(String userId){
		return todoService.getTodoByUserId(userId);
	}
	
	@PostMapping("/saveTodo")
	public Todo saveTodo(@RequestBody Todo todo){
		return todoService.saveTodo(todo);
	}
	
	@PostMapping("/deleteTodoById")
	public void deleteTodoById(Long todoId){
		todoService.deleteTodoById(todoId);
	}
	
}
