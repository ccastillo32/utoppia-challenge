package co.com.tecso.utoppia.challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.tecso.utoppia.challenge.application.GetAllStockQuotesQuery;
import co.com.tecso.utoppia.challenge.application.GetAllStockQuotesService;
import co.com.tecso.utoppia.challenge.domain.PagedList;
import co.com.tecso.utoppia.challenge.domain.StockQuote;

@RestController

public class GetAllStockQuotesController {

	private GetAllStockQuotesService useCase;
	
	public GetAllStockQuotesController(GetAllStockQuotesService useCase) {
		this.useCase = useCase;
	}
	
	
	@GetMapping("/api/stock-quotes")
	
	public ResponseEntity<PagedList<StockQuote>> handleReques(
			@RequestParam(required = false) String symbol, 
			@RequestParam(required = false) Integer pageNumber, 
			@RequestParam(required = false) Integer pageSize) {
		
		GetAllStockQuotesQuery query = GetAllStockQuotesQuery.of(symbol, pageNumber, pageSize);
		
		PagedList<StockQuote> pageResults = useCase.getAllStockQuotes(query);
		
		return ResponseEntity.ok(pageResults);
		
	}
	
}
