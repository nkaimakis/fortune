package data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import server.Server;

public class ServerListener extends Thread {
	public String username;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Server server;

	public ServerListener(Socket s, Server server) {
		try {
			this.server = server;
			this.oos = new ObjectOutputStream(s.getOutputStream());
			this.ois = new ObjectInputStream(s.getInputStream());
			this.start();
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
	}

	public ObjectOutputStream getOOS() {
		return this.oos;
	}

	public void run() {
		try {
			while (true) {
				Object received = null;
				received = this.ois.readObject();
				if (received instanceof Transaction) {
					// buy or sell request from user
					// validity has already been checked on client side
					// update stocks owned and capital available in database
					// return nothing
					server.mysql.connect();
					Transaction t = (Transaction) received;
					if (t.isSell()) {
						this.server.mysql.sell(t);
						this.server.mysql.returnUser(t.getUser())
								.setBalance(this.server.mysql.returnBalance(t.getUser()));
					} else {
						this.server.mysql.buy(t);
						this.server.mysql.returnUser(t.getUser())
								.setBalance(this.server.mysql.returnBalance(t.getUser()));
					}
					server.mysql.stop();
				} else if (received instanceof SignupMessage) {
					// add new user information to database
					// return nothing
					SignupMessage sm = (SignupMessage) received;
					User u = sm.getUser();
					server.mysql.connect();
					if (server.mysql.isUsernameAvailable(u.getUsername())) {
						sm.setUsernamenotTaken(true);
						this.oos.writeObject(sm);
						this.oos.flush();
						this.server.mysql.createNewUser(u.getFname(), u.getLname(), u.getUsername(), u.getPassword(),
								u.getEmail());
					} else {
						sm.setUsernamenotTaken(false);
						this.oos.writeObject(sm);
						this.oos.flush();
					}

					server.mysql.stop();
				} else if (received instanceof UpdateUserMessage) {
					// contains all up to date user info, not just what has been
					// changed
					// update user information in database
					// return nothing
					UpdateUserMessage uum = (UpdateUserMessage) received;
					server.mysql.connect();
					server.mysql.updateUser(uum.username, uum.password, uum.email, uum.fname, uum.lname);
					server.mysql.stop();
				} else if (received instanceof GetUserMessage) {
					// contains username
					// return the user object
					GetUserMessage gum = (GetUserMessage) received;
					server.mysql.connect();
					User u = server.mysql.returnUser(gum.username);
					server.mysql.stop();

					this.oos.writeObject(u);
					this.oos.flush();
				} else if (received instanceof GetStockMessage) {
					// contains stock ticker
					// return stock object
					GetStockMessage gsm = (GetStockMessage) received;
					Vector<Stock> stocks = new Vector<Stock>();
					Vector<String> tickers = gsm.getTickers();
					for (String ticker : tickers) {
						stocks.add(StockFetcher.getStock(ticker));
					}
					this.oos.writeObject(stocks);
					this.oos.flush();
				} else if (received instanceof VerifyLoginMessage) {
					VerifyLoginMessage vlm = (VerifyLoginMessage) received;
					boolean verified = server.mysql.isValidLogin(vlm.getUsername(), vlm.getPassword());

					if (verified) {
						User u = server.mysql.returnUser(vlm.getUsername());
						vlm.setUser(u);
						this.oos.writeObject(vlm);
						this.oos.flush();
					} else {
						// sends back vlm without a user set, login incorrect
						this.oos.writeObject(vlm);
						this.oos.flush();
					}
				} else if (received instanceof GetStockUpdatesMessage) {
					GetStockUpdatesMessage gsum = (GetStockUpdatesMessage) received;
					Map<String, Integer> ownedstockmap = this.server.mysql.returnCurrentStock(gsum.getUsername());
					gsum.setCountOwned(ownedstockmap);
					List<Stock> stocklist = new ArrayList<Stock>();
					Set<String> ownedTickers = new HashSet<String>();
					for (Map.Entry<String, Integer> entry : ownedstockmap.entrySet()) {
						Stock s = StockFetcher.getStock(entry.getKey());
						s.setAmountOwned(entry.getValue());
						stocklist.add(s);
						ownedTickers.add(s.getSymbol());
						System.out.println("owned: " + s.getSymbol());
					}
					gsum.setOwnedStocks(stocklist);
					Map<String, Stock> fetchStocks = StockFetcher.getStocks();
					List<Stock> otherStocks = new ArrayList<Stock>();
					for (Map.Entry<String, Stock> stock : fetchStocks.entrySet()) {
						if (ownedTickers.contains(stock.getKey())) {
							// System.out.println("wtf is going on");
						} else {
							otherStocks.add(stock.getValue());
							System.out.println("other: " + stock.getValue().getSymbol());
						}
					}
					gsum.setOtherStocks(otherStocks);
					gsum.setFoundStocks(true);
					this.oos.writeObject(gsum);
					this.oos.flush();
				} else if (received instanceof ProfileUpdateMessage) {
					ProfileUpdateMessage pum = (ProfileUpdateMessage) received;
					server.mysql.updateUser(pum.getUser());
				}else if(received instanceof GetHistoryMessage){
					GetHistoryMessage ghm = (GetHistoryMessage)received;
					ghm.setHistory(this.server.mysql.returnCurrentTransactions());
					this.oos.writeObject(ghm);
					this.oos.flush();
				}
			}
		} catch (IOException e) {
			System.out.println("ioe in serverListener: " + this.username + " " + e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}