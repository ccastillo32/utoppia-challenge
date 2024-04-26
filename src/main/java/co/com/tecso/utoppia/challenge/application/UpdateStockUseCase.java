package co.com.tecso.utoppia.challenge.application;

import org.springframework.stereotype.Service;

import co.com.tecso.utoppia.challenge.domain.GetStockQuotesService;
import co.com.tecso.utoppia.challenge.domain.StockQuoteSaver;

@Service
public class UpdateStockUseCase {

	private GetStockQuotesService stockInfoService;
	private StockQuoteSaver stockSaver;
	
	public UpdateStockUseCase(GetStockQuotesService stockInfoService, StockQuoteSaver stockSaver) {
		this.stockInfoService = stockInfoService;
		this.stockSaver = stockSaver;
	}
	
	public void updateStockInfo(String stockSymbol) {
		
		
		
	}
	
}
