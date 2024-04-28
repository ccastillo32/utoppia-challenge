package co.com.tecso.utoppia.challenge.application.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import co.com.tecso.utoppia.challenge.domain.PagedList;
import co.com.tecso.utoppia.challenge.domain.StockQuote;
import co.com.tecso.utoppia.challenge.util.EpochConverter;

public class StockQuoteData {
	
	public static final String AAPL = "AAPL";

	public static StockQuote firstQueryOfTheDay() { // TODO: Refactor methods
		String id = "75b1a0cb-43ee-4b89-99ac-20b340ba54bd";
		BigDecimal currentPrice = new BigDecimal("169.365");
		BigDecimal change = new BigDecimal("-0.525");
		double percentChange = -0.309;
		BigDecimal highPrice = new BigDecimal("171.34");
		BigDecimal lowPrice = new BigDecimal("169.19");
		BigDecimal openPrice = new BigDecimal("169.87");
		BigDecimal previousClosePrice = new BigDecimal("169.89");
		LocalDateTime updatedAt = EpochConverter.toLocalDateTime(1714161559); // 26/04/2024 at 19:59:19
		
		return StockQuote.of(id, AAPL, currentPrice, change, percentChange, highPrice, 
				lowPrice, openPrice, previousClosePrice, updatedAt);
	}
	
	public static StockQuote secondQueryOfTheDay() { // TODO: Refactor methods
		String id = "1021e525-afe5-4543-aaf2-56a3d1c13434";
		BigDecimal currentPrice = new BigDecimal("169.3");
		BigDecimal change = new BigDecimal("-0.59");
		double percentChange = -0.3473;
		BigDecimal highPrice = new BigDecimal("171.34");
		BigDecimal lowPrice = new BigDecimal("169.19");
		BigDecimal openPrice = new BigDecimal("169.87");
		BigDecimal previousClosePrice = new BigDecimal("169.89");
		LocalDateTime updatedAt = EpochConverter.toLocalDateTime(1714161601); // 26/04/2024 at 20:00:01
		
		return StockQuote.of(id, AAPL, currentPrice, change, percentChange, highPrice, 
				lowPrice, openPrice, previousClosePrice, updatedAt);
	}
	
	public static StockQuote lastRecordOfTheDay() { // TODO: Refactor methods
		String id = firstQueryOfTheDay().id();
		BigDecimal currentPrice = secondQueryOfTheDay().currentPrice();
		BigDecimal change = secondQueryOfTheDay().change();
		double percentChange = secondQueryOfTheDay().percentChange();
		BigDecimal highPrice = secondQueryOfTheDay().highPrice();
		BigDecimal lowPrice = secondQueryOfTheDay().lowPrice();
		BigDecimal openPrice = secondQueryOfTheDay().openPrice();
		BigDecimal previousClosePrice = secondQueryOfTheDay().previousClosePrice();
		LocalDateTime updatedAt = secondQueryOfTheDay().updatedAt(); // 26/04/2024 at 19:59:19
		
		return StockQuote.of(id, AAPL, currentPrice, change, percentChange, highPrice, 
				lowPrice, openPrice, previousClosePrice, updatedAt);
	}
	
	public static PagedList<StockQuote> pagedList() {
		
		List<StockQuote> elements = List.of( lastRecordOfTheDay() ); 
		
		return new PagedList<StockQuote>(elements, 1, 1, 0, 0);
		
	}
	
	public static PagedList<StockQuote> emptyPagedList() {
		
		return new PagedList<StockQuote>(Collections.emptyList(), 1, 1, 0, 0);
		
	}
	
}
