package co.com.tecso.utoppia.challenge.application;

import jakarta.validation.constraints.NotBlank;

public class UpdateQuoteCommand extends SelfValdating<UpdateQuoteCommand> {
	
	@NotBlank(message = "symbol cannot be empty")
	private String symbol;
	
	public UpdateQuoteCommand (String symbol) {
		this.symbol = symbol;
		validateSelf();
	}
	
	public static UpdateQuoteCommand of(String symbol) {
		return new UpdateQuoteCommand(symbol);
	}

	public String getSymbol() {
		return symbol;
	}
	
}
