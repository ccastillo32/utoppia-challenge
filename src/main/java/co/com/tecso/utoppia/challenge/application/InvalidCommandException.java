package co.com.tecso.utoppia.challenge.application;

import java.util.Map;

public class InvalidCommandException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final Map<String, String> errors;
	
	public InvalidCommandException(Map<String, String> errors) {
		this.errors = errors;
	}
	
	public static InvalidCommandException of(Map<String, String> errors) {
		return new InvalidCommandException(errors);
	}

	public Map<String, String> getErrors() {
		return errors;
	}
	
}
