package com.banquemisr.challeng05.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TaskDTO {

	private Long id;
	private String title;
	private String description;
	private String status;
	private String priority;
	private LocalDate duedate;

}
