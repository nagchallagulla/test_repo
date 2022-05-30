package com.zensar.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
	@ExceptionHandler(value=InvalidStockIdException.class)
	public ResponseEntity<Object> handleConflict(RuntimeException exception,WebRequest request){
		String errorMessage="{\"error\":\"" +exception.toString() +"\"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception,errorMessage,new HttpHeaders(),HttpStatus.CONFLICT,request);
		return response;
		
	}
	
	
	
}
