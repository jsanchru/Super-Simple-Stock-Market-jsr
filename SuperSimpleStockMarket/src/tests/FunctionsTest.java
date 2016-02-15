package tests;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import enums.TradeIndicators;
import exceptions.StockException;
import jpmorgan.Functions;
import jpmorgan.Stock;
import jpmorgan.Trade;

/**
 * @author jorge
 *
 */
public class FunctionsTest {
	private Stock stock;
	private Stock stock2;
	private Stock stock3;
	private Stock stock4;

	private Trade trade;

	@Before
	public void createObjectStock() throws StockException {
		stock = new Stock("TEA", "Common", 0, null, 100);
		stock2 = new Stock("POP", "Common", 8, null, 100);
		stock3 = new Stock("ALE", "Common", 23, null, 60);
		stock4 = new Stock("GIN", "Preferred", 8, new Float(2), 100);

	}

	@Test
	public void testRecordATrade() {
		Date date = new Date();
		trade = new Trade(stock, new Timestamp(date.getTime()), 100, TradeIndicators.BUY, 125);
		Functions.recordATrade(trade);
		assertEquals(1, Functions.trades.size());
	}

	@Test
	public void testRecordATrade2() {
		Date date = new Date();
		trade = new Trade(stock, new Timestamp(date.getTime()), 100, TradeIndicators.BUY, 135);
		Functions.recordATrade(trade);
		assertEquals(2, Functions.trades.get("TEA").size());
	}
	
	@Test
	public void testRecordATrade3() {
		Date date = new Date();
		trade = new Trade(stock2, new Timestamp(date.getTime()), 100, TradeIndicators.BUY, 200);
		Functions.recordATrade(trade);
		assertEquals(1, Functions.trades.get("POP").size());
	}
	
	@Test
	public void testRecordATrade4() {
		Date date = new Date();
		trade = new Trade(stock2, new Timestamp(date.getTime()), 200, TradeIndicators.BUY, 205);
		Functions.recordATrade(trade);
		assertEquals(2, Functions.trades.get("POP").size());
	}
	
	@Test
	public void testGetTradesByStockAndMins1() {
		Date date = new Date();
		trade = new Trade(stock3, new Timestamp(date.getTime()), 200, TradeIndicators.BUY, 205);
		Functions.recordATrade(trade);
		trade = new Trade(stock3, new Timestamp(date.getTime()), 200, TradeIndicators.BUY, 205);
		Functions.recordATrade(trade);
		
		assertEquals(2,Functions.getTradesByStockAndMins("ALE", 2).size());
	}
	
	@Test
	public void testGetTradesByStockAndMins2() throws InterruptedException {
		Date date = new Date();
		trade = new Trade(stock4, new Timestamp(date.getTime()), 200, TradeIndicators.BUY, 205);
		Functions.recordATrade(trade);
		trade = new Trade(stock4, new Timestamp(date.getTime()), 200, TradeIndicators.BUY, 205);
		Functions.recordATrade(trade);
		Thread.sleep(65000);
		assertEquals(0,Functions.getTradesByStockAndMins("GIN", 1).size());
	}
	

}
