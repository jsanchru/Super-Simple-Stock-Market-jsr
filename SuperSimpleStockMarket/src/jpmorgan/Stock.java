package jpmorgan;

import enums.StockTypes;
import exceptions.StockException;

/**
 * Class Stock contains the information about the stock, moreover,
 * it has methods to calculate PER and dividend Yield.
 * @author jorge sánchez ruiz.
 * @version 1.0.
 */
public class Stock {
	private String symbol;
	private StockTypes  type;
	private int lastDividend;
	private Float fixedDividend;
	private int parValue;
	
	
	
	/**
	 * Constructor to create a new object of Stock.
	 * @param symbol Symbol of the stock.
	 * @param type it can be Common or Preferred.
	 * @param lastDividend Last given dividend.
	 * @param fixedDividend Fixed dividend.
	 * @param parValue Par Value of the stock.
	 * @throws StockException
	 */
	public Stock(String symbol, String type, int lastDividend, Float fixedDividend, int parValue) throws StockException {
		
		this.symbol = symbol;
		setType(type);
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}
	
	/**
	 * Method to calculate the Dividend Yield for a stock passing the price.
	 * @param price Price to calculate the Dividend.
	 * @return The dividend yield.
	 * @throws StockException
	 */
	public float calculateDividendYield(int price) throws StockException{
		if(price==0){
			throw new StockException("The price is 0, Dividend Yield can't be calculated");
		}
		//common
		if(this.type == StockTypes.COMMON){

			return (float)this.lastDividend/price;
		// preferred
		}else{
			return this.fixedDividend*this.parValue/price;
		}
	}
	/**
	 * Method to calculate the PER for a stock passing the price.
	 * @param price Price to calculate the price.
	 * @return PER.
	 * @throws StockException
	 */
	public float calculatePER(int price) throws StockException{
		if(this.lastDividend==0){
			throw new StockException("The last dividend is 0, PER can't be calculated");
		}
		return (float)price/this.lastDividend;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public StockTypes getType() {
		return type;
	}
	public void setType(String type) throws StockException {
		if("Common".equals(type)){
			this.type = StockTypes.COMMON;
		}else if("Preferred".equals(type)){
			this.type = StockTypes.PREFERRED;
		}else{
			throw new StockException("Type is not valid");
		}
		
	}
	public int getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(int lastDividend) {
		this.lastDividend = lastDividend;
	}
	public Float getFixedDividend() {
		return fixedDividend;
	}
	public void setFixedDividend(Float fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	public int getParValue() {
		return parValue;
	}
	public void setParValue(int parValue) {
		this.parValue = parValue;
	}
	@Override
	public String toString() {
		
		return new StringBuffer()
		.append("Stock [symbol=")
		.append(symbol)
		.append(", type=")
		.append(type)
		.append(", lastDividend=")
		.append(lastDividend)
		.append(", fixedDividend=")
		.append(fixedDividend)
		.append(", parValue=")
		.append(parValue).toString();
	}
}
