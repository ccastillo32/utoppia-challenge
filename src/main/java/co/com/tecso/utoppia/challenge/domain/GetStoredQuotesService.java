package co.com.tecso.utoppia.challenge.domain;

import java.time.LocalDate;
import java.util.Optional;

public interface GetStoredQuotesService {

	Optional<StockQuote> getLatestStoredQuoteByDate(String stockSymbol, LocalDate localDate);
	
	PagedList<StockQuote> getAll(int pageNumber, int pageLimit);
	
	PagedList<StockQuote> getBySymbol(String symbol, int pageNumber, int pageLimit);
	
}
