package co.com.tecso.utoppia.challenge.repositories;

public class NoTokenProvidedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "Service unavailable. Please try again later";
	
	public NoTokenProvidedException() {
		super(MESSAGE);
	}
	
}
