package data;

import java.io.Serializable;
import java.sql.Timestamp;

public class LeaderTransaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String investor_username;
	private String investee_username;
	private int amount_invested;
	private double investee_price;
	private Timestamp time;
	private boolean isSell;
	private boolean isSuccess;

	public LeaderTransaction(String investor_username, String investee_username, int amount_invested,
			double investee_price, Timestamp time, boolean isSell) {
		super();
		this.investor_username = investor_username;
		this.investee_username = investee_username;
		this.amount_invested = amount_invested;
		this.investee_price = investee_price;
		this.time = time;
		this.isSell = isSell;
	}

	public String getInvestor_username() {
		return investor_username;
	}

	public String getInvestee_username() {
		return investee_username;
	}

	public int getAmount_invested() {
		return amount_invested;
	}

	public double getInvestee_price() {
		return investee_price;
	}

	public Timestamp getTime() {
		return time;
	}

	public boolean isSell() {
		return isSell;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setInvestor_username(String investor_username) {
		this.investor_username = investor_username;
	}

	public void setInvestee_username(String investee_username) {
		this.investee_username = investee_username;
	}

	public void setAmount_invested(int amount_invested) {
		this.amount_invested = amount_invested;
	}

	public void setInvestee_price(float investee_price) {
		this.investee_price = investee_price;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public void setSell(boolean isSell) {
		this.isSell = isSell;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}