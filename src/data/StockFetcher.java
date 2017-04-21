package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockFetcher {

	//gets stock by ticker symbol
	public static Stock getStock(String symbol) {
		String sym = symbol.toUpperCase();
		double price = 0.0;
		double pe = 0.0;
		double week52low = 0.0;
		double week52high = 0.0;
		double daylow = 0.0;
		double dayhigh = 0.0;
		double movingav50day = 0.0;
		double marketcap = 0.0;
		String name = "";
		String currency = "";
		double open = 0.0;
		double previousClose = 0.0;
		String exchange;
		try {

			// Retrieve CSV File
			URL yahoo = new URL("http://finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=l1vr2ejkghm3j3nc4s7pox");
			URLConnection connection = yahoo.openConnection();
			InputStreamReader is = new InputStreamReader(connection.getInputStream());
			BufferedReader br = new BufferedReader(is);

			// Parse CSV Into Array
			String line = br.readLine();
			// Only split on commas that aren't in quotes
			String[] stockinfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

			price = StockHelper.handleDouble(stockinfo[0]);
			// 1st is volume (double)
			pe = StockHelper.handleDouble(stockinfo[2]);
			// 3rd is eps (double)
			week52low = StockHelper.handleDouble(stockinfo[4]);
			week52high = StockHelper.handleDouble(stockinfo[5]);
			daylow = StockHelper.handleDouble(stockinfo[6]);
			dayhigh = StockHelper.handleDouble(stockinfo[7]);
			movingav50day = StockHelper.handleDouble(stockinfo[8]);
			marketcap = StockHelper.handleDouble(stockinfo[9]);
			name = stockinfo[10].replace("\"", "");
			currency = stockinfo[11].replace("\"", "");
			// 12th is shortRatio (double)
			previousClose = StockHelper.handleDouble(stockinfo[13]);
			open = StockHelper.handleDouble(stockinfo[14]);
			// 15th is exchange
			// exchange = stockinfo[15].replace("\"", "");

		} catch (IOException e) {
			Logger log = Logger.getLogger(StockFetcher.class.getName());
			log.log(Level.SEVERE, e.toString(), e);
			return null;
		}

		return new Stock(sym, price, pe, week52low, week52high, daylow, dayhigh, movingav50day, marketcap, name,
				previousClose, open);

	}
	
	//returns map of twenty stocks from ticker to Stock object
	public static Map<String, Stock> getStocks(){
		Map<String, Stock> map = new HashMap<String, Stock>();
		
		String[] stocks = {"AMZN", "FB", "MSFT", "INTC", "TSLA", "NFLX", "SNAP", "YELP", "GOOG", "AAPL", "NKE", "PYPL", "CSCO", "SPY", "BAC", "T", "GE", "VXX", "T", "EEM"};
		
		for(String s: stocks){
			map.put(s, getStock(s));
		}
		
		return map;
	}
}