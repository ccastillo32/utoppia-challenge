package co.com.tecso.utoppia.challenge.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import co.com.tecso.utoppia.challenge.application.InvalidCommandException;
import co.com.tecso.utoppia.challenge.application.NoInformationFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidCommandException.class)
	public ResponseEntity<?> handleInvalidCommandException(InvalidCommandException exception, WebRequest req) {
		
		Map<String, String> errors = exception.getErrors();
		
		Map<String, Object> result = Map.of("errors", errors, "code", "field_validation");
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoInformationFoundException.class)
	public ResponseEntity<?> handleNoInformationFoundException(NoInformationFoundException exception, WebRequest req) {
		
		Map<String, String> errors = Map.of("symbol", exception.getMessage());
		
		Map<String, Object> result = Map.of("errors", errors, "code", "no_information_found");
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		
	}
	
}
