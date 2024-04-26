package co.com.tecso.utoppia.challenge.repositories;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import co.com.tecso.utoppia.challenge.domain.StockQuote;
import co.com.tecso.utoppia.challenge.util.EpochConverter;

@DataJpaTest

final class StockPersisteceAdapterTests {

	@Autowired
	private StockQuotePersisteceAdapter persisteceAdapter;
	
	@Test
	void saveStockQuote() {
		
		String id = UUID.randomUUID().toString();
		String symbol = "APPL";
		BigDecimal currentPrice = new BigDecimal("169.365");
		double change = -0.525;
		double percentChange = -0.309;
		BigDecimal highPrice = new BigDecimal("171.34");
		BigDecimal lowPrice = new BigDecimal("169.19");
		BigDecimal openPrice = new BigDecimal("169.87");
		BigDecimal previousClosePrice = new BigDecimal("169.89");
		LocalDateTime updatedAt = EpochConverter.toLocalDateTime(1714161559); // 26/04/2024 at 19:59:19
		
		StockQuote data = StockQuote.of(id, symbol, currentPrice, change, percentChange, highPrice, 
				lowPrice, openPrice, previousClosePrice, updatedAt); // TODO: Move to StockTestData or something
		
		assertDoesNotThrow(() -> {
			persisteceAdapter.save(data);
		});
		
		LocalDate today = LocalDate.of(2024, 04, 26);
		Optional<StockQuote> latestStoredInBD = persisteceAdapter.getLatestStoredQuoteByDate(symbol, today);
		
		Assertions.assertThat( latestStoredInBD ).isPresent();
		assertEquals(latestStoredInBD.get(), data);
		
	}
	
	@Test
	void getLatestStoredQuote() {
		
		String id = UUID.randomUUID().toString();
		String symbol = "APPL";
		BigDecimal currentPrice = new BigDecimal("169.365");
		double change = -0.525;
		double percentChange = -0.309;
		BigDecimal highPrice = new BigDecimal("171.34");
		BigDecimal lowPrice = new BigDecimal("169.19");
		BigDecimal openPrice = new BigDecimal("169.87");
		BigDecimal previousClosePrice = new BigDecimal("169.89");
		LocalDateTime updatedAt = EpochConverter.toLocalDateTime(1714161559); // 26/04/2024 at 19:59:19
		
		StockQuote firstRecord = StockQuote.of(id, symbol, currentPrice, change, percentChange, highPrice, 
				lowPrice, openPrice, previousClosePrice, updatedAt);
		
		currentPrice = new BigDecimal("169.3");
		change = -0.59;
		percentChange = -0.3473;
		updatedAt = EpochConverter.toLocalDateTime(1714161601); // 26/04/2024 at 20:00:01
		
		StockQuote secondRecord = StockQuote.of(id, symbol, currentPrice, change, percentChange, highPrice, 
				lowPrice, openPrice, previousClosePrice, updatedAt);
		
		assertDoesNotThrow(() -> {
			
			persisteceAdapter.save(firstRecord);
			persisteceAdapter.save(secondRecord);
			
		});
		
		LocalDate today = LocalDate.of(2024, 04, 26);
		Optional<StockQuote> latestStoredInBD = persisteceAdapter.getLatestStoredQuoteByDate(symbol, today);
		
		Assertions.assertThat( latestStoredInBD ).isPresent();
		assertEquals(latestStoredInBD.get(), secondRecord);
		
	}
	
}
