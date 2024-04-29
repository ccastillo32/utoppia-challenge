package co.com.tecso.utoppia.challenge.application;

public record GetAllStockQuotesQuery (
	
	String stockSymbol,
	Integer pageNumber,
	Integer pageSize
		
) {
	
	private static final Integer DEFAULT_PAGE_SIZE = 20;
	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	
	public static GetAllStockQuotesQuery of(String stockSymbol, Integer pageNumber, Integer pageSize) {
		int size = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
		int number = pageNumber == null ? DEFAULT_PAGE_NUMBER : pageNumber;
		
		return new GetAllStockQuotesQuery(stockSymbol, number, size);
	}
	
	public boolean hasParameters() {
		return (stockSymbol != null && !stockSymbol.isBlank());
	}
	
}
