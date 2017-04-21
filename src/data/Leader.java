package data;

import java.io.Serializable;

public class Leader implements Serializable {
	
	public Leader(String username, double currentPortfolioValue){
		this.username = username; 
		this.currentPortfolioValue = currentPortfolioValue;
		this.percentMadeLifetime = 100  * (currentPortfolioValue-5000)/5000;
	}
	public String username; 
	public double percentMadeLifetime; 
	public double currentPortfolioValue; 
	
	public void printInfo(){
		System.out.println(username); 
		System.out.println(currentPortfolioValue);
		System.out.println(percentMadeLifetime);
	}
	
	
}
