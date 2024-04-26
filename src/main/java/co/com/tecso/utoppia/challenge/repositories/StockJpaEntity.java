package co.com.tecso.utoppia.challenge.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import co.com.tecso.utoppia.challenge.domain.Stock;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "stocks")

public class StockJpaEntity {

	@Id
	private String id;
	private String symbol;
	
	@Column(name = "current_price")
	private BigDecimal currentPrice;
	
	private Double change;
	
	@Column(name = "percent_change")
	private Double percentChange;
	
	@Column(name = "high_price")
	private BigDecimal highPrice;
	
	@Column(name = "low_price")
	private BigDecimal lowPrice;
	
	@Column(name = "open_price")
	private BigDecimal openPrice;
	
	@Column(name = "previous_close_price")
	private BigDecimal previousClosePrice;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	public StockJpaEntity() {}
	
	public StockJpaEntity(Stock stock) {
		setId(stock.id());
		setSymbol(stock.symbol());
		setCurrentPrice(stock.currentPrice());
		setChange(stock.change());
		setPercentChange(stock.percentChange());
		setHighPrice(stock.highPrice());
		setLowPrice(stock.lowPrice());
		setOpenPrice(stock.openPrice());
		setPreviousClosePrice(stock.previousClosePrice());
		setUpdatedAt(stock.updatedAt());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Double getChange() {
		return change;
	}

	public void setChange(Double change) {
		this.change = change;
	}

	public Double getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(Double percentChange) {
		this.percentChange = percentChange;
	}

	public BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public BigDecimal getPreviousClosePrice() {
		return previousClosePrice;
	}

	public void setPreviousClosePrice(BigDecimal previousClosePrice) {
		this.previousClosePrice = previousClosePrice;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
