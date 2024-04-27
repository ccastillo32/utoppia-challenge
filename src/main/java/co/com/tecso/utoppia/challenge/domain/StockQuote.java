package co.com.tecso.utoppia.challenge.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StockQuote (
	
	String id,
	String symbol,
	BigDecimal currentPrice,
	double change,
	double percentChange,
	BigDecimal highPrice,
	BigDecimal lowPrice,
	BigDecimal openPrice,
	BigDecimal previousClosePrice,
	LocalDateTime updatedAt
		
) {
	
	public static StockQuote of (
			String id,
			String symbol,
			BigDecimal currentPrice,
			double change,
			double percentChange,
			BigDecimal highPrice,
			BigDecimal lowPrice,
			BigDecimal openPrice,
			BigDecimal previousClosePrice,
			LocalDateTime updatedAt
	) {
		return new StockQuote(id, symbol, currentPrice, change, percentChange, highPrice, lowPrice,
					openPrice, previousClosePrice, updatedAt);
	}
	
	public StockQuote replaceId(String id) {
		return new StockQuote(id, symbol, currentPrice, change, percentChange, highPrice, lowPrice,
				openPrice, previousClosePrice, updatedAt);
	}
	
}
