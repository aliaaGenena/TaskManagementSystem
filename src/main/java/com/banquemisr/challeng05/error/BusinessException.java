package com.banquemisr.challeng05.error;

import java.util.Map;
import org.springframework.http.HttpStatus;
import com.banquemisr.challeng05.constant.ResponseStatusEnum;
import lombok.Data;

/**
 * General Business exception, should be extended by any custom exception and has its own handler in
 * GeneralExceptionHanler.java
 */
@Data
public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;

	private String ecode;
	private String message;
	private HttpStatus httpStatus;
	private Map<String, String> extraResponseData;
	private ResponseStatusEnum statusEnum;

	public BusinessException() {
	}

	public BusinessException(String message) {
		this.message = message;
	}

	public BusinessException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public BusinessException(String ecode, String message) {
		super();
		this.ecode = ecode;
		this.message = message;
	}

	public BusinessException(int ecode, String message) {
		super();
		this.ecode = String.valueOf(ecode);
		this.message = message;
	}

	public BusinessException(String ecode, String message, HttpStatus httpStatus) {
		super();
		this.ecode = ecode;
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public BusinessException(String message, String ecode, HttpStatus httpStatus,
			Map<String, String> extraResponseData) {
		super();
		this.message = message;
		this.ecode = ecode;
		this.httpStatus = httpStatus;
		this.extraResponseData = extraResponseData;
	}

	public BusinessException(ResponseStatusEnum statusEnum) {
		super();
		this.message = statusEnum.getMessage();
		this.ecode = "" + statusEnum.getEcode();
	}


}
