package co.com.tecso.utoppia.challenge.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

final class UpdateStockQuoteControllerTests {

	 @Autowired
	 private TestRestTemplate restTemplate;

	 @Test
	 void stockQuoteSuccessfullyUpdate() {
		 
		 UpdateQuoteRequest body = new UpdateQuoteRequest("MSFT");
		 
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Content-Type", "application/json");
		 HttpEntity<UpdateQuoteRequest> request = new HttpEntity<>(body, headers);
		 
		 ResponseEntity<Void> response = restTemplate.exchange(
	                "/api/stock-quotes/update",
	                HttpMethod.POST,
	                request,
	                Void.class
	     );
		 
		 assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		 
	 }
	
	
	
}
