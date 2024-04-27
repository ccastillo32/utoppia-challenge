package co.com.tecso.utoppia.challenge.application;

public class NoInformationFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoInformationFoundException() {
		super("We could not find any information about the specified symbol");
	}
	
}
