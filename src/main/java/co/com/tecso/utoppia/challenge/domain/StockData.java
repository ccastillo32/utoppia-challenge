package co.com.tecso.utoppia.challenge.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StockData (
	
	String id,
	String symbol,
	BigDecimal currentPrice,
	double change,
	double percentChange,
	LocalDateTime updatedAt
		
) {
	
	public static StockData of (String id, String symbol, BigDecimal currentPrice, 
			double change, double percentChange, LocalDateTime updatedAt) {
		return new StockData(id, symbol, currentPrice, change, percentChange, updatedAt);
	}
	
}
