package com.banquemisr.challeng05.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.banquemisr.challeng05.dto.TaskDTO;

@Service
public class EmailService {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	TaskService taskService;

	public void sendMail(String subject, String to, String body) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setSubject(subject);
		mailMessage.setFrom("aliaa.emad.ali69@gmail.com");
		mailMessage.setTo(to);
		mailMessage.setText(body);

		mailSender.send(mailMessage);

	}

	@Scheduled(cron = "0 0 0 * * *")
	public void notifyTodayTasks() {
		List<TaskDTO> taskDTOs = taskService.findAllByDuedate(LocalDate.now());

		for (TaskDTO taskDTO : taskDTOs) {
			StringBuilder body = new StringBuilder();
			body.append("Dear user").append("\n\n").append("Kindly Note that Today task is ").append(taskDTO.getTitle())
					.append(" with description ").append(taskDTO.getDescription())
					.append(" Nothing that it's priority ").append(taskDTO.getPriority());

			sendMail("TodayTasks", "user@gmail", body.toString());

		}

	}

}
