package com.project.todo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDTO {

	private Long taskId;
	
	@NotNull(message = "{todo.title.absent}")
	private String title;
	
	@NotNull(message = "{todo.description.absent}")
	private String description;
	
	@NotNull(message = "{todo.is_completed.absent}")
	private Boolean isCompleted;
	
}
