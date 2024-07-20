package com.banquemisr.challeng05.error;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



	  @ExceptionHandler(BusinessException.class)
	  @ResponseBody
	  public ResponseEntity<ResponseModel> handleBusinessException(BusinessException e, WebRequest request) {
		  log.error(e.getMessage(), e);
		   if (e.getExtraResponseData() != null && !e.getExtraResponseData().isEmpty()) {
			   ResponseModel errorResponse = new ResponseModel();
			      errorResponse.setAdditionalInfo(e.getExtraResponseData());
			      errorResponse.setDescription(e.getMessage());
			      errorResponse.setEcode(e.getEcode());
			      if (e.getHttpStatus() == null) {
			        errorResponse.setMessage(e.getMessage());
			        errorResponse.setEcode(HttpStatus.INTERNAL_SERVER_ERROR.name());
			        
			        return new ResponseEntity<ResponseModel>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			      }
			      errorResponse.setEcode(e.getHttpStatus().name());
			      errorResponse.setMessage(e.getMessage());
			      return new ResponseEntity<ResponseModel>(errorResponse, e.getHttpStatus());
			    } else {
			    	ResponseModel errorResponse = new ResponseModel();
			      errorResponse.setDescription(e.getMessage());
			      errorResponse.setEcode(e.getEcode());
			      if (e.getHttpStatus() == null) {
			    	errorResponse.setMessage(e.getMessage());
			    	errorResponse.setEcode(HttpStatus.INTERNAL_SERVER_ERROR.name());
			        return new ResponseEntity<ResponseModel>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			      }
			      errorResponse.setMessage(e.getMessage());
			      errorResponse.setEcode(e.getHttpStatus().name());
			      return new ResponseEntity<ResponseModel>(errorResponse, e.getHttpStatus());
			    }

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
