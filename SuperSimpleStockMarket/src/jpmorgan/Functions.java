package jpmorgan;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that contains some functions such as record a trade, get the trades for 
 * a stock in the last X min, GBCE and Volume Weighted Stock Price.
 * @author jorge sánchez ruiz
 * @version 1.0
 */
public class Functions {

	public static Map<String, List<Trade>> trades = new HashMap<String, List<Trade>>();

	/**
	 * Record a trade, with timestamp, quantity of shares, 
	 * buy or sell indicator and traded price
	 * @param trade trade to be recorded.
	 */
	public static void recordATrade(Trade trade) {
		if (trades.containsKey(trade.getStock().getSymbol())) {
			(trades.get(trade.getStock().getSymbol())).add(trade);
		} else {
			List<Trade> listTradesByStock = new ArrayList<>();
			listTradesByStock.add(trade);
			trades.put(trade.getStock().getSymbol(), listTradesByStock);
		}
	}

	/**
	 * Returns a list with the trades for a stock in past XX minutes.
	 * @param stockName Name of the stock.
	 * @param mins minutes to be considered.
	 * @return List with the trades.
	 */
	public static List<Trade> getTradesByStockAndMins(String stockName, int mins) {

		List<Trade> list = trades.get(stockName);
		List<Trade> listResult = new ArrayList<>();

		Date date = new java.util.Date();
		long milis = mins * 60 * 1000;
		for (Trade trade : list) {
			if (mins == 0 || trade.getTimestamp().getTime() > date.getTime() - milis) {
				listResult.add(trade);
			}
		}
		return listResult;
	}

	/**
	 * Calculate Volume Weighted Stock Price based on trades in past XX minutes
	 * @param stockName Name of the stock.
	 * @param mins minutes to be considered.
	 * @return Volume Weighted Stock Price.
	 */
	public static double doVolumeWeightedStockPrice(String stockName, int mins) {
		double result = 0;
		long quantity = 0;
		List<Trade> list = getTradesByStockAndMins(stockName, mins);
		for (Trade trade : list) {
			result += trade.getPrice() * trade.getQuantity();
			quantity += trade.getQuantity();
		}
		return result / quantity;
	}

	/**
	 * Calculate the GBCE All Share Index 
	 * using the geometric mean of prices for all stocks.
	 * @return GBCE All Share Index.
	 */
	public static double calculateGBCE() {
		double result = 1;
		int i = 0;
		for (String stockName : trades.keySet()) {
			result *= doVolumeWeightedStockPrice(stockName, 0);
			i++;
		}
		return Math.pow(result, (double)1/i);
	}
}
