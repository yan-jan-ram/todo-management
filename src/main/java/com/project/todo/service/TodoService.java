package com.project.todo.service;

import java.util.List;

import com.project.todo.dto.TodoDTO;
import com.project.todo.exception.TodoException;

public interface TodoService {

	public TodoDTO addTodoTask(TodoDTO todoDTO) throws TodoException;
	public List<TodoDTO> addTodoTasks(List<TodoDTO> todoDTOs) throws TodoException;
	public TodoDTO getTodoTask(Long taskId) throws TodoException;
	public List<TodoDTO> getAllTodoTasks() throws TodoException;
	public TodoDTO updateTodoTask(Long taskId, TodoDTO todoDTO) throws TodoException;
	public void deleteTodoTask(Long taskId) throws TodoException;
	public TodoDTO completeTodoTask(Long taskId) throws TodoException;
	public TodoDTO incompleteTodoTask(Long taskId) throws TodoException;
}
