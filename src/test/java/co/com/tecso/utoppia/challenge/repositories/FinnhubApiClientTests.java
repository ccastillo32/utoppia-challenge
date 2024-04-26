package co.com.tecso.utoppia.challenge.repositories;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.tecso.utoppia.challenge.domain.StockData;

@SpringBootTest

final class FinnhubApiClientTests {

	@Autowired
	private FinnhunApiClient finnhunApiClient;
	
	@Test
	void success() {
		
		Optional<StockData> result = getStockDataFromAPI("AAPL");
		Assertions.assertThat(result).isNotEmpty();
		
	}
	
	@Test
	void emptyResponseForUnknownSymbol() {
		
		Optional<StockData> result = getStockDataFromAPI("AAPL22");
		Assertions.assertThat(result).isEmpty();
		
	}
	
	private Optional<StockData> getStockDataFromAPI(String symbol) {
		return finnhunApiClient.getLatestStockData(symbol);
	}
	
}
