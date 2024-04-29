package co.com.tecso.utoppia.challenge.application;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(symbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateQuoteCommand other = (UpdateQuoteCommand) obj;
		return Objects.equals(symbol, other.symbol);
	}
	
}
