package co.com.tecso.utoppia.challenge.application;

import java.time.LocalDate;

public record GetAllStockQuotesQuery (
	
	String stockSymbol,
	Integer pageNumber,
	Integer pageSize,
	LocalDate startDate,
	LocalDate endDate
		
) {
	
	private static final Integer DEFAULT_PAGE_SIZE = 20;
	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	
	public static GetAllStockQuotesQuery of(String stockSymbol, Integer pageNumber, Integer pageSize, 
			LocalDate startDate, LocalDate endDate) {
		int size = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
		int number = pageNumber == null ? DEFAULT_PAGE_NUMBER : pageNumber;
		
		return new GetAllStockQuotesQuery(stockSymbol, number, size, startDate, endDate);
	}
	
	public boolean hasParameters() {
		return (stockSymbol != null && !stockSymbol.isBlank())
			|| startDate != null
			|| endDate != null;
	}
	
}
