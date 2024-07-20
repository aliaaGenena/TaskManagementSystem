package com.banquemisr.challeng05.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banquemisr.challeng05.pojo.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

	public List<Task> findAllByTitle(String title);

	public List<Task> findAllByPriority(String priority);
	
	public List<Task> findAllByDuedate(LocalDate due);

}
