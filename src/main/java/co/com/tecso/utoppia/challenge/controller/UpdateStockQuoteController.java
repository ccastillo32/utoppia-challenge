package co.com.tecso.utoppia.challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.tecso.utoppia.challenge.application.UpdateQuoteCommand;
import co.com.tecso.utoppia.challenge.application.UpdateStockQuoteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Stock quotes")

@RestController

public class UpdateStockQuoteController {

	private UpdateStockQuoteUseCase useCase;
	
	public UpdateStockQuoteController(UpdateStockQuoteUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/stock-quotes/update")
	@Operation(summary = "Get the latest stock quote and stores it")
	@ApiResponses(value = {
		@ApiResponse( responseCode = "204", description = "Success"),
		@ApiResponse( responseCode = "400", description = "Validation errors")
	})
	
	public ResponseEntity<Void> handleRequest(@RequestBody UpdateQuoteRequest request) {
		
		UpdateQuoteCommand command = request.toCommand();
		
		useCase.updateStockQuote(command);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
