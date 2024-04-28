package co.com.tecso.utoppia.challenge.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import co.com.tecso.utoppia.challenge.application.data.AAPLStockQuoteTestData;
import co.com.tecso.utoppia.challenge.domain.PagedList;
import co.com.tecso.utoppia.challenge.domain.StockQuote;

@DataJpaTest

final class StockPersisteceAdapterTests {

	@Autowired
	private StockQuotePersisteceAdapter persisteceAdapter;
	
	@Test
	void shouldSaveAndGetTheFirstQueryMadeToday() {
		
		StockQuote firstRecord = AAPLStockQuoteTestData.firstQueryOfTheDay();
		
		saveToBD(firstRecord);
		
		Optional<StockQuote> lastRecordStored = getLastRecordSavedTodayForAAPL();
		
		Assertions.assertThat( lastRecordStored ).isPresent();
		assertEquals(lastRecordStored.get(), firstRecord);
		
	}
	
	@Test
	void shouldRefreshTheLatestStoredData() {
		
		StockQuote firstRecord = AAPLStockQuoteTestData.firstQueryOfTheDay();
		StockQuote secondRecord = AAPLStockQuoteTestData.lastRecordOfTheDay();
		
		saveToBD( firstRecord );
		saveToBD( secondRecord );
		
		Optional<StockQuote> lastRecordStored = getLastRecordSavedTodayForAAPL();
		
		Assertions.assertThat( lastRecordStored ).isPresent();
		assertEquals(lastRecordStored.get(), secondRecord);
		
	}
	
	@Test
	void queriesMadeYesterdayShouldRemain() {
		
		StockQuote yesterdayData = AAPLStockQuoteTestData.yesterdaysLastData();
		
		saveToBD(yesterdayData);
		
		StockQuote todaysEntry = AAPLStockQuoteTestData.firstQueryOfTheDay();
		
		saveToBD(todaysEntry);
		
		Optional<StockQuote> lastRecordStoredYesterday = getLastRecordSavedYesterdayForAAPL();
		Optional<StockQuote> lastRecordStoredToday = getLastRecordSavedTodayForAAPL();
		
		Assertions.assertThat( lastRecordStoredYesterday ).isPresent();
		Assertions.assertThat( lastRecordStoredToday ).isPresent();
		
		assertEquals(lastRecordStoredYesterday.get(), yesterdayData);
		assertEquals(lastRecordStoredToday.get(), todaysEntry);
		
	}
	
	@Test
	void findAll() {
		
		StockQuote firstQuery = AAPLStockQuoteTestData.firstQueryOfTheDay();
		
		persisteceAdapter.save(firstQuery);
		
		PagedList<StockQuote> page = persisteceAdapter.getAll(0, 10);
		
		assertEquals(1, page.getTotalElements());
		
	}
	
	@Test
	void findBySimbol() {
		
		StockQuote firstQuery = AAPLStockQuoteTestData.firstQueryOfTheDay();
		
		persisteceAdapter.save(firstQuery);
		
		PagedList<StockQuote> page = persisteceAdapter.getBySymbol(AAPLStockQuoteTestData.AAPL, 0, 10);
		
		assertEquals(1, page.getTotalElements());
		
	}
	
	private void saveToBD( StockQuote record ) {
		persisteceAdapter.save( record );
	}
	
	private Optional<StockQuote> getLastRecordSavedTodayForAAPL() {
		LocalDate today = LocalDate.of(2024, 04, 26);
		return persisteceAdapter.getLatestStoredQuoteByDate(AAPLStockQuoteTestData.AAPL, today);
	}
	
	private Optional<StockQuote> getLastRecordSavedYesterdayForAAPL() {
		LocalDate yesterday = LocalDate.of(2024, 04, 25);
		return persisteceAdapter.getLatestStoredQuoteByDate(AAPLStockQuoteTestData.AAPL, yesterday);
	}
	
}