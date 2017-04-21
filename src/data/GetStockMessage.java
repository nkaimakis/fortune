package data;

import java.io.Serializable;
import java.util.Vector;

public class GetStockMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3912269560915544476L;
	public Vector<String> tickers;

	public GetStockMessage(Vector<String> tickers) {
		this.tickers = tickers;
	}

	public Vector<String> getTickers() {
		return this.tickers;
	}

	public void addTicker(String ticker) {
		tickers.add(ticker);
	}
}
