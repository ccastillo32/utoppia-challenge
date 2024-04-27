package co.com.tecso.utoppia.challenge.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import co.com.tecso.utoppia.challenge.application.InvalidCommandException;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(InvalidCommandException.class)
	public ResponseEntity<?> handleException(InvalidCommandException exception, WebRequest req) {
		
		Map<String, String> errors = exception.getErrors();
		
		Map<String, Object> result = Map.of("errors", errors, "errorType", "validation");
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		
	}
	
}
