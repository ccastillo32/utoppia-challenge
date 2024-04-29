package co.com.tecso.utoppia.challenge.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

public class StockQuoteSpecs {

	private StockQuoteSpecs() {}
	
	static Specification<StockQuoteJpaEntity> bySymbolEqual(String symbol) {
        return (root, query, builder) -> builder.equal(root.get("symbol"), symbol);
    }
	
	static Specification<StockQuoteJpaEntity> byStartDate(LocalDateTime startDate) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("updatedAt"), startDate);
    }

	static Specification<StockQuoteJpaEntity> byEndDate(LocalDateTime endDate) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("updatedAt"), endDate);
    }
	
}
