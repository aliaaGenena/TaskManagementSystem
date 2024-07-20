package com.banquemisr.challeng05.constant;

public enum TaskStatusEnum {

	TODO("todo"), IN_PROGRESS("in progress"), DONE("done");

	private final String status;

	TaskStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
