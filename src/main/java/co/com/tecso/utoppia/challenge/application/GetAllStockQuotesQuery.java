package co.com.tecso.utoppia.challenge.application;

public record GetAllStockQuotesQuery (
	
	String stockSymbol,
	Integer pageNumber,
	Integer pageSize
		
) {
	
	public static GetAllStockQuotesQuery of(String stockSymbol, Integer pageNumber, Integer pageSize) {
		return new GetAllStockQuotesQuery(stockSymbol, pageNumber, pageSize);
	}
	
}
