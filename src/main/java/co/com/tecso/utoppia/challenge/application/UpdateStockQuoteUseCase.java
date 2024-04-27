package co.com.tecso.utoppia.challenge.application;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.com.tecso.utoppia.challenge.domain.GetQuotesService;
import co.com.tecso.utoppia.challenge.domain.GetStoredQuotesService;
import co.com.tecso.utoppia.challenge.domain.StockQuote;
import co.com.tecso.utoppia.challenge.domain.StockQuoteSaver;

@Service
public class UpdateStockQuoteUseCase {

	private GetQuotesService getQuotesService;
	private GetStoredQuotesService getStoredQuotesService;
	private StockQuoteSaver stockQuoteSaver;
	
	public UpdateStockQuoteUseCase(GetQuotesService stockInfoService, GetStoredQuotesService storedQuotesService,
			StockQuoteSaver stockQuoteSaver) {
		this.getQuotesService = stockInfoService;
		this.getStoredQuotesService = storedQuotesService;
		this.stockQuoteSaver = stockQuoteSaver;
	}

	public void updateStockQuote( UpdateQuoteCommand command ) {
		
		String stockSymbol = command.getSymbol();
		
		StockQuote latestQuoteFromMarket = getLatestPriceFromMarket(stockSymbol);
		
		Optional<StockQuote> latestQuoteStoredToday = getLatestPriceStoredToday(stockSymbol);
		
		if (latestQuoteStoredToday.isEmpty()) {
			saveStockQuote( latestQuoteFromMarket );
			return;
		}
		
		updateTodaysStockQuote(latestQuoteStoredToday.get(), latestQuoteFromMarket);
		
	}
	
	private StockQuote getLatestPriceFromMarket(String stockSymbol) {
		return getQuotesService.getLatestPricesByStockSymbol(stockSymbol)
							   .orElseThrow(NoInformationFoundInMarketException::new);
	}
	
	private Optional<StockQuote> getLatestPriceStoredToday(String stockSymbol) {
		return getStoredQuotesService.getLatestStoredQuoteByDate(stockSymbol, LocalDate.now());
	}
	
	private void saveStockQuote(StockQuote quote) {
		stockQuoteSaver.save( quote );
	}
	
	private void updateTodaysStockQuote(StockQuote oldInfo, StockQuote newInfo) {
		StockQuote recordToUpdate = newInfo.replaceId(oldInfo.id()); 
		stockQuoteSaver.save( recordToUpdate );
	}
	
}
