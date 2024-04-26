package co.com.tecso.utoppia.challenge.repositories;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.tecso.utoppia.challenge.domain.StockQuote;

@SpringBootTest

final class FinnhubApiClientTests {

	@Autowired
	private FinnhunApiClient finnhunApiClient;
	
	@Test
	void success() {
		
		Optional<StockQuote> result = quote("AAPL");
		Assertions.assertThat(result).isNotEmpty();
		
	}
	
	@Test
	void emptyResponseForUnknownSymbol() {
		
		Optional<StockQuote> result = quote("AAPL22");
		Assertions.assertThat(result).isEmpty();
		
	}
	
	private Optional<StockQuote> quote(String symbol) {
		return finnhunApiClient.getLatestPrices(symbol);
	}
	
}
