package co.com.tecso.utoppia.challenge.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import co.com.tecso.utoppia.challenge.domain.StockQuote;
import co.com.tecso.utoppia.challenge.util.EpochConverter;

public record QuoteResponse (
	
	BigDecimal c,
	BigDecimal d,
	Double dp,
	BigDecimal h,
	BigDecimal l,
	BigDecimal o,
	BigDecimal pc,
	long t
		
) {
	
	public static QuoteResponse of(BigDecimal c, BigDecimal d, Double dp, BigDecimal h, 
			BigDecimal l, BigDecimal o, BigDecimal pc, long t) {
		return new QuoteResponse(c, d, dp, h, l, o, pc, t);
	}
	
	public StockQuote toStockData(String symbol) {
		return StockQuote.of(generateId(), symbol, c, dp, dp, h, l, o, pc, parseTime(t));
	}
	
	private String generateId() {
		return UUID.randomUUID().toString();
	}
	
	private LocalDateTime parseTime(long epochTime) {
		return EpochConverter.toLocalDateTime(epochTime);
	}
	
	private QuoteResponse emptyResponse() {
		return of(BigDecimal.ZERO, null, null, BigDecimal.ZERO, 
				BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0l);
	}
	
	public boolean isEmpty() {
		return emptyResponse().equals(this);
	}
	
}