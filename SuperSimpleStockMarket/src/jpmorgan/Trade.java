package jpmorgan;

import java.sql.Timestamp;

import enums.TradeIndicators;

/**
 * Class Trade contains the data of the transaction in the market.
 * @author jorge sánchez ruiz.
 * @version 1.0.
 */
public class Trade {
	private Stock stock;
	private Timestamp timestamp;
	private long quantity;
	private TradeIndicators indicator;
	private float price;
	
	
	/**
	 * Constructor to create a new object of class Trade.
	 * @param stock
	 * @param timestamp
	 * @param quantity
	 * @param indicator
	 * @param price
	 */
	public Trade(Stock stock, Timestamp timestamp, long quantity, TradeIndicators indicator, float price) {
		this.stock = stock;
		this.timestamp = timestamp;
		this.quantity = quantity;
		this.indicator = indicator;
		this.price = price;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public TradeIndicators getIndicator() {
		return indicator;
	}
	public void setIndicator(TradeIndicators indicator) {
		this.indicator = indicator;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
}
