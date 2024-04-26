package co.com.tecso.utoppia.challenge.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Stock (
	
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
	
	public static Stock of (
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
		return new Stock(id, symbol, currentPrice, change, percentChange, highPrice, lowPrice,
					openPrice, previousClosePrice, updatedAt);
	}
	
}
