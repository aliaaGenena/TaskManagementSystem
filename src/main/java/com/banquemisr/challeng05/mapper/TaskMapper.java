package com.banquemisr.challeng05.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import com.banquemisr.challeng05.dto.TaskDTO;
import com.banquemisr.challeng05.pojo.Task;

@Component
@Mapper(componentModel = "spring") 
public interface TaskMapper {

	TaskDTO mapDto(Task task);
	
	List<TaskDTO> mapTaskDTOs(List<Task> tasks);

	Task mapTask(TaskDTO taskDTO);
}
