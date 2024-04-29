package co.com.tecso.utoppia.challenge.domain;

import java.time.LocalDate;
import java.util.Optional;

public interface GetStoredQuotesService {

	Optional<StockQuote> getLatestStoredQuoteByDate(String stockSymbol, LocalDate localDate);
	
	PagedList<StockQuote> getAll(String symbol, int pageNumber, int pageLimit);
	
}
