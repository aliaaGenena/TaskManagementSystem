package com.banquemisr.challeng05.constant;

public enum ResponseStatusEnum {

	SUCCESS(200, "SUCCESS"), SERVER_ERROR(500, "SERVER_ERROR"), INVALIDREQUEST(400, "INVALID_REQUEST"),
	NOTFOUND(501, "NOT_EXIST"), ALREADYFOUND(209, "ALREADY_EXIST");

	private int ecode;
	private String message;

	private ResponseStatusEnum(int ecode, String message) {
		this.ecode = ecode;
		this.message = message;
	}

	public int getEcode() {
		return ecode;
	}

	public String getMessage() {
		return message;
	}

	public static ResponseStatusEnum getResponseStatusEnum(int ecode) {
		for (ResponseStatusEnum item : values()) {
			if (item.ecode == ecode)
				return item;
		}
		return null;
	}

	public static String getMessage(int ecode) {
		for (ResponseStatusEnum item : values()) {
			if (item.ecode == ecode)
				return item.message;
		}
		return "GENERAL_ERROR";
	}

}
