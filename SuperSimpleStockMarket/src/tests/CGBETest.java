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

public class CGBETest {
	
	private Stock stock3;
	private Stock stock4;
	private Trade trade3;

	@Before
	public void createObjectStock() throws StockException {
		stock3 = new Stock("ALE", "Common", 23, null, 60);
		stock4 = new Stock("GIN", "Preferred", 8, new Float(2), 100);

		Date date = new Date();
		trade3 = new Trade(stock3, new Timestamp(date.getTime()), 100, TradeIndicators.BUY, 25);
		Functions.recordATrade(trade3);
		trade3 = new Trade(stock3, new Timestamp(date.getTime()), 100, TradeIndicators.BUY, 35);
		Functions.recordATrade(trade3);
		
		trade3 = new Trade(stock4, new Timestamp(date.getTime()), 100, TradeIndicators.BUY, 125);
		Functions.recordATrade(trade3);
		trade3 = new Trade(stock4, new Timestamp(date.getTime()), 200, TradeIndicators.BUY, 135);
		Functions.recordATrade(trade3);
		}
	@Test
	public void testCalculateGBCE(){
		assertEquals(62.84902, Functions.calculateGBCE(),0.0001);
	}

}
