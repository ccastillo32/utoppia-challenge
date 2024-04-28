package co.com.tecso.utoppia.challenge.application.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import co.com.tecso.utoppia.challenge.domain.PagedList;
import co.com.tecso.utoppia.challenge.domain.StockQuote;
import co.com.tecso.utoppia.challenge.domain.StockQuoteBuilder;

public class AAPLStockQuoteTestData {
	
	public static final String AAPL = "AAPL";

	public static StockQuote firstQueryOfTheDay() {
		String id = "75b1a0cb-43ee-4b89-99ac-20b340ba54bd";
		
		return AAPLFinnhubQuoteResponseData
				.firstQueryOfTheDay()
				.toStockQuote(id, AAPL);
	}
	
	public static StockQuote secondQueryOfTheDay() {
		String id = "1021e525-afe5-4543-aaf2-56a3d1c13434";
		
		return AAPLFinnhubQuoteResponseData
				.secondQueryOfTheDay()
				.toStockQuote(id, AAPL);
	}
	
	public static StockQuote lastRecordOfTheDay() {
		
		String id = firstQueryOfTheDay().id();
		BigDecimal currentPrice = secondQueryOfTheDay().currentPrice();
		BigDecimal change = secondQueryOfTheDay().change();
		double percentChange = secondQueryOfTheDay().percentChange();
		BigDecimal highPrice = secondQueryOfTheDay().highPrice();
		BigDecimal lowPrice = secondQueryOfTheDay().lowPrice();
		BigDecimal openPrice = secondQueryOfTheDay().openPrice();
		BigDecimal previousClosePrice = secondQueryOfTheDay().previousClosePrice();
		LocalDateTime updatedAt = secondQueryOfTheDay().updatedAt();
		
		return new StockQuoteBuilder()
					.id(id)
					.symbol(AAPL)
					.currentPrice(currentPrice)
					.change(change)
					.percentChange(percentChange)
					.highPrice(highPrice)
					.lowPrice(lowPrice)
					.openPrice(openPrice)
					.previousClosePrice(previousClosePrice)
					.updatedAt(updatedAt)
					.build();
		
	}
	
	public static PagedList<StockQuote> pagedList() {
		
		List<StockQuote> elements = List.of( lastRecordOfTheDay() ); 
		
		return new PagedList<StockQuote>(elements, 1, 1, 0, 0);
		
	}
	
	public static PagedList<StockQuote> emptyPagedList() {
		
		return new PagedList<StockQuote>(Collections.emptyList(), 1, 1, 0, 0);
		
	}
	
}
