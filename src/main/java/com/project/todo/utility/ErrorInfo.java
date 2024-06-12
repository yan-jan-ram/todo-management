package com.project.todo.utility;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorInfo {

	private Integer errorCode;
	private String errorMsg;
	private LocalDateTime timestamp;
	private String webRequestDetails;
}
