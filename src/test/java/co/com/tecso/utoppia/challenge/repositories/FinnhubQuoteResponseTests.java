package co.com.tecso.utoppia.challenge.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import co.com.tecso.utoppia.challenge.application.data.AAPLFinnhubQuoteResponseData;
import co.com.tecso.utoppia.challenge.application.data.StockQuoteData;
import co.com.tecso.utoppia.challenge.domain.StockQuote;

final class FinnhubQuoteResponseTests {

	@Test
	void shouldBuildObject() {
		
		FinnhubQuoteResponse response = AAPLFinnhubQuoteResponseData.firstQueryOfTheDay();
		
		String id = StockQuoteData.firstQueryOfTheDay().id();
		StockQuote domain = response.toStockQuote( id, StockQuoteData.AAPL );
		
		StockQuote expected = StockQuoteData.firstQueryOfTheDay();
		
		assertEquals(expected.currentPrice(), domain.currentPrice());
		assertEquals(expected.change(), domain.change());
		assertEquals(expected.percentChange(), domain.percentChange());
		assertEquals(expected.highPrice(), domain.highPrice());
		assertEquals(expected.lowPrice(), domain.lowPrice());
		assertEquals(expected.openPrice(), domain.openPrice());
		assertEquals(expected.previousClosePrice(), domain.previousClosePrice());
		
	}
	
}
