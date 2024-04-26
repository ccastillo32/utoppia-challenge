package co.com.tecso.utoppia.challenge.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.com.tecso.utoppia.challenge.application.data.StockQuoteData;
import co.com.tecso.utoppia.challenge.domain.GetStockQuotesService;
import co.com.tecso.utoppia.challenge.domain.GetStoredQuotesService;
import co.com.tecso.utoppia.challenge.domain.StockQuoteSaver;

final class UpdateStockQuoteUseCaseTests {

	private GetStockQuotesService stockQuotesService = Mockito.mock(GetStockQuotesService.class);
	private GetStoredQuotesService storedQuotesService = Mockito.mock(GetStoredQuotesService.class);
	private StockQuoteSaver stockQuoteSaver = Mockito.mock(StockQuoteSaver.class);
	
	private UpdateStockQuoteUseCase useCase = new UpdateStockQuoteUseCase(stockQuotesService, 
			storedQuotesService, stockQuoteSaver);
	
	private static final String AAPL = "AAPL";
	
	@Test
	void shouldCreateANewRecord() {
		
		Mockito.when(stockQuotesService.getLatestPrices(AAPL))
			   .thenReturn( Optional.of(StockQuoteData.firstQueryOfTheDay()) );
		
		Mockito.when( storedQuotesService.getLatestStoredQuoteByDate(AAPL, today()) )
				.thenReturn( Optional.empty() );
		
		useCase.updateStockQuote(AAPL);
		
		Mockito.verify(stockQuoteSaver, Mockito.times(1))
			   .save( StockQuoteData.firstQueryOfTheDay() );
		
	}
	
	@Test
	void shouldUpdateAnExistingRecord() {
		
		Mockito.when(stockQuotesService.getLatestPrices(AAPL))
		   .thenReturn( Optional.of(StockQuoteData.secondQueryOfTheDay()) );
	
		Mockito.when( storedQuotesService.getLatestStoredQuoteByDate(AAPL, today()) )
			.thenReturn( Optional.of( StockQuoteData.firstQueryOfTheDay() ) );
		
		useCase.updateStockQuote(AAPL);
		
		Mockito.verify(stockQuoteSaver, Mockito.times(1))
		   .save( StockQuoteData.latestStoredRecord() );
		
	}
	
	private LocalDate today() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse("2024-04-26", formatter);
	}
	
	
}
