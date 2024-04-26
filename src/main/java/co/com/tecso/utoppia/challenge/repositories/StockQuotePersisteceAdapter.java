package co.com.tecso.utoppia.challenge.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.com.tecso.utoppia.challenge.domain.GetStoredQuotesService;
import co.com.tecso.utoppia.challenge.domain.StockQuote;
import co.com.tecso.utoppia.challenge.domain.StockQuoteSaver;

@Service

public class StockQuotePersisteceAdapter implements StockQuoteSaver, GetStoredQuotesService {

	private StockQuoteJpaRepository repository;
	
	public StockQuotePersisteceAdapter(StockQuoteJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(StockQuote stockQuote) {
		
		StockQuoteJpaEntity entity = new StockQuoteJpaEntity(stockQuote);
		repository.save(entity);
		
	}

	@Override
	public Optional<StockQuote> getLatestStoredQuoteByDate(String stockSymbol, LocalDate localDate) {
		
		LocalDateTime startDate = localDate.atStartOfDay();
		LocalDateTime endDate = localDate.atTime(23, 59, 59);
		StockQuoteJpaEntity entity = repository.getBySymbolBetweenDates(stockSymbol, startDate, endDate);
		
		return entity != null 
					? Optional.of( entity.toStockQuote() )
					: Optional.empty();
		
	}
	
}
