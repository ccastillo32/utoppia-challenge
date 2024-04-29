package co.com.tecso.utoppia.challenge.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import co.com.tecso.utoppia.challenge.application.UpdateQuoteCommand;
import co.com.tecso.utoppia.challenge.application.UpdateStockQuoteUseCase;
import co.com.tecso.utoppia.challenge.repositories.StockQuoteJpaRepository;

@WebMvcTest(controllers = UpdateStockQuoteController.class)
final class UpdateStockQuoteControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StockQuoteJpaRepository jpaRepository;

	@MockBean
	private UpdateStockQuoteUseCase useCase;

	 @Test
	 void stockQuoteSuccessfullyUpdate() throws Exception {
		 
		 Gson gson = new Gson();
		 
		 UpdateQuoteRequest body = new UpdateQuoteRequest("MSFT");
		 
		 mockMvc.perform(
			 MockMvcRequestBuilders
			 	.post("/api/stock-quotes/update",
			 			body)
			 	.content( gson.toJson(body) )
			 	.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(	MockMvcResultMatchers.status().isNoContent() );
		 
		 UpdateQuoteCommand command = UpdateQuoteCommand.of("MSFT");
		 
		 then( useCase ).should().updateStockQuote( eq(command) );
		 
	 }
	
}
