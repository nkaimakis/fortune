package data;

import java.io.Serializable;

public class Stock implements Serializable {

	private String symbol;
	private double price;
	private double pe;
	private double week52low;
	private double week52high;
	private double daylow;
	private double dayhigh;
	private double movingav50day;
	private double marketcap;
	private String name;
	private double previousClose;
	private double open;
	private double growth;
	private int amountOwned = 0;

	public Stock(String symbol, double price, double pe, double week52low, double week52high, double daylow,
			double dayhigh, double movingav50day, double marketcap, String name, double previousClose, double open) {
		this.symbol = symbol;
		this.price = price;
		this.pe = pe;
		this.week52low = week52low;
		this.week52high = week52high;
		this.daylow = daylow;
		this.dayhigh = dayhigh;
		this.movingav50day = movingav50day;
		this.marketcap = marketcap;
		this.name = name;
		this.previousClose = previousClose;
		this.open = open;
		this.growth = ((price - previousClose) / previousClose) * 100;
	}

	public void setAmountOwned(int amount) {
		this.amountOwned = amount;
	}

	public double getPreviousClose() {
		return this.previousClose;
	}

	public double getOpen() {
		return this.open;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public double getPrice() {
		return this.price;
	}

	public double getPe() {
		return this.pe;
	}

	public double getWeek52low() {
		return this.week52low;
	}

	public double getWeek52high() {
		return this.week52high;
	}

	public double getDaylow() {
		return this.daylow;
	}

	public double getDayhigh() {
		return this.dayhigh;
	}

	public double getMovingav50day() {
		return this.movingav50day;
	}

	public double getMarketcap() {
		return this.marketcap;
	}

	public String getName() {
		return this.name;
	}

	public void printInfo() {
		System.out.println(symbol);
		System.out.println(price);

	}

	public double percentChange() {
		double change = ((this.price - this.previousClose) / this.previousClose) * 100;
		return change;
	}

	public double getGrowth() {
		return growth;
	}

	public void setGrowth(double growth) {
		this.growth = growth;
	}

	public int getAmountOwned() {
		return amountOwned;
	}

}