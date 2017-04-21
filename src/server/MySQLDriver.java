package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import data.Leader;
import data.LeaderTransaction;
import data.Stock;
import data.StockFetcher;
import data.Transaction;
import data.User;
import data.UserStock;

public class MySQLDriver {
	private Connection conn;

	public MySQLDriver() {
		try {
			new com.mysql.jdbc.Driver();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			conn = DriverManager
					.getConnection("Jdbc:mysql://localhost/finalproject?user=root&password=root&useSSL=false");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * These are the methods I plan on having and I will include what it returns
	 * 
	 */

	/* isValidLogin return true if the login is valid else false */
	public boolean isValidLogin(String username, String password) {
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT username, password FROM finalproject.users WHERE username=? AND password=? ");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				System.out.println(result.getString(1) + " has password " + result.getString(2));
				this.stop();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("invalid login");
		this.stop();
		return false;
	}

	/*
	 * creates new UserAccount object, insert row into database, return
	 * UserAccount
	 */
	public void createNewUser(String fname, String lname, String username, String password, String email) {
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO `finalproject`.`users` (`username`, `fname`, `lname`, `email`, `password`, `balance`) VALUES (?, ?, ?, ?, ?, '1000000')");
			ps.setString(1, username);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, email);
			ps.setString(5, password);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
	}

	/*
	 * return true if username is available (it doesn't exist in the database
	 * else return false
	 */

	public boolean isUsernameAvailable(String username) {
		this.connect();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT username FROM finalproject.users WHERE username=?");
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				System.out.println(result.getString(1) + " exists");
				this.stop();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(username + " does not exists");
		this.stop();
		return true;
	}

	/*
	 * returns a vector of transaction objects that the specified user has made
	 */

	public Vector<Transaction> returnCurrentTransactions() throws ParseException {
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT stock_symbol, stock_price, stock_amount, isSell, timestamp, user_id FROM finalproject.transactions");
			ResultSet result = ps.executeQuery();
			Vector<Transaction> list = new Vector<Transaction>();
			while (result.next()) {
				String ticker = result.getString(1);
				int amount = result.getInt(2);
				double price = result.getDouble(3);
				boolean isSell = result.getBoolean(4);
				String time = result.getString(5);
				String username = result.getString(6);
				Transaction trans = new Transaction(username, ticker, amount, price, isSell, time);
				trans.printTransaction();
				list.add(trans);
			}
			this.stop();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
		return null;
	}

	/*
	 * Passes a vector of ordered user values and vector of corresponding
	 * usernames -- they will be in the same order.
	 */

	/*
	 * public Vector<Map.Entry<String, Double>> returnTopInvestors() {
	 * this.connect(); Vector<String> usernames = returnAllUsers();
	 * SortedSet<Double> values = new TreeSet<Double>(); Map<Double,
	 * Set<String>> legend = new HashMap<Double, Set<String>>();
	 * Vector<Map.Entry<String, Double>> pairs = new Vector<Map.Entry<String,
	 * Double>>(); for (String name : usernames) { double value =
	 * returnValue(name); values.add(value); if (legend.containsKey(value)) {
	 * legend.get(value).add(name); } else { Set<String> names = new
	 * HashSet<String>(); names.add(name); legend.put(value, names); } }
	 * 
	 * values = ((TreeSet<Double>) values).descendingSet(); for (Double score :
	 * values) { for (String name : legend.get(score)) { Map.Entry<String,
	 * Double> pair = new AbstractMap.SimpleEntry<String, Double>(name, score);
	 * pairs.add(pair); } }
	 * 
	 * this.stop(); return pairs; }
	 */

	public List<Leader> returnTopInvestors() {
		this.connect();
		Vector<String> usernames = returnAllUsers();
		SortedSet<Double> values = new TreeSet<Double>();
		Map<Double, Set<String>> legend = new HashMap<Double, Set<String>>();
		List<Leader> pairs = new ArrayList<Leader>();
		for (String name : usernames) {
			double value = returnValue(name);
			values.add(value);
			if (legend.containsKey(value)) {
				legend.get(value).add(name);
			} else {
				Set<String> names = new HashSet<String>();
				names.add(name);
				legend.put(value, names);
			}
		}

		values = ((TreeSet<Double>) values).descendingSet();
		int counter = 0;
		for (Double score : values) {
			for (String name : legend.get(score)) {
				if (counter < 10) {
					Leader leader = new Leader(name, score);
					pairs.add(leader);
					counter++;
				}

			}
		}

		this.stop();
		return pairs;
	}

	public double returnBalance(String username) {
		this.connect();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT balance FROM finalproject.users WHERE username = ?;");
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Double balance = result.getDouble(1);
				this.stop();
				return balance;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
		return 0;
	}

	/* returns a vector of investors that invest in a particular stock */

	public Vector<String> returnInvestors(String stockSymbol) {
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT user_id FROM finalproject.transactions WHERE stock_symbol = ? GROUP BY user_id;");
			ps.setString(1, stockSymbol.toLowerCase());
			ResultSet result = ps.executeQuery();
			Vector<String> list = new Vector<String>();
			while (result.next()) {
				list.add(result.getString(1));
			}
			this.stop();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
		return null;
	}

	/* returns a vector of transaction of the followers of a particular user */

	public Vector<Transaction> returnFeed(String username) {
		try {
			this.connect();
			PreparedStatement ps = conn
					.prepareStatement("SELECT stock_symbol, stock_price, stock_amount, isSell, timestamp, user_id"
							+ " FROM finalproject.transactions WHERE user_id in (SELECT following_id FROM "
							+ "finalproject.follows WHERE follower_id = ?) " + "Order by timestamp desc;");
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			Vector<Transaction> list = new Vector<Transaction>();
			while (result.next()) {
				String ticker = result.getString(1);
				int amount = result.getInt(2);
				double price = result.getDouble(3);
				boolean isSell = result.getBoolean(4);
				String time = result.getString(5);
				Transaction trans = new Transaction(result.getString(6), ticker, amount, price, isSell, time);
				trans.printTransaction();
				list.add(trans);
			}
			this.stop();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		this.stop();
		return null;
	}

	public void follow(String follower, String following) {
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO `finalproject`.`follows` (`follower_id`, `following_id`) values (?, ?); ");
			ps.setString(1, follower);
			ps.setString(2, following);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
	}

	public void updateBalance(String username, double newBalance) {
		try {
			this.connect();
			PreparedStatement ps = conn
					.prepareStatement("UPDATE `finalproject`.`users` SET `balance`=? WHERE `username`=?;");
			ps.setDouble(1, newBalance);
			ps.setString(2, username);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
	}

	public void buy(Transaction trans) {
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `finalproject`.`transactions` "
					+ "(`stock_symbol`, `stock_price`, `stock_amount`, `isSell`, `timestamp`, `user_id`, `isSuccess`) VALUES "
					+ "(?, ?, ?, ?, ?, ?, 1);");
			ps.setString(1, trans.getTicker());
			ps.setDouble(2, trans.getPrice());
			ps.setInt(3, trans.getAmount());
			ps.setInt(4, 0);
			ps.setString(5, trans.getTime());
			ps.setString(6, trans.getUser());

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
	}

	public void sell(Transaction trans) {
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `finalproject`.`transactions` "
					+ "(`stock_symbol`, `stock_price`, `stock_amount`, `isSell`, `timestamp`, `user_id`, `isSuccess`) VALUES "
					+ "(?, ?, ?, ?, ?, ?, 1);");
			ps.setString(1, trans.getTicker());
			ps.setDouble(2, trans.getPrice());
			ps.setInt(3, trans.getAmount());
			ps.setInt(4, 1);
			ps.setString(5, trans.getTime());
			ps.setString(6, trans.getUser());

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
	}

	public User returnUser(String username) {
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM finalproject.users WHERE username=?");
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				String fname = result.getString(2);
				String lname = result.getString(3);
				String email = result.getString(4);
				String password = result.getString(5);
				double balance = result.getDouble(6);

				User user = new User(fname, lname, username, password, email, balance, returnCurrentStock(username),
						returnCurrentUserInvestments(username));
				this.stop();
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
		return null;
	}

	private Map<String, Double> returnCurrentUserInvestments(String username) {
		try {
			this.connect();
			PreparedStatement ps = conn
					.prepareStatement("Select investee_username,sum(amount.value) as amount_invested "
							+ "From (SELECT investor_username, investee_username, IF(isSell = 1, sum(amount_invested)*-1, sum(amount_invested)) as value "
							+ "FROM finalproject.userinvestments Group by investor_username, investee_username, isSell)"
							+ " as amount  WHERE  amount.investor_username = ? Group By investee_username  HAVING sum(amount.value) <> 0; ");
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			Map<String, Double> list = new HashMap<String, Double>();
			while (result.next()) {
				String name = result.getString(1);
				double amount = result.getDouble(2);
				list.put(name, amount);
			}
			this.stop();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
		return null;
	}

	public void updateUser(String username, String password, String email, String fname, String lname) {
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE `finalproject`.`users` SET `fname`=?, `lname`=?, `email`=?, `password`=? WHERE `username`= ?;");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, email);
			ps.setString(4, password);
			ps.setString(5, username);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
	}

	public void updateUser(User user) {
		this.connect();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE `finalproject`.`users` SET `fname`=?, `lname`=?, `email`=?, `password`=?, `balance`=? WHERE `username`= ?;");
			ps.setString(1, user.fname);
			ps.setString(2, user.lname);
			ps.setString(3, user.email);
			ps.setString(4, user.password);
			ps.setDouble(5, user.balance);
			ps.setString(6, user.username);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
	}

	/*
	 * return vector of stocks that user currently owns
	 */
	public Map<String, Integer> returnCurrentStock(String username) {
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement("Select stock_symbol,sum(amount.value) "
					+ "From (SELECT user_id, stock_symbol, IF(isSell = 1, sum(stock_amount)*-1, sum(stock_amount)) "
					+ "as value FROM finalproject.transactions Group by user_id,stock_symbol, isSell) as amount WHERE "
					+ "user_id = ? Group By user_id, stock_symbol  HAVING sum(amount.value) <> 0; ");
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			Map<String, Integer> list = new HashMap<String, Integer>();
			while (result.next()) {
				String ticker = result.getString(1).toUpperCase();
				Integer amount = result.getInt(2);
				list.put(ticker, amount);
			}
			this.stop();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
		return null;
	}

	public void buy(LeaderTransaction lt) { /// FIX THIS
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `finalproject`.`userinvestments` "
					+ "(`investor_username`, `investee_username`, `amount_invested`, `isSell`, `timestamp`, "
					+ "`investee_value`) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, lt.getInvestor_username());
			ps.setString(2, lt.getInvestee_username());
			ps.setInt(3, lt.getAmount_invested());
			ps.setBoolean(4, lt.isSell());
			ps.setTimestamp(5, lt.getTime());
			ps.setDouble(6, lt.getInvestee_price());
			ps.execute();
			updateBalance(lt.getInvestor_username(),
					returnBalance(lt.getInvestor_username()) - lt.getAmount_invested());
			updateBalance(lt.getInvestee_username(),
					returnBalance(lt.getInvestee_username()) + lt.getAmount_invested());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
	}

	public void sell(LeaderTransaction lt) { /// FIX THIS
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `finalproject`.`userinvestments` "
					+ "(`investor_username`, `investee_username`, `amount_invested`, `isSell`, `timestamp`, "
					+ "`investee_value`) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, lt.getInvestor_username());
			ps.setString(2, lt.getInvestee_username());
			ps.setInt(3, lt.getAmount_invested());
			ps.setBoolean(4, lt.isSell());
			ps.setTimestamp(5, lt.getTime());
			ps.setDouble(6, lt.getInvestee_price());

			ps.execute();

			ps = conn.prepareStatement("SELECT investee_value, amount_invested, timestamp FROM "
					+ "finalproject.userinvestments WHERE investor_username =? "
					+ "AND investee_username = ? AND isSell = 0 ORDER BY timestamp desc;");
			ps.setString(1, lt.getInvestor_username());
			ps.setString(2, lt.getInvestee_username());
			ResultSet result = ps.executeQuery();
			double oldValue = 0;
			while (result.next()) {
				oldValue = result.getDouble(1) + result.getDouble(2);
			}
			double newValue = lt.getInvestee_price();

			double profit = (1 + (newValue - oldValue) / oldValue) * lt.getAmount_invested();
			updateBalance(lt.getInvestor_username(), returnBalance(lt.getInvestee_username()) + profit);
			updateBalance(lt.getInvestee_username(), returnBalance(lt.getInvestee_username()) - profit);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
	}

	public void process(LeaderTransaction lt) {
		if (lt.isSell()) {
			sell(lt);
		} else {
			buy(lt);
		}
	}

	/* returns the value of a particular user */
	public double returnValue(String username) {
		this.connect();
		Map<String, Integer> list = returnCurrentStock(username);
		StockFetcher sf = new StockFetcher();
		double totalValue = 0;
		for (Map.Entry<String, Integer> entry : list.entrySet()) {
			double price = sf.getStock(entry.getKey()).getPrice();
			totalValue = totalValue + price * entry.getValue();
			/* System.out.println(entry.getKey() + "/" + entry.getValue()); */
		}
		this.stop();
		return returnBalance(username) + totalValue;
	}

	public Vector<String> returnAllUsers() {
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement("SELECT username FROM finalproject.users;");
			ResultSet result = ps.executeQuery();
			Vector<String> list = new Vector<String>();
			while (result.next()) {

				list.add(result.getString(1));
			}
			this.stop();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
		return null;
	}

	public List<Object> returnObjects() {
		List<Object> list = new ArrayList();
		List<String> existingStock = new ArrayList();
		StockFetcher sf = new StockFetcher();
		try {
			this.connect();
			PreparedStatement ps = conn.prepareStatement("Select stock_symbol " + "From (SELECT user_id, stock_symbol, "
					+ "IF(isSell = 1, sum(stock_amount)*-1, sum(stock_amount)) as value "
					+ "FROM finalproject.transactions Group by user_id,stock_symbol, isSell)"
					+ " as amount Group By stock_symbol  HAVING sum(amount.value) <> 0 "
					+ "order by sum(amount.value) desc;  ");
			ResultSet result = ps.executeQuery();
			while (result.next()) {

				list.add(sf.getStock(result.getString(1)));
				existingStock.add(result.getString(1));
			}

			ps = conn.prepareStatement("Select investee_username,sum(amount.value) as "
					+ "amount_invested From (SELECT investor_username, investee_username, "
					+ "IF(isSell = 1, sum(amount_invested)*-1, sum(amount_invested)) as value"
					+ " FROM finalproject.userinvestments Group by investor_username, "
					+ "investee_username, isSell) as amount  Group By investee_username  "
					+ "HAVING sum(amount.value) <> 0 order by sum(amount.value) desc; ");
			result = ps.executeQuery();
			while (result.next()) {
				UserStock us = new UserStock(result.getString(1), result.getDouble(2),
						returnDailyGrowth(result.getString(1)));
				list.add(us);
			}
			Map<String, Stock> allStocks = sf.getStocks();
			for (Map.Entry<String, Stock> entry : allStocks.entrySet()) {
				if (!existingStock.contains(entry.getKey())) {
					list.add(entry.getValue());
				}
			}
			this.stop();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.stop();
		}
		return list;

	}

	public double returnDailyGrowth(String username) {
		StockFetcher sf = new StockFetcher();
		Double yesterdayValue = returnBalance(username);
		Double todayValue = returnValue(username);
		Map<String, Integer> stocksOwned = returnCurrentStock(username);
		for (Map.Entry<String, Integer> entry : stocksOwned.entrySet()) {

			double price = sf.getStock(entry.getKey()).getPreviousClose();
			yesterdayValue = yesterdayValue + price * entry.getValue();
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		Double growth = (todayValue - yesterdayValue) / yesterdayValue;
		return growth;
	}

	public static void main(String[] args) throws ParseException {
		MySQLDriver msql = new MySQLDriver();

		/*
		 * LeaderTransaction lt = new LeaderTransaction("jessicaFu", "cjin",
		 * 100000, msql.returnValue("cjin"), new
		 * Timestamp(System.currentTimeMillis()), true); msql.process(lt);
		 */
		for (Object temp : msql.returnObjects()) {
			if (temp instanceof Stock) {
				((Stock) temp).printInfo();
			} else if (temp instanceof UserStock) {
				((UserStock) temp).printInfo();
			}
		}

		for (Leader leader : msql.returnTopInvestors()) {
			leader.printInfo();
		}

		/*
		 * user=msql.returnUser("jessicaFu");
		 * msql.returnUser("cjin").printInfo(); msql.updateBalance("cjin",
		 * 2000000);
		 * 
		 * lt = new LeaderTransaction("jessicaFu", "cjin", 1000,
		 * msql.returnValue("cjin"), new Timestamp(System.currentTimeMillis()),
		 * true); msql.process(lt); user=msql.returnUser("jessicaFu");
		 * msql.returnUser("cjin").printInfo(); lt = new
		 * LeaderTransaction("jessicaFu", "whitneysit", 1000,
		 * msql.returnValue("whitneysit"), new
		 * Timestamp(System.currentTimeMillis()), false); msql.process(lt);
		 * user=msql.returnUser("jessicaFu");
		 * msql.returnUser("cjin").printInfo(); msql.updateBalance("cjin",
		 * 1000000); lt = new LeaderTransaction("jessicaFu", "whitneysit", 1000,
		 * msql.returnValue("whitneysit"), new
		 * Timestamp(System.currentTimeMillis()), true); msql.process(lt);
		 * user=msql.returnUser("jessicaFu");
		 * msql.returnUser("cjin").printInfo(); user.printInfo();
		 */

		/*
		 * msql.isUsernameAvailable("jessicaFu"); msql.createNewUser("jessica",
		 * "fu", "jessicaFu", "jessicaFu", "jessica.fu@gpmail.org"); if
		 * (msql.isValidLogin("jessicaFu", "jessicafu")) {
		 * System.out.println("Valid Login"); } else {
		 * System.out.println("Invalid login"); }
		 * msql.updateBalance("jessicaFu", 2000.50);
		 * 
		 * Transaction trans = new Transaction("jessicaFu", "aapl", 1000,
		 * 1000.0, false); msql.buy(trans);
		 */
		/*
		 * try { Vector<Transaction> list =
		 * msql.returnCurrentTransactions("whitneysit"); for (Transaction temp :
		 * list) { temp.printTransaction(); }
		 * 
		 * } catch (ParseException e) { e.printStackTrace(); }
		 * 
		 * msql.returnUser("whitneysit").printInfo(); Vector<String> list =
		 * msql.returnInvestors("AMZN"); for (String temp : list) {
		 * System.out.println(temp); } msql.updateUser("jessicaFu", "jessFU",
		 * "j.iskandar@gmail.com", "jess", "iskandar"); LeaderTransaction lt =
		 * new LeaderTransaction("jessicaFu", "fwada", 1000, 10000, new
		 * Timestamp(System.currentTimeMillis()), false); msql.process(lt); for
		 * (String temp : msql.returnAllUsers()) { System.out.println(temp); }
		 * System.out.println(msql.returnValue("jessicaFu"));
		 * 
		 * for (Map.Entry<String, Double> temp : msql.returnTopInvestors()){
		 * System.out.println(temp.getKey() + "/" + temp.getValue()); }
		 */
		/*
		 * StockFetcher sf = new StockFetcher(); Transaction trans = new
		 * Transaction("whitneysit", "FB", 1000, sf.getStock("FB").getPrice(),
		 * false); msql.buy(trans); Transaction sell = new
		 * Transaction("whitneysit", "FB", 500, sf.getStock("FB").getPrice()+10,
		 * false); msql.sell(sell); Transaction trans1 = new
		 * Transaction("jessicaOh", "FB", 1000, sf.getStock("FB").getPrice(),
		 * false); msql.buy(trans1); Transaction sell1 = new
		 * Transaction("jessicaOh", "FB", 500, sf.getStock("FB").getPrice()+10,
		 * false); msql.sell(sell1); Transaction trans11 = new
		 * Transaction("cjin", "EEM", 1000, sf.getStock("EEM").getPrice(),
		 * false); msql.buy(trans11); Transaction sell11 = new
		 * Transaction("cjin", "EEM", 500, sf.getStock("EEM").getPrice()+10,
		 * false); msql.sell(sell11); Transaction trans111 = new
		 * Transaction("jessicaOh", "PYPL", 1000,
		 * sf.getStock("PYPL").getPrice(), false); msql.buy(trans111);
		 * Transaction sell121 = new Transaction("jessicaOh", "PYPL", 500,
		 * sf.getStock("PYPL").getPrice()+10, false); msql.sell(sell121);
		 * Transaction sell123 = new Transaction("cjin", "SNAP", 1000,
		 * sf.getStock("SNAP").getPrice(), false); msql.buy(sell123);
		 * Transaction tran123 = new Transaction("cjin", "SNAP", 500,
		 * sf.getStock("SNAP").getPrice()+10, false); msql.sell(tran123);
		 */
		/*
		 * msql.follow("whitneysit", "jessicaFu"); msql.follow("whitney",
		 * "jessicaOh" );
		 * 
		 * for (Transaction temp: msql.returnFeed("whitneysit")){
		 * temp.printTransaction(); }
		 */
	}

}
