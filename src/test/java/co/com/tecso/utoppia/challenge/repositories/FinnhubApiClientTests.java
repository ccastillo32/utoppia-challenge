package co.com.tecso.utoppia.challenge.repositories;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import co.com.tecso.utoppia.challenge.domain.StockData;

final class FinnhubApiClientTests {

	private FinnhunApiClient finnhunApiClient = new FinnhunApiClient();
	
	@Test
	void success() {
		
		Optional<StockData> result = finnhunApiClient.getLatestStockData("AAPL");
		Assertions.assertThat(result).isNotEmpty();
		
	}
	
}
