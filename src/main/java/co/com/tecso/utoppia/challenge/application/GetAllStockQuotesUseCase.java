package co.com.tecso.utoppia.challenge.application;

import org.springframework.stereotype.Service;

import co.com.tecso.utoppia.challenge.domain.GetStoredQuotesService;
import co.com.tecso.utoppia.challenge.domain.PagedList;
import co.com.tecso.utoppia.challenge.domain.StockQuote;

@Service
public class GetAllStockQuotesUseCase {

	private GetStoredQuotesService getStoredQuotesService;
	
	public GetAllStockQuotesUseCase(GetStoredQuotesService getStoredQuotesService) {
		this.getStoredQuotesService = getStoredQuotesService;
	}
	
	public PagedList<StockQuote> getAllStockQuotes(GetAllStockQuotesQuery query) {
		
		String stockSymbol = query.stockSymbol();
		return getStoredQuotesService.getAll(stockSymbol, query.pageNumber(), query.pageSize());
		
	}
	
	
	
}
