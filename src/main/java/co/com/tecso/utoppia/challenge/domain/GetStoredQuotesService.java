package co.com.tecso.utoppia.challenge.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GetStoredQuotesService {

	Optional<StockQuote> getLatestStoredQuoteByDate(String stockSymbol, LocalDate localDate);
	
	List<StockQuote> getAll();
	
}
