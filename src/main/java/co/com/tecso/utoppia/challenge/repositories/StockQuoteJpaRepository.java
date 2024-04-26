package co.com.tecso.utoppia.challenge.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockQuoteJpaRepository extends JpaRepository<StockQuoteJpaEntity, String> {
	
	@Query(value = "SELECT sq from stock_quotes sq "
			 + "where sq.updatedAt BETWEEN :startDate AND :endDate "
			 + "AND sq.symbol = :symbol")
	public StockQuoteJpaEntity getBySymbolBetweenDates(String symbol, LocalDateTime startDate, LocalDateTime endDate);
	
}
