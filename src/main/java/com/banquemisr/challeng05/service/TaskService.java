package com.banquemisr.challeng05.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.banquemisr.challeng05.constant.ResponseStatusEnum;
import com.banquemisr.challeng05.dao.TaskRepo;
import com.banquemisr.challeng05.dto.TaskDTO;
import com.banquemisr.challeng05.error.BusinessException;
import com.banquemisr.challeng05.mapper.TaskMapper;
import com.banquemisr.challeng05.pojo.Task;

@Service
public class TaskService {

	@Autowired
	TaskRepo taskRepo;

	@Autowired
	TaskMapper taskMapper;

	public List<TaskDTO> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
		Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Task> page = taskRepo.findAll(pageable);

		return taskMapper.mapTaskDTOs(page.getContent());

	}

	@Transactional
	public TaskDTO createTask(TaskDTO taskDTO) throws BusinessException {

		if (findAllByDuedate(taskDTO.getDuedate()).size() > 3) {
			throw new BusinessException(ResponseStatusEnum.EXCEED_TASKS_PER_DAY);
		}
		Task task = taskRepo.save(taskMapper.mapTask(taskDTO));
		return taskMapper.mapDto(task);

	}

	@Transactional
	public TaskDTO updateTask(TaskDTO taskDTO) throws BusinessException {

		Task task = taskRepo.findById(taskDTO.getId())
				.orElseThrow(() -> new BusinessException(ResponseStatusEnum.NOTFOUND));

		if (findAllByDuedate(taskDTO.getDuedate()).size() > 3) {
			throw new BusinessException(ResponseStatusEnum.EXCEED_TASKS_PER_DAY);
		}

		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		task.setPriority(taskDTO.getPriority());
		task.setStatus(taskDTO.getStatus());
		task.setDuedate(taskDTO.getDuedate());

		taskRepo.save(task);

		return taskDTO;
	}

	@Transactional
	public void deleteTask(TaskDTO taskDTO) throws BusinessException {
		taskRepo.findById(taskDTO.getId()).orElseThrow(() -> new BusinessException(ResponseStatusEnum.NOTFOUND));
		taskRepo.deleteById(taskDTO.getId());

	}

	public List<TaskDTO> findAllByDuedate(LocalDate date) {
		List<Task> tasks = taskRepo.findAllByDuedate(date);
		return taskMapper.mapTaskDTOs(tasks);

	}
	
}
