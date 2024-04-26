package co.com.tecso.utoppia.challenge.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import co.com.tecso.utoppia.challenge.domain.StockData;
import co.com.tecso.utoppia.challenge.util.EpochConverter;

public record GetStockDataResponse (
	
	BigDecimal c,
	BigDecimal d,
	Double dp,
	BigDecimal h,
	BigDecimal l,
	BigDecimal o,
	BigDecimal pc,
	long t
		
) {
	
	public static GetStockDataResponse of(BigDecimal c, BigDecimal d, Double dp, BigDecimal h, 
			BigDecimal l, BigDecimal o, BigDecimal pc, long t) {
		return new GetStockDataResponse(c, d, dp, h, l, o, pc, t);
	}
	
	public StockData toStockData(String symbol) {
		return StockData.of(generateId(), symbol, c, dp, dp, parseTime(t));
	}
	
	private String generateId() {
		return UUID.randomUUID().toString();
	}
	
	private LocalDateTime parseTime(long epochTime) {
		return EpochConverter.toLocalDateTime(epochTime);
	}
	
	private GetStockDataResponse emptyResponse() {
		return of(BigDecimal.ZERO, null, null, BigDecimal.ZERO, 
				BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0l);
	}
	
	public boolean isEmpty() {
		return emptyResponse().equals(this);
	}
	
}
