package co.com.tecso.utoppia.challenge.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import co.com.tecso.utoppia.challenge.application.data.StockQuoteData;
import co.com.tecso.utoppia.challenge.domain.StockQuote;

final class QuoteResponseTests {

	@Test
	void shouldBuildObject() {
		
		QuoteResponse response = QuoteResponse.of(
				new BigDecimal("169.365"), 
				new BigDecimal("-0.525"), 
				-0.309, 
				new BigDecimal("171.34"), 
				new BigDecimal("169.19"), 
				new BigDecimal("169.87"), 
				new BigDecimal("169.89"), 
				1714161559
		);
		
		StockQuote domain = response.toStockQuote( StockQuoteData.AAPL );
		
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
