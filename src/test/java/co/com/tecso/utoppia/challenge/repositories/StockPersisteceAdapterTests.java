package co.com.tecso.utoppia.challenge.repositories;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import co.com.tecso.utoppia.challenge.application.data.StockQuoteData;
import co.com.tecso.utoppia.challenge.domain.StockQuote;

@DataJpaTest

final class StockPersisteceAdapterTests {

	@Autowired
	private StockQuotePersisteceAdapter persisteceAdapter;
	
	@Test
	void saveTodaysInfo() {
		
		StockQuote dataToBePersisted = StockQuoteData.firstQueryOfTheDay();
		
		assertDoesNotThrow(() -> {
			persisteceAdapter.save(dataToBePersisted);
		});
		
		LocalDate today = LocalDate.of(2024, 04, 26);
		Optional<StockQuote> latestStoredData = persisteceAdapter.getLatestStoredQuoteByDate(StockQuoteData.AAPL, today);
		
		Assertions.assertThat( latestStoredData ).isPresent();
		assertEquals(latestStoredData.get(), dataToBePersisted);
		
	}
	
	@Test
	void updateTodaysInfo() {
		
		StockQuote firstQuery = StockQuoteData.firstQueryOfTheDay();
		StockQuote expectedResult = StockQuoteData.lastRecordOfTheDay();
		
		assertDoesNotThrow(() -> {
			
			persisteceAdapter.save(firstQuery);
			persisteceAdapter.save(expectedResult);
			
		});
		
		LocalDate today = LocalDate.of(2024, 04, 26);
		Optional<StockQuote> latestStoredData = persisteceAdapter.getLatestStoredQuoteByDate(StockQuoteData.AAPL, today);
		
		Assertions.assertThat( latestStoredData ).isPresent();
		assertEquals(latestStoredData.get(), expectedResult);
		
	}
	
}