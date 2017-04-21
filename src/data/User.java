package data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	public String username;
	public String password;
	public String email;
	public String fname;
	public String lname;
	public double balance;
	private boolean isGuest;
	/*
	 * public Map<Stock, Integer> stocksOwned = null;
	 */ public Map<String, Integer> stocksOwned = null;
	public Map<String, Double> usersOwned = null;

	public User(String fname, String lname, String username, String password, String email, double balance,
			Map<String, Integer> stocksOwned, Map<String, Double> usersOwned) { // constructor?
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.balance = balance;
		this.stocksOwned = stocksOwned;
		this.usersOwned = usersOwned;
		this.isGuest = false;
	}

	public User(String fname, String lname, String username, String password, String email, double balance) { // constructor?
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.balance = balance;
		this.isGuest = false;

		this.stocksOwned = new HashMap<String, Integer>();
		this.usersOwned = new HashMap<String, Double>();
	}

	public User(boolean isGuest) {
		this.isGuest = isGuest;
	}

	public boolean isGuest() {
		return this.isGuest;
	}

	public void buyStock(Stock s, int amount) {
		if (this.stocksOwned.containsKey(s)) {
			int total = amount + this.stocksOwned.get(s);
			this.stocksOwned.put(s.getSymbol(), total);
		} else {
			this.stocksOwned.put(s.getSymbol(), amount);
		}
		this.balance -= amount * s.getPrice();
		// need to call right MySQLDriver Methods
	}

	public void buyUser(String user, double amount) {
		if (this.usersOwned.containsKey(user)) {
			double total = amount + this.usersOwned.get(user);
			this.usersOwned.put(user, total);
		} else {
			this.usersOwned.put(user, amount);
		}
		this.balance -= amount;
		// need to call right MySQLDriver Method
	}

	public void sellUser(String user, double amount) {
		// amount user is able to sell is limited on front end
		if (amount == this.usersOwned.get(user)) {
			// if amount to sell = amount owned, remove from map
			this.usersOwned.remove(user);
		} else {
			// else update value
			double total = this.usersOwned.get(user) - amount;
			this.usersOwned.put(user, total);
		}
		this.balance += amount;
		// need to call right MySQLDriver Method
	}

	public void sellStock(Stock s, int amount) {
		// amount user is able to sell is limited on front end
		if (amount == this.stocksOwned.get(s)) {
			// if amount to sell = amount owned, remove from map
			this.stocksOwned.remove(s);
		} else {
			// else update value
			int total = this.stocksOwned.get(s) - amount;
			this.stocksOwned.put(s.getSymbol(), total);
		}
		this.balance += amount * s.getPrice();
		// need to call right MySQLDriver Methods
	}

	// returns int of count of stocks owned
	public int getAmountOwned(Stock s) {
		if (this.stocksOwned.containsKey(s))
			return this.stocksOwned.get(s);
		else
			return 0;
	}

	//
	public double getValue(Stock s) {
		if (this.stocksOwned.containsKey(s))
			return (this.stocksOwned.get(s) * s.getPrice());
		else
			return 0;
	}

	// returns a double for amount owned of username given
	public double getAmountOwned(String user) {
		if (this.usersOwned.containsKey(user))
			return this.usersOwned.get(user);
		else
			return 0;
	}

	public void printInfo() {
		System.out.println(fname);
		System.out.println(lname);
		System.out.println(username);
		System.out.println(password);
		System.out.println(email);
		for (Map.Entry<String, Integer> entry : stocksOwned.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		for (Map.Entry<String, Double> entry : usersOwned.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Map<String, Integer> getStocksOwned() {
		return stocksOwned;
	}
	
	public int getCountStocksOwned(){
		return this.stocksOwned.size();
	}

	public void setStocksOwned(Map<String, Integer> stocksOwned) {
		this.stocksOwned = stocksOwned;
	}

	public Map<String, Double> getUsersOwned() {
		return usersOwned;
	}

	public void setUsersOwned(Map<String, Double> usersOwned) {
		this.usersOwned = usersOwned;
	}

}
