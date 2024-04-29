package co.com.tecso.utoppia.challenge.domain;

import java.time.LocalDate;
import java.util.Optional;

import co.com.tecso.utoppia.challenge.application.GetAllStockQuotesQuery;

public interface GetStoredQuotesService {

	Optional<StockQuote> getLatestStoredQuoteByDate(String stockSymbol, LocalDate localDate);
	
	PagedList<StockQuote> getAll(GetAllStockQuotesQuery query);
	
}
