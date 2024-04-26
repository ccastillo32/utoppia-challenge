package co.com.tecso.utoppia.challenge.repositories;

import org.springframework.stereotype.Service;

import co.com.tecso.utoppia.challenge.domain.Stock;
import co.com.tecso.utoppia.challenge.domain.StockSaver;

@Service

public class StockPersisteceAdapter implements StockSaver {

	private StockJpaRepository repository;
	
	public StockPersisteceAdapter(StockJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(Stock stockData) {
		
		StockJpaEntity entity = new StockJpaEntity(stockData);
		repository.save(entity);
		
	}

	
	
}
