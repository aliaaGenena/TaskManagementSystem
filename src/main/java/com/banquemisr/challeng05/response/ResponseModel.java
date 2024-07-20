package com.banquemisr.challeng05.response;

import java.util.Map;
import lombok.Data;

@Data
public class ResponseModel {

	private String ecode;
	private String message;
	private String description;
	private Map<String, String> additionalInfo;

}
