package co.com.tecso.utoppia.challenge.application;

import org.springframework.stereotype.Service;

import co.com.tecso.utoppia.challenge.domain.GetStoredQuotesService;
import co.com.tecso.utoppia.challenge.domain.PagedList;
import co.com.tecso.utoppia.challenge.domain.StockQuote;

@Service
public class GetAllStockQuotesService {

	private static final Integer DEFAULT_PAGE_SIZE = 20;
	
	private GetStoredQuotesService getStoredQuotesService;
	
	public GetAllStockQuotesService(GetStoredQuotesService getStoredQuotesService) {
		this.getStoredQuotesService = getStoredQuotesService;
	}
	
	public PagedList<StockQuote> getAllStockQuotes(GetAllStockQuotesQuery query) {
		
		int pageSize = getPageSize(query);
		String stockSymbol = query.stockSymbol();
		
		return stockSymbol == null || stockSymbol.isBlank()
				? getStoredQuotesService.getAll(pageSize, pageSize)
				: getStoredQuotesService.getBySymbol(stockSymbol, query.pageNumber(), pageSize);
		
	}
	
	private int getPageSize(GetAllStockQuotesQuery query) {
		return query.pageSize() == null 
				? DEFAULT_PAGE_SIZE
				: query.pageSize();
	}
	
}
