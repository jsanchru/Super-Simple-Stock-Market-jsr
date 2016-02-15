package exceptions;

/**
 * Customized exception to show messages to the user.
 * @author jorge sánchez ruiz.
 * @version 1.0.
 */
@SuppressWarnings("serial")
public class StockException extends Exception {
	public StockException(String msg) {
        super(msg);
    }
}
