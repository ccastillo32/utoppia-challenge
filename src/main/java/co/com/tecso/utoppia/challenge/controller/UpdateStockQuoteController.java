package co.com.tecso.utoppia.challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.tecso.utoppia.challenge.application.UpdateQuoteCommand;
import co.com.tecso.utoppia.challenge.application.UpdateStockQuoteUseCase;

@RestController

public class UpdateStockQuoteController {

	private UpdateStockQuoteUseCase useCase;
	
	public UpdateStockQuoteController(UpdateStockQuoteUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/stock-quotes/update")
	
	public ResponseEntity<Void> handleRequest(@RequestBody UpdateQuoteRequest request) {
		
		UpdateQuoteCommand command = request.toCommand();
		
		useCase.updateStockQuote(command);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
