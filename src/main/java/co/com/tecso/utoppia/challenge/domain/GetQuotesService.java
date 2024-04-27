package co.com.tecso.utoppia.challenge.domain;

import java.util.Optional;

public interface GetQuotesService {

	Optional<StockQuote> getLatestPricesByStockSymbol( String stockSymbol );
	
}
