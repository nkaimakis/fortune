package data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String ticker;
	private int amount;
	private double price;
	private Date time;
	private boolean isSell;
	private boolean isSuccess;

	public Transaction(String username, String ticker, int amount, double price, boolean isSell) {
		this.time = new Timestamp(System.currentTimeMillis());
		this.username = username.toLowerCase();
		this.ticker = ticker.toLowerCase();
		this.amount = amount;
		this.price = price;
		this.isSell = isSell;
		this.isSuccess = false;
	}

	public Transaction(String username, String ticker, int amount, double price, boolean isSell, String timeStamp)
			throws ParseException {
		this.time = new Timestamp(System.currentTimeMillis());
		this.username = username;
		this.ticker = ticker;
		this.amount = amount;
		this.price = price;
		this.isSell = isSell;
		this.isSuccess = false;
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date lFromDate1 = datetimeFormatter1.parse(timeStamp);
		this.time = lFromDate1;
	}

	public Transaction(String username, String ticker, int amount, double price, boolean isSell, Timestamp ts)
			throws ParseException {
		this.time = new Timestamp(System.currentTimeMillis());
		this.username = username;
		this.ticker = ticker;
		this.amount = amount;
		this.price = price;
		this.isSell = isSell;
		this.isSuccess = false;
		this.time = ts;
	}

	public String getUser() {
		return username;
	}

	public void setUser(String username) {
		this.username = username;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getTime() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.time);
		return timeStamp;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public boolean isSell() {
		return isSell;
	}

	public void setBuy(boolean isSell) {
		this.isSell = isSell;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void printTransaction() {
		System.out.println(username);
		System.out.println(ticker);
		System.out.println(price);
		System.out.println(amount);
		System.out.println(time);
	}
}
