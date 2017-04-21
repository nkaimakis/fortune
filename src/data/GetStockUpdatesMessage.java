package data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class GetStockUpdatesMessage implements Serializable {

	private static final long serialVersionUID = -1720865755929018058L;
	private List<Stock> ownedStocks;
	private Map<String, Integer> countOwned;
	private List<Stock> otherStocks;
	private boolean foundStocks;
	private String username;

	public GetStockUpdatesMessage(String username) {
		foundStocks = false;
		this.username = username;
	}

	public List<Stock> getOwnedStocks() {
		return ownedStocks;
	}

	public List<Stock> getOtherStocks() {
		return otherStocks;
	}

	public void setOtherStocks(List<Stock> otherStocks) {
		this.otherStocks = otherStocks;
	}

	public void setOwnedStocks(List<Stock> ownedStocks) {
		this.ownedStocks = ownedStocks;
	}

	public Map<String, Integer> getCountOwned() {
		return countOwned;
	}

	public void setCountOwned(Map<String, Integer> countOwned) {
		this.countOwned = countOwned;
	}

	public boolean FoundStocks() {
		return foundStocks;
	}

	public void setFoundStocks(boolean foundStocks) {
		this.foundStocks = foundStocks;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
