package co.com.tecso.utoppia.challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.tecso.utoppia.challenge.application.GetAllStockQuotesQuery;
import co.com.tecso.utoppia.challenge.application.GetAllStockQuotesService;
import co.com.tecso.utoppia.challenge.domain.PagedList;
import co.com.tecso.utoppia.challenge.domain.StockQuote;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Stock quotes")

@RestController

public class GetAllStockQuotesController {

	private GetAllStockQuotesService useCase;
	
	public GetAllStockQuotesController(GetAllStockQuotesService useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/stock-quotes")
	@Operation(summary = "Get all the stored stock quotes")
	@ApiResponses(value = {
		@ApiResponse( responseCode = "200", description = "Success")
	})
	
	public ResponseEntity<PagedList<StockQuote>> handleReques(
			@Parameter(description = "Stock symbol")
			@RequestParam(required = false) String symbol,
			
			@Parameter(description = "Number of the page")
			@RequestParam(required = false) Integer pageNumber,
			
			@Parameter(description = "Total records per page")
			@RequestParam(required = false) Integer pageSize) {
		
		GetAllStockQuotesQuery query = GetAllStockQuotesQuery.of(symbol, pageNumber, pageSize);
		
		PagedList<StockQuote> pageResults = useCase.getAllStockQuotes(query);
		
		return ResponseEntity.ok(pageResults);
		
	}
	
}
