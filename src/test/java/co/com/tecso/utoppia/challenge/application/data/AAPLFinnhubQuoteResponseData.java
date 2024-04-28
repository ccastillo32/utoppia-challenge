package co.com.tecso.utoppia.challenge.application.data;

import java.math.BigDecimal;

import co.com.tecso.utoppia.challenge.repositories.FinnhubQuoteResponse;

public class AAPLFinnhubQuoteResponseData {

	public static FinnhubQuoteResponse firstQueryOfTheDay() {
		
		BigDecimal currentPrice = new BigDecimal("169.365");
		BigDecimal change = new BigDecimal("-0.525");
		double percentChange = -0.309;
		BigDecimal highPrice = new BigDecimal("171.34");
		BigDecimal lowPrice = new BigDecimal("169.19");
		BigDecimal openPrice = new BigDecimal("169.87");
		BigDecimal previousClosePrice = new BigDecimal("169.89");
		long time = 1714161559;
		
		return new FinnhubQuoteResponse(currentPrice, change, percentChange, highPrice, lowPrice, openPrice, previousClosePrice, time);	
		
	}
	
	public static FinnhubQuoteResponse secondQueryOfTheDay() {
		
		BigDecimal currentPrice = new BigDecimal("169.3");
		BigDecimal change = new BigDecimal("-0.59");
		double percentChange = -0.3473;
		BigDecimal highPrice = new BigDecimal("171.34");
		BigDecimal lowPrice = new BigDecimal("169.19");
		BigDecimal openPrice = new BigDecimal("169.87");
		BigDecimal previousClosePrice = new BigDecimal("169.89");
		long time = 1714161601;
		
		return new FinnhubQuoteResponse(currentPrice, change, percentChange, highPrice, lowPrice, openPrice, previousClosePrice, time);	
		
	}
	
	public static FinnhubQuoteResponse yesterdaysLastEntry() {
		
		BigDecimal currentPrice = new BigDecimal("406.32");
		BigDecimal change = new BigDecimal("7.28");
		double percentChange = 1.8244;
		BigDecimal highPrice = new BigDecimal("413");
		BigDecimal lowPrice = new BigDecimal("405.7601");
		BigDecimal openPrice = new BigDecimal("412.41");
		BigDecimal previousClosePrice = new BigDecimal("399.04");
		long time = 1714076250;
		
		return new FinnhubQuoteResponse(currentPrice, change, percentChange, highPrice, lowPrice, openPrice, previousClosePrice, time);	
		
	}
	
}
