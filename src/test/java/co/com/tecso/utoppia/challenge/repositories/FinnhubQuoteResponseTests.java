package co.com.tecso.utoppia.challenge.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import co.com.tecso.utoppia.challenge.application.data.AAPLFinnhubQuoteResponseData;
import co.com.tecso.utoppia.challenge.application.data.AAPLStockQuoteTestData;
import co.com.tecso.utoppia.challenge.domain.StockQuote;

final class FinnhubQuoteResponseTests {

	@Test
	void shouldBuildDomainObjectFromTheAPIResponse() {
		
		FinnhubQuoteResponse response = AAPLFinnhubQuoteResponseData.firstQueryOfTheDay();
		
		String id = AAPLStockQuoteTestData.firstQueryOfTheDay().id();
		StockQuote domain = response.toStockQuote( id, AAPLStockQuoteTestData.AAPL );
		
		StockQuote expected = AAPLStockQuoteTestData.firstQueryOfTheDay();
		
		assertEquals(expected.currentPrice(), domain.currentPrice());
		assertEquals(expected.change(), domain.change());
		assertEquals(expected.percentChange(), domain.percentChange());
		assertEquals(expected.highPrice(), domain.highPrice());
		assertEquals(expected.lowPrice(), domain.lowPrice());
		assertEquals(expected.openPrice(), domain.openPrice());
		assertEquals(expected.previousClosePrice(), domain.previousClosePrice());
		
	}
	
}
