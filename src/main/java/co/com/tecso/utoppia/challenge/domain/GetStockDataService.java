package co.com.tecso.utoppia.challenge.domain;

import java.util.Optional;

public interface GetStockDataService {

	Optional<StockData> getLatestStockData( String symbol );
	
}
