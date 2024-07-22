package com.banquemisr.challeng05.request;

import com.banquemisr.challeng05.dto.TaskDTO;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class UpdateTaskRq {

	@Valid
	TaskDTO taskDTO;
}
