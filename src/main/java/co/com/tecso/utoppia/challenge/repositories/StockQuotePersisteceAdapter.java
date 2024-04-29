package co.com.tecso.utoppia.challenge.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import co.com.tecso.utoppia.challenge.application.GetAllStockQuotesQuery;
import co.com.tecso.utoppia.challenge.domain.GetStoredQuotesService;
import co.com.tecso.utoppia.challenge.domain.PagedList;
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
		
		Specification<StockQuoteJpaEntity> spec = getSpecification(
				stockSymbol,
				localDate,
				localDate);
		
		if (spec == null) {
			throw new IllegalArgumentException("Malformed specification");
		}
		
		Optional<StockQuoteJpaEntity> data = repository.findOne( spec );
		
		if (!data.isPresent()) {
			return Optional.empty();
		}
		
		return Optional.of( data.get().toStockQuote() );
		
	}

	@Override
	public PagedList<StockQuote> getAll(GetAllStockQuotesQuery query) {
		
		Pageable pageable = PageRequest.of(query.pageNumber(), query.pageSize());
		
		Specification<StockQuoteJpaEntity> spec = getSpecification(
				query.stockSymbol(),
				query.startDate(),
				query.endDate());
		
		Page<StockQuoteJpaEntity> content = spec != null 
				? repository.findAll( spec, pageable )
				: repository.findAll(pageable);
		
		return getPagedList(content);
		
	}
	
	private PagedList<StockQuote> getPagedList(Page<StockQuoteJpaEntity> content) {
		List<StockQuote> elements = content.getContent()
				.stream()
				.map(StockQuoteJpaEntity::toStockQuote)
				.toList();
		
		return new PagedList<>(elements, 
				content.getTotalElements(), 
				content.getTotalPages(), 
				content.getPageable().getOffset(), 
				content.getPageable().getPageSize()
		);
	}
	
	private Specification<StockQuoteJpaEntity> getSpecification(String stockSymbol, LocalDate startDate, LocalDate endDate) {
		Specification<StockQuoteJpaEntity> spec = null;
		
		if (stockSymbol != null && !stockSymbol.isBlank()) {
			spec = StockQuoteSpecs.bySymbolEqual( stockSymbol );
		}
		
		if (startDate != null) {
			spec = spec != null 
						? spec.and( StockQuoteSpecs.byStartDate(startDate.atStartOfDay()))
						: StockQuoteSpecs.byStartDate(startDate.atStartOfDay());
		}
		
		if (endDate != null) {
			spec = spec != null 
						? spec.and( StockQuoteSpecs.byEndDate(endDate.atTime(23, 59, 59)))
						: StockQuoteSpecs.byEndDate(endDate.atTime(23, 59, 59));
		}
		
		return spec;
	}
	
}
