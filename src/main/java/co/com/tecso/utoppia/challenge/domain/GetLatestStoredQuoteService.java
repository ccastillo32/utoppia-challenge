package co.com.tecso.utoppia.challenge.domain;

import java.time.LocalDate;
import java.util.Optional;

public interface GetLatestStoredQuoteService {

	Optional<StockQuote> getLatestStoredQuoteByDate(String stockSymbol, LocalDate localDate);
	
}
