package co.com.tecso.utoppia.challenge.repositories;

import org.springframework.stereotype.Service;

import co.com.tecso.utoppia.challenge.domain.StockQuote;
import co.com.tecso.utoppia.challenge.domain.StockQuoteSaver;

@Service

public class StockQuotePersisteceAdapter implements StockQuoteSaver {

	private StockQuoteJpaRepository repository;
	
	public StockQuotePersisteceAdapter(StockQuoteJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(StockQuote stockQuote) {
		
		StockQuoteJpaEntity entity = new StockQuoteJpaEntity(stockQuote);
		repository.save(entity);
		
	}
	
}
