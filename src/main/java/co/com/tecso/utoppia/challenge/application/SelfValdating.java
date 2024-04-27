package co.com.tecso.utoppia.challenge.application;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public abstract class SelfValdating<T> {

	private Validator validator;
	
	protected SelfValdating() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@SuppressWarnings("unchecked")
	protected void validateSelf() {
		
		Set<ConstraintViolation<T>> errors = validator.validate((T) this);
		
		Map<String, String> result = new HashMap<>();
		
		for (ConstraintViolation<T> error : errors) {
			String name = error.getPropertyPath().toString();
			String message = error.getMessage();
			
			result.put(name, message);
		}
		
		if (!result.isEmpty()) {
			throw InvalidCommandException.of(result);
		}
		
	}
	
}
