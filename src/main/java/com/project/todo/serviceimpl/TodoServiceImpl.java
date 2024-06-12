package com.project.todo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.project.todo.dto.TodoDTO;
import com.project.todo.entity.TodoEntity;
import com.project.todo.exception.TodoException;
import com.project.todo.repository.TodoRepository;
import com.project.todo.service.TodoService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service(value = "todoService")
@Transactional
public class TodoServiceImpl implements TodoService{

	private final TodoRepository todoRepository;
	
	private final ModelMapper modelMapper;
	
	@Override
	public TodoDTO addTodoTask(TodoDTO todoDTO) throws TodoException {
		// TODO Auto-generated method stub
		TodoEntity todoEntity = modelMapper.map(todoDTO, TodoEntity.class);
		
		TodoEntity savedEntity = todoRepository.save(todoEntity);
		
		return modelMapper.map(savedEntity, TodoDTO.class);
	}

	@Override
	public List<TodoDTO> addTodoTasks(List<TodoDTO> todoDTOs) throws TodoException {
		// TODO Auto-generated method stub
		List<TodoEntity> todoEntities = todoDTOs
				.stream()
				.map((todo) -> modelMapper.map(todo, TodoEntity.class))
				.collect(Collectors.toList());
		List<TodoEntity> savedEntities = todoRepository.saveAll(todoEntities);
		
		return savedEntities
				.stream()
				.map((saved) -> modelMapper.map(saved, TodoDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public TodoDTO getTodoTask(Long taskId) throws TodoException {
		// TODO Auto-generated method stub
		TodoEntity todoEntity = todoRepository
				.findById(taskId)
				.orElseThrow(() -> new TodoException("service.TASK_NOT_FOUND"));
		
		return modelMapper.map(todoEntity, TodoDTO.class);
	}

	@Override
	public List<TodoDTO> getAllTodoTasks() throws TodoException {
		// TODO Auto-generated method stub
		List<TodoEntity> todoEntities = todoRepository.findAll();
		
		if (todoEntities.isEmpty()) {
			throw new TodoException("service.NO_TASKS_FOUND");
		}
		
		return todoEntities
				.stream()
				.map((todo) -> modelMapper.map(todo, TodoDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public TodoDTO updateTodoTask(Long taskId, TodoDTO todoDTO) throws TodoException {
		// TODO Auto-generated method stub
		TodoEntity todoEntity = todoRepository
				.findById(taskId)
				.orElseThrow(() -> new TodoException("service.TASK_NOT_FOUND"));
		
		todoEntity.setTitle(todoDTO.getTitle());
		todoEntity.setDescription(todoDTO.getDescription());
		todoEntity.setIsCompleted(todoDTO.getIsCompleted());
		TodoEntity savedEntity = todoRepository.save(todoEntity);
		
		return modelMapper.map(savedEntity, TodoDTO.class);
	}

	@Override
	public void deleteTodoTask(Long taskId) throws TodoException {
		// TODO Auto-generated method stub
		todoRepository
				.findById(taskId)
				.orElseThrow(() -> new TodoException("service.TASK_NOT_FOUND"));
		todoRepository.deleteById(taskId);
	}

	@Override
	public TodoDTO completeTodoTask(Long taskId) throws TodoException {
		// TODO Auto-generated method stub
		TodoEntity todoEntity = todoRepository
				.findById(taskId)
				.orElseThrow(() -> new TodoException("service.TASK_NOT_FOUND"));
		todoEntity.setIsCompleted(Boolean.TRUE);
		TodoEntity savedEntity = todoRepository.save(todoEntity);
		
		return modelMapper.map(savedEntity, TodoDTO.class);
	}

	@Override
	public TodoDTO incompleteTodoTask(Long taskId) throws TodoException {
		// TODO Auto-generated method stub
		TodoEntity todoEntity = todoRepository
				.findById(taskId)
				.orElseThrow(() -> new TodoException("service.TASK_NOT_FOUND"));
		todoEntity.setIsCompleted(Boolean.FALSE);
		TodoEntity savedEntity = todoRepository.save(todoEntity);
		
		return modelMapper.map(savedEntity, TodoDTO.class);
	}

}
