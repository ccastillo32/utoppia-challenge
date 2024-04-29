package co.com.tecso.utoppia.challenge.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.com.tecso.utoppia.challenge.application.data.AAPLStockQuoteTestData;
import co.com.tecso.utoppia.challenge.domain.GetStoredQuotesService;
import co.com.tecso.utoppia.challenge.domain.PagedList;
import co.com.tecso.utoppia.challenge.domain.StockQuote;

final class GetAllStockQuotesUseCaseTests {

	private GetStoredQuotesService getStoredQuotesService = Mockito.mock(GetStoredQuotesService.class);
	private GetAllStockQuotesUseCase useCase = new GetAllStockQuotesUseCase(getStoredQuotesService);
	
	@Test
	void findBySymbol() {
		
		Mockito.when( getStoredQuotesService.getAll( AAPLStockQuoteTestData.AAPL, 0, 10))
		       .thenReturn( AAPLStockQuoteTestData.pagedList() );
		
		GetAllStockQuotesQuery query = GetAllStockQuotesQuery.of(AAPLStockQuoteTestData.AAPL, 0, 10);
		
		PagedList<StockQuote> data = useCase.getAllStockQuotes(query);
		
		assertEquals(1, data.getTotalElements());
		
	}
	
}
