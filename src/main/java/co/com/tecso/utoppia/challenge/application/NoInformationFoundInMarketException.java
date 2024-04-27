package co.com.tecso.utoppia.challenge.application;

public class NoInformationFoundInMarketException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoInformationFoundInMarketException() {
		super("We could not find any information about the specified symbol");
	}
	
}
