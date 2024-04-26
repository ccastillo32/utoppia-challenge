package co.com.tecso.utoppia.challenge.repositories;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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
	void saveStock() {
		
		String id = UUID.randomUUID().toString();
		String symbol = "APPL";
		BigDecimal currentPrice = new BigDecimal("169.365");
		double change = -0.525;
		double percentChange = -0.309;
		BigDecimal highPrice = new BigDecimal("171.34");
		BigDecimal lowPrice = new BigDecimal("169.19");
		BigDecimal openPrice = new BigDecimal("169.87");
		BigDecimal previousClosePrice = new BigDecimal("169.89");
		LocalDateTime updatedAt = EpochConverter.toLocalDateTime(1714161559);
		
		StockQuote data = StockQuote.of(id, symbol, currentPrice, change, percentChange, highPrice, 
				lowPrice, openPrice, previousClosePrice, updatedAt); // TODO: Move to StockTestData or something
		
		assertDoesNotThrow(() -> {
			persisteceAdapter.save(data);
		});
		
	}
	
}
