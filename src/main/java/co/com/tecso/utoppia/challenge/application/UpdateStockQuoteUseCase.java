package co.com.tecso.utoppia.challenge.application;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.com.tecso.utoppia.challenge.domain.GetStockQuotesService;
import co.com.tecso.utoppia.challenge.domain.GetStoredQuotesService;
import co.com.tecso.utoppia.challenge.domain.StockQuote;
import co.com.tecso.utoppia.challenge.domain.StockQuoteSaver;

@Service
public class UpdateStockQuoteUseCase {

	private GetStockQuotesService stockInfoService;
	private GetStoredQuotesService storedQuotesService;
	private StockQuoteSaver stockQuoteSaver;
	
	public UpdateStockQuoteUseCase(GetStockQuotesService stockInfoService, GetStoredQuotesService storedQuotesService,
			StockQuoteSaver stockQuoteSaver) {
		this.stockInfoService = stockInfoService;
		this.storedQuotesService = storedQuotesService;
		this.stockQuoteSaver = stockQuoteSaver;
	}

	public void updateStockQuote( UpdateQuoteCommand command ) {
		
		String stockSymbol = command.getSymbol();
		
		Optional<StockQuote> latestPrices = stockInfoService.getLatestPrices(stockSymbol);
		
		if (!latestPrices.isPresent()) {
			return;
		}
		
		Optional<StockQuote> latestStoredToday = storedQuotesService.getLatestStoredQuoteByDate(stockSymbol, LocalDate.now());
		
		if (latestStoredToday.isEmpty()) {
			stockQuoteSaver.save( latestPrices.get() );
			return;
		}
		
		StockQuote updatedInfo = latestPrices.get();
		StockQuote oldRecord = latestStoredToday.get();
		
		StockQuote updatedRecord = StockQuote.of(
				oldRecord.id(), 
				oldRecord.symbol(), 
				updatedInfo.currentPrice(), 
				updatedInfo.change(),
				updatedInfo.percentChange(), 
				updatedInfo.highPrice(), 
				updatedInfo.lowPrice(), 
				updatedInfo.openPrice(), 
				updatedInfo.previousClosePrice(), 
				updatedInfo.updatedAt()
		);
		
		stockQuoteSaver.save(updatedRecord);
		
	}
	
	
	
}
