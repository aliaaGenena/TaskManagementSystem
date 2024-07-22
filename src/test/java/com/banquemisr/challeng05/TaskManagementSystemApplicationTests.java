package com.banquemisr.challeng05;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.banquemisr.challeng05.dao.TaskRepo;
import com.banquemisr.challeng05.dto.TaskDTO;
import com.banquemisr.challeng05.error.BusinessException;
import com.banquemisr.challeng05.mapper.TaskMapper;
import com.banquemisr.challeng05.service.TaskService;

@SpringBootTest
class TaskManagementSystemApplicationTests {

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskRepo taskRepo;

	@Autowired
	private TaskMapper taskMapper;

	@Test
	public void testcreateTaskExceedLimit() throws BusinessException {
		LocalDate today = LocalDate.now();

		List<TaskDTO> tasksToday = taskService.findAllByDuedate(today);
		assertThat(tasksToday.size()).isLessThanOrEqualTo(4);

	}
}
