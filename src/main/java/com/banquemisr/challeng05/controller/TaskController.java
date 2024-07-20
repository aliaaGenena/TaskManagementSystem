package com.banquemisr.challeng05.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.banquemisr.challeng05.dto.TaskDTO;
import com.banquemisr.challeng05.request.CreateTaskRq;
import com.banquemisr.challeng05.request.DeleteTaskRq;
import com.banquemisr.challeng05.request.UpdateTaskRq;
import com.banquemisr.challeng05.response.CreateTaskRs;
import com.banquemisr.challeng05.response.GetTasksRs;
import com.banquemisr.challeng05.service.TaskService;
import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	TaskService taskService;

	@GetMapping("/All")
	@RolesAllowed({ "USER", "ADMIN" })
	public ResponseEntity<GetTasksRs> findAll(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "ASC") String sortDirection) {
		List<TaskDTO> taskDTOs = taskService.findAll(pageNo, pageSize, sortBy, sortDirection);

		GetTasksRs getTasksRs = new GetTasksRs();
		getTasksRs.setTaskDTO(taskDTOs);
		return new ResponseEntity<GetTasksRs>(getTasksRs, HttpStatus.OK);
	}

	@PostMapping("/Add")
	@RolesAllowed("ADMIN")
	public ResponseEntity<CreateTaskRs> create(@RequestBody CreateTaskRq createTaskRq) {
		TaskDTO taskDTO = taskService.createTask(createTaskRq.getTaskDTO());
		CreateTaskRs createTaskRs = new CreateTaskRs();
		createTaskRs.setTaskDTO(taskDTO);

		URI uri = URI.create("/task/" + taskDTO.getId());
		return ResponseEntity.created(uri).body(createTaskRs);
	}

	@PostMapping("/Update")
	@RolesAllowed("ADMIN")
	public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRq updateTaskRq) {

		taskService.updateTask(updateTaskRq.getTaskDTO());
		return new ResponseEntity(HttpStatus.OK);

	}

	@PostMapping("/Delete")
	@RolesAllowed("ADMIN")
	public ResponseEntity<?> deleteTask(@RequestBody DeleteTaskRq deleteTaskRq) {

		taskService.deleteTask(deleteTaskRq.getTaskDTO());
		return new ResponseEntity(HttpStatus.OK);

	}

}
