package com.banquemisr.challeng05.error;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.banquemisr.challeng05.response.ResponseModel;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseBody
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		ResponseModel errorResponse = new ResponseModel();
		errorResponse.setEcode(HttpStatus.BAD_REQUEST.name());

		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		errorResponse.setDescription(e.getMessage());
		errorResponse.setAdditionalInfo(errors);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public ResponseEntity<ResponseModel> handleBusinessException(BusinessException e, WebRequest request) {
		log.error(e.getMessage(), e);
		ResponseModel errorResponse = new ResponseModel();
		errorResponse.setEcode(e.getEcode() != null ? e.getEcode() : HttpStatus.INTERNAL_SERVER_ERROR.name());
		errorResponse.setDescription(e.getMessage());
		errorResponse.setMessage(e.getMessage());
		errorResponse.setAdditionalInfo(e.getExtraResponseData());

		HttpStatus status = e.getHttpStatus() != null ? e.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(errorResponse, status);

	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<ResponseModel> processRuntimeException(Exception e, WebRequest request) {
		log.error(e.getMessage(), e);
		return Optional.of(e).map(o -> o.getCause()).filter(BusinessException.class::isInstance)
				.map(BusinessException.class::cast).map(e1 -> this.handleBusinessException(e1, request))
				.orElseGet(() -> {

					ResponseModel errorResponse = new ResponseModel();
					errorResponse.setEcode(HttpStatus.INTERNAL_SERVER_ERROR.name());
					errorResponse.setDescription(e.getMessage());
					errorResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
					log.error(e.getMessage());
					return new ResponseEntity<ResponseModel>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
				});
	}

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public ResponseEntity<ResponseModel> handle(Throwable e, WebRequest request) {
		log.error(e.getMessage(), e);
		ResponseModel errorResponse = new ResponseModel();
		errorResponse.setEcode(HttpStatus.INTERNAL_SERVER_ERROR.name());
		errorResponse.setDescription(e.getMessage());
		errorResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
		log.error(e.getMessage());
		return new ResponseEntity<ResponseModel>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
