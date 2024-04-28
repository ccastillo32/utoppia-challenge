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
import co.com.tecso.utoppia.challenge.domain.PagedList;
import co.com.tecso.utoppia.challenge.domain.StockQuote;

@DataJpaTest

final class StockPersisteceAdapterTests {

	@Autowired
	private StockQuotePersisteceAdapter persisteceAdapter;
	
	@Test
	void shouldSaveAndGetTheFirstQueryMadeToday() {
		
		StockQuote firstQuery = StockQuoteData.firstQueryOfTheDay();
		
		persisteceAdapter.save(firstQuery);
		
		LocalDate today = LocalDate.of(2024, 04, 26);
		Optional<StockQuote> latestStoredData = persisteceAdapter.getLatestStoredQuoteByDate(StockQuoteData.AAPL, today);
		
		Assertions.assertThat( latestStoredData ).isPresent();
		assertEquals(latestStoredData.get(), firstQuery);
		
	}
	
	@Test
	void shouldRefreshTheLatestStoredData() {
		
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
	
	@Test
	void findAll() {
		
		StockQuote firstQuery = StockQuoteData.firstQueryOfTheDay();
		
		persisteceAdapter.save(firstQuery);
		
		PagedList<StockQuote> page = persisteceAdapter.getAll(0, 10);
		
		assertEquals(1, page.getTotalElements());
		
	}
	
	@Test
	void findBySimbol() {
		
		StockQuote firstQuery = StockQuoteData.firstQueryOfTheDay();
		
		persisteceAdapter.save(firstQuery);
		
		PagedList<StockQuote> page = persisteceAdapter.getBySymbol(StockQuoteData.AAPL, 0, 10);
		
		assertEquals(1, page.getTotalElements());
		
	}
	
	
}