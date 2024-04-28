package co.com.tecso.utoppia.challenge.repositories;

import java.math.BigDecimal;

import co.com.tecso.utoppia.challenge.domain.StockQuote;
import co.com.tecso.utoppia.challenge.domain.StockQuoteBuilder;
import co.com.tecso.utoppia.challenge.util.EpochConverter;

public record FinnhubQuoteResponse (
	
	BigDecimal c,
	BigDecimal d,
	Double dp,
	BigDecimal h,
	BigDecimal l,
	BigDecimal o,
	BigDecimal pc,
	long t
		
) {
	
	public static FinnhubQuoteResponse of(BigDecimal c, BigDecimal d, Double dp, BigDecimal h, 
			BigDecimal l, BigDecimal o, BigDecimal pc, long t) {
		return new FinnhubQuoteResponse(c, d, dp, h, l, o, pc, t);
	}
	
	public StockQuote toStockQuote(String id, String symbol) {
		return new StockQuoteBuilder()
					.id( id )
					.symbol(symbol)
					.currentPrice(c)
					.change(d)
					.percentChange(dp)
					.highPrice(h)
					.lowPrice(l)
					.openPrice(o)
					.previousClosePrice(pc)
					.updatedAt( EpochConverter.toLocalDateTime(t) )
					.build();
	}
	
	private FinnhubQuoteResponse emptyResponse() {
		return of(BigDecimal.ZERO, null, null, BigDecimal.ZERO, 
				BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0l);
	}
	
	public boolean isEmpty() {
		return emptyResponse().equals(this);
	}
	
}