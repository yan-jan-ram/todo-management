package com.project.todo.controller;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.todo.dto.TodoDTO;
import com.project.todo.exception.TodoException;
import com.project.todo.service.TodoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/todo")
@Validated
public class TodoController {

	private final TodoService todoService;
	
	Environment environment;
	
	//http://localhost:8085/api/todo/addTask
	@PostMapping(value = "/addTask", consumes = "application/json", produces = "application/json")
	public ResponseEntity<TodoDTO> addTodoTask(@RequestBody @Valid TodoDTO todoDTO) throws TodoException {
		TodoDTO savedDto = todoService.addTodoTask(todoDTO);
		
		return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/bulk")
	//http://localhost:8085/api/todo/bulk
	public ResponseEntity<List<TodoDTO>> addTodoTasks(@RequestBody @Valid List<TodoDTO> todoDTOs) throws TodoException {
		List<TodoDTO> savedDtos = todoService.addTodoTasks(todoDTOs);
		
		return new ResponseEntity<>(savedDtos, HttpStatus.CREATED);
	}
	
	//http://localhost:8085/api/todo/getTask/
	@GetMapping(value = "/getTask/{id}")
	public ResponseEntity<TodoDTO> getTodoTask(@PathVariable("id")
			@Min(value = 1, message = "{todo.task_id.invalid}") Long taskId) throws TodoException {
		TodoDTO todoDto = todoService.getTodoTask(taskId);
		
		return new ResponseEntity<>(todoDto, HttpStatus.OK);
	}
	
	//http://localhost:8085/api/todo/getAll
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<TodoDTO>> getAllTodoTasks() throws TodoException {
		List<TodoDTO> todoDTOs = todoService.getAllTodoTasks();
		
		return new ResponseEntity<>(todoDTOs, HttpStatus.OK);
	}
	
	//http://localhost:8085/api/todo/update/
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<TodoDTO> updateTodoTask(@PathVariable("id")
			@Min(value = 1, message = "{todo.task_id.invalid}") Long taskId,
			@RequestBody @Valid TodoDTO todoDTO) throws TodoException {
		TodoDTO savedDto = todoService.updateTodoTask(taskId, todoDTO);
		
		return new ResponseEntity<>(savedDto, HttpStatus.OK);
	}
	
	//http://localhost:8085/api/todo/delete/
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteTodoTask(@PathVariable("id")
			@Min(value = 1, message = "{todo.task_id.invalid}") Long taskId) throws TodoException {
		todoService.deleteTodoTask(taskId);
		String message = environment.getProperty("API.TODO_DELETE_SUCCESS");
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	//http://localhost:8085/api/todo/completed-task/
	@PatchMapping(value = "/completed-task/{id}")
	public ResponseEntity<TodoDTO> completeTodoTask(@PathVariable("id") 
	@Min(value = 1, message = "{todo.task_id.invalid}") Long taskId) throws TodoException {
		TodoDTO todoDTO = todoService.completeTodoTask(taskId);
		return new ResponseEntity<>(todoDTO, HttpStatus.OK);
	}
	
	//http://localhost:8085/api/todo/incompleted-task/
	@PatchMapping(value = "/incompleted-task/{id}")
	public ResponseEntity<TodoDTO> incompleteTodoTask(@PathVariable("id")
	@Min(value = 1, message = "{todo.task_id.invalid}") Long taskId) throws TodoException {
		TodoDTO todoDTO = todoService.incompleteTodoTask(taskId);
		return new ResponseEntity<>(todoDTO, HttpStatus.OK);
	}
	
}
