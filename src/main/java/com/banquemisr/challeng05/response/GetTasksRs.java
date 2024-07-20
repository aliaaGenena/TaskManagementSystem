package com.banquemisr.challeng05.response;

import java.util.List;

import com.banquemisr.challeng05.dto.TaskDTO;

import lombok.Data;

@Data
public class GetTasksRs {

	private List<TaskDTO> taskDTO;

}
