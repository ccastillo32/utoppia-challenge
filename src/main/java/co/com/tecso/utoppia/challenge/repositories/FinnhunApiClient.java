package co.com.tecso.utoppia.challenge.repositories;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import co.com.tecso.utoppia.challenge.domain.GetStockDataService;
import co.com.tecso.utoppia.challenge.domain.StockData;

@Repository

public class FinnhunApiClient implements GetStockDataService {

	@Override
	public Optional<StockData> getLatestStockData(String symbol) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://finnhub.io/api/v1/quote?symbol=AAPL&token=cojvlkpr01qq4pku97e0cojvlkpr01qq4pku97eg";
		ResponseEntity<GetStockDataResponse> response = restTemplate.getForEntity(url, GetStockDataResponse.class);
		
		// TODO: If response == 200
		// TODO: Validate empty response
		
		StockData result = response.getBody().toStockData(symbol); 
		return Optional.of(result);
	}

}
