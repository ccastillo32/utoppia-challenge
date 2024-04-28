package co.com.tecso.utoppia.challenge.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.com.tecso.utoppia.challenge.domain.GetQuotesService;
import co.com.tecso.utoppia.challenge.domain.StockQuote;

@Service

public class FinnhunApiClient implements GetQuotesService {

	@Value("${finnhub.api.endpoint}")
	private String baseURL;
	
	@Value("${finnhub.api.key}")
	private String key;
	
	private static final String QUOTE = "{baseURL}/quote?symbol={symbol}&token={token}";
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public Optional<StockQuote> getLatestPricesByStockSymbol(String symbol) {
		
		ResponseEntity<FinnhubQuoteResponse> response = callAPI(symbol);
		
		if ( !(response.getStatusCode().equals(HttpStatus.OK))) {
			return Optional.empty();
		}
		
		FinnhubQuoteResponse responseBody = response.getBody();
		
		return processResponse(responseBody, symbol);
		
	}
	
	private String getQuoteURL(String symbol) {
			return QUOTE.replace("{baseURL}", baseURL)
						.replace("{symbol}", symbol)
						.replace("{token}", key);
	}
	
	private ResponseEntity<FinnhubQuoteResponse> callAPI(String symbol) {
		String url = getQuoteURL(symbol);
		return restTemplate.getForEntity(url, FinnhubQuoteResponse.class);
	}
	
	private Optional<StockQuote> processResponse(FinnhubQuoteResponse responseBody, String symbol) {
		if (responseBody == null || responseBody.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of( responseBody.toStockQuote(generateId(), symbol) );
	}
	
	private String generateId() {
		return UUID.randomUUID().toString();
	}

}