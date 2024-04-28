package co.com.tecso.utoppia.challenge.application;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.com.tecso.utoppia.challenge.application.data.AAPLStockQuoteTestData;
import co.com.tecso.utoppia.challenge.domain.GetQuotesService;
import co.com.tecso.utoppia.challenge.domain.GetStoredQuotesService;
import co.com.tecso.utoppia.challenge.domain.StockQuoteSaver;

final class UpdateStockQuoteUseCaseTests {

	private GetQuotesService stockQuotesService = Mockito.mock(GetQuotesService.class);
	private GetStoredQuotesService storedQuotesService = Mockito.mock(GetStoredQuotesService.class);
	private StockQuoteSaver stockQuoteSaver = Mockito.mock(StockQuoteSaver.class);
	
	private UpdateStockQuoteUseCase useCase = new UpdateStockQuoteUseCase(stockQuotesService, 
			storedQuotesService, stockQuoteSaver);
	
	private static final String AAPL = "AAPL";
	
	@Test
	void shouldCreateANewRecord() {
		
		Mockito.when(stockQuotesService.getLatestPricesByStockSymbol(AAPL))
			   .thenReturn( Optional.of(AAPLStockQuoteTestData.firstQueryOfTheDay()) );
		
		Mockito.when( storedQuotesService.getLatestStoredQuoteByDate(AAPL, today()) )
				.thenReturn( Optional.empty() );
		
		UpdateQuoteCommand command = UpdateQuoteCommand.of(AAPL);
		useCase.updateStockQuote(command);
		
		Mockito.verify(stockQuoteSaver, Mockito.times(1))
			   .save( AAPLStockQuoteTestData.firstQueryOfTheDay() );
		
	}
	
	@Test
	void shouldUpdateAnExistingRecord() {
		
		Mockito.when(stockQuotesService.getLatestPricesByStockSymbol(AAPL))
		   .thenReturn( Optional.of(AAPLStockQuoteTestData.secondQueryOfTheDay()) );
	
		Mockito.when( storedQuotesService.getLatestStoredQuoteByDate(AAPL, today()) )
			.thenReturn( Optional.of( AAPLStockQuoteTestData.firstQueryOfTheDay() ) );
		
		UpdateQuoteCommand command = UpdateQuoteCommand.of(AAPL);
		useCase.updateStockQuote(command);
		
		Mockito.verify(stockQuoteSaver, Mockito.times(1))
		   .save( AAPLStockQuoteTestData.lastRecordOfTheDay() );
		
	}
	
	@Test
	void checkUnknownSymbol() {
		
		Mockito.when(stockQuotesService.getLatestPricesByStockSymbol(AAPL))
		   .thenReturn( Optional.empty() );
		
		UpdateQuoteCommand command = UpdateQuoteCommand.of(AAPL);
		
		assertThrows(NoInformationFoundInMarketException.class, () -> {
			useCase.updateStockQuote(command);
		});
		
	}
	
	private LocalDate today() {
		return LocalDate.now();
	}
	
	
}
