package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.StockException;
import jpmorgan.Stock;

public class StockTest {

	private Stock stock;
	private Stock stock2;
	private Stock stock3;
	private Stock stock4;
	private Stock stock5;
	
	@Before 
	public void createObjectStock() throws StockException{
		stock = new Stock("TEA","Common",0,null,100);
		stock2 = new Stock("POP","Common",8,null,100);
		stock3 = new Stock("ALE","Common",23,null,60);
		stock4 = new Stock("GIN","Preferred",8,new Float(2),100);
		stock5 = new Stock("JOE","Common",13,null,250);
	}
	@Test
	public void testCalculateDividendYield1() throws StockException {
		assertEquals(0,stock.calculateDividendYield(1),0.00000001);
	}
	
	@Test
	public void testCalculateDividendYield2() throws StockException {
		assertEquals(2,stock2.calculateDividendYield(4),0.00000001);
	}
	
	@Test
	public void testCalculateDividendYield3() throws StockException {
		assertEquals(5.75,stock3.calculateDividendYield(4),0.00000001);
	}
	
	@Test
	public void testCalculateDividendYield4() throws StockException {
		assertEquals(0.5,stock4.calculateDividendYield(4),0.00000001);
	}
	
	@Test
	public void testCalculateDividendYield4b() throws StockException {
		assertEquals(0.2,stock4.calculateDividendYield(10),0.00000001);
	}
	
	@Test
	public void testCalculateDividendYield5() throws StockException {
		assertEquals(6.5,stock5.calculateDividendYield(2),0.00000001);
	}
	
	@Test(expected=StockException.class)
	public void testCalculateDividendYield6() throws StockException {
		assertEquals(6.5,stock5.calculateDividendYield(0),0.00000001);
	}

	@Test(expected=StockException.class)
	public void testCalculatePER() throws StockException {
		assertEquals(0,stock.calculatePER(1),0.00000001);		
	}
	
	@Test
	public void testCalculatePER2() throws StockException {
		assertEquals(0.125,stock2.calculatePER(1),0.00000001);		
	}
	
	@Test
	public void testCalculatePER3() throws StockException {
		assertEquals(0.869565,stock3.calculatePER(2),0.000001);		
	}

}
