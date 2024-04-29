package co.com.tecso.utoppia.challenge.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import co.com.tecso.utoppia.challenge.application.InvalidCommandException;
import co.com.tecso.utoppia.challenge.application.NoInformationFoundInMarketException;
import co.com.tecso.utoppia.challenge.repositories.NoTokenProvidedException;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidCommandException.class)
	public ResponseEntity<Map<String, Object>> handleInvalidCommandException(InvalidCommandException exception, WebRequest req) {
		
		Map<String, String> errors = exception.getErrors();
		
		Map<String, Object> result = Map.of("errors", errors, "code", "field_validation");
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoInformationFoundInMarketException.class)
	public ResponseEntity<Map<String, Object>> handleNoInformationFoundException(NoInformationFoundInMarketException exception, WebRequest req) {
		
		Map<String, String> errors = Map.of("symbol", exception.getMessage());
		
		Map<String, Object> result = Map.of("errors", errors, "code", "no_information_found");
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NoTokenProvidedException.class)
	public ResponseEntity<Map<String, String>> handlenNoTokenProvidedException(NoTokenProvidedException exception, WebRequest req) {
		
		Map<String, String> error = Map.of("message", exception.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
