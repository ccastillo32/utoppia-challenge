package co.com.tecso.utoppia.challenge.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StockQuoteBuilder {
	
	private String id;
	private String symbol;
	private BigDecimal currentPrice;
	private BigDecimal change;
	private double percentChange;
	private BigDecimal highPrice;
	private BigDecimal lowPrice;
	private BigDecimal openPrice;
	private BigDecimal previousClosePrice;
	private LocalDateTime updatedAt;
	
	public StockQuoteBuilder id(String id) {
		this.id = id;
		return this;
	}
	
	public StockQuoteBuilder symbol(String symbol) {
		this.symbol = symbol;
		return this;
	}
	
	public StockQuoteBuilder currentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
		return this;
	}
	
	public StockQuoteBuilder change(BigDecimal change) {
		this.change = change;
		return this;
	}
	
	public StockQuoteBuilder percentChange(double percentChange) {
		this.percentChange = percentChange;
		return this;
	}
	
	public StockQuoteBuilder highPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
		return this;
	}
	
	public StockQuoteBuilder lowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
		return this;
	}
	
	public StockQuoteBuilder openPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
		return this;
	}
	
	public StockQuoteBuilder previousClosePrice(BigDecimal previousClosePrice) {
		this.previousClosePrice = previousClosePrice;
		return this;
	}
	
	public StockQuoteBuilder updatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	
	public StockQuote build() {
		return new StockQuote(id, symbol, currentPrice, change, percentChange, highPrice, lowPrice, 
				openPrice, previousClosePrice, updatedAt);
	}
	
}
