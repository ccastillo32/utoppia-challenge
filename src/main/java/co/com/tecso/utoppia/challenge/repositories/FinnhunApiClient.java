package co.com.tecso.utoppia.challenge.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.com.tecso.utoppia.challenge.domain.GetStockDataService;
import co.com.tecso.utoppia.challenge.domain.StockData;

@Service

public class FinnhunApiClient implements GetStockDataService {

	@Value("${finnhub.api.endpoint}")
	private String baseURL;
	
	@Value("${finnhub.api.key}")
	private String key;
	
	@Override
	public Optional<StockData> getLatestStockData(String symbol) {
		RestTemplate restTemplate = new RestTemplate();
		String url = getQuoteURL(symbol);
		ResponseEntity<GetStockDataResponse> response = restTemplate.getForEntity(url, GetStockDataResponse.class);
		
		// TODO: If response == 200
		// TODO: Validate empty response
		
		StockData result = response.getBody().toStockData(symbol); 
		return Optional.of(result);
	}
	
	private String getQuoteURL(String symbol) {
		return String.format("%s/quote?symbol=%s&token=%s", 
					baseURL, symbol, key);
	}

}
