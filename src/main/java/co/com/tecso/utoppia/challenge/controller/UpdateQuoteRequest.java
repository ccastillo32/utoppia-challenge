package co.com.tecso.utoppia.challenge.controller;

import co.com.tecso.utoppia.challenge.application.UpdateQuoteCommand;

public record UpdateQuoteRequest (
		
	String symbol	
	
) {
	
	public UpdateQuoteCommand toCommand() {
		return UpdateQuoteCommand.of(symbol);
	}
	
}
