package com.banquemisr.challeng05.constant;

public enum TaskPriorityEnum {

	HIGH("high"), MEDIUM("medium"), LOW("low");

	private String priority;

	private TaskPriorityEnum(String priority) {
		this.priority = priority;
	}

	public String getPriority() {
		return priority;
	}

}
