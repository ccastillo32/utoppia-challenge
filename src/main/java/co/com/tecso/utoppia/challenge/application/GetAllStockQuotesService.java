package co.com.tecso.utoppia.challenge.application;

import org.springframework.stereotype.Service;

import co.com.tecso.utoppia.challenge.domain.GetStoredQuotesService;
import co.com.tecso.utoppia.challenge.domain.PagedList;
import co.com.tecso.utoppia.challenge.domain.StockQuote;

@Service
public class GetAllStockQuotesService {

	private GetStoredQuotesService getStoredQuotesService;
	
	public GetAllStockQuotesService(GetStoredQuotesService getStoredQuotesService) {
		this.getStoredQuotesService = getStoredQuotesService;
	}
	
	public PagedList<StockQuote> getAllStockQuotes(GetAllStockQuotesQuery query) {
		
		String stockSymbol = query.stockSymbol();
		
		return stockSymbol == null || stockSymbol.isBlank()
				? getStoredQuotesService.getAll(query.pageNumber(), query.pageSize())
				: getStoredQuotesService.getBySymbol(stockSymbol, query.pageNumber(), query.pageSize());
		
	}
	
	
	
}
