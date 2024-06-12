package com.project.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.todo.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

}
