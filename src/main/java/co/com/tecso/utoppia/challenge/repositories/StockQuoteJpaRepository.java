package co.com.tecso.utoppia.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StockQuoteJpaRepository extends JpaRepository<StockQuoteJpaEntity, String>, JpaSpecificationExecutor<StockQuoteJpaEntity> {
}
