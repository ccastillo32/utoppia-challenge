package co.com.tecso.utoppia.challenge.domain;

import java.util.Optional;

public interface GetStockService {

	Optional<Stock> getLatestStockData( String symbol );
	
}
