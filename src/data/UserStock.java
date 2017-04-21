package data;

import java.io.Serializable;

public class UserStock implements Serializable {
	public String username;
	public double amountOwned;
	//daily increase/decrease
	public double growth;
	
	public UserStock(String username, double amountOwned, double growth){
		this.username = username;
		this.amountOwned = amountOwned;
		this.growth = growth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getAmountOwned() {
		return amountOwned;
	}

	public void setAmountOwned(double amountOwned) {
		this.amountOwned = amountOwned;
	}

	public double getGrowth() {
		return growth;
	}

	public void setGrowth(double growth) {
		this.growth = growth;
	}
	
	public void printInfo(){
		System.out.println(username);
		System.out.println(amountOwned);
		System.out.println(growth);
	}
	
}
