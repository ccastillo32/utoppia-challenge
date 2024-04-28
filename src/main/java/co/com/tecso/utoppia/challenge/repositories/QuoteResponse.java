package co.com.tecso.utoppia.challenge.repositories;

import java.math.BigDecimal;
import java.util.UUID;

import co.com.tecso.utoppia.challenge.domain.StockQuote;
import co.com.tecso.utoppia.challenge.domain.StockQuoteBuilder;

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
	
	public StockQuote toStockQuote(String symbol) {
		return new StockQuoteBuilder()
					.id( generateId() )
					.symbol(symbol)
					.currentPrice(c)
					.change(d)
					.percentChange(dp)
					.highPrice(h)
					.lowPrice(l)
					.openPrice(o)
					.previousClosePrice(pc)
					.build();
	}
	
	private String generateId() {
		return UUID.randomUUID().toString();
	}
	
	private QuoteResponse emptyResponse() {
		return of(BigDecimal.ZERO, null, null, BigDecimal.ZERO, 
				BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0l);
	}
	
	public boolean isEmpty() {
		return emptyResponse().equals(this);
	}
	
}