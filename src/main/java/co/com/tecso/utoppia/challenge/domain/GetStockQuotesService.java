package co.com.tecso.utoppia.challenge.domain;

import java.util.Optional;

public interface GetStockQuotesService {

	Optional<StockQuote> getLatestPrices( String stockSymbol );
	
}
