package com.banquemisr.challeng05.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskDTO {

	private Long id;

	@NotNull(message = " title can not be null ")
	@Size(max = 10, message = " title max caracter 10 ")
	private String title;

	@NotNull(message = "Name can not be null")
	@Size(max = 100, message = " description max caracter 100 ")
	private String description;

	@Pattern(regexp = "todo|in progress|done", message = "Status must be one of todo, in progress, done")
	private String status;

	@Pattern(regexp = "low|medium|high", message = "Priority must be one of low, medium , high")
	private String priority;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate duedate;

}
