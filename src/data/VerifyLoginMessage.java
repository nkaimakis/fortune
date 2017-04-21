package data;

import java.io.Serializable;

public class VerifyLoginMessage implements Serializable{
	public String username;
	public String password;
	public User user;
	
	public VerifyLoginMessage(String username, String password){
		this.username = username;
		this.password = password;
		this.user = null;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public boolean isVerified(){
		if(this.user == null)
			return false;
		else
			return true;
	}
	
	//user's credentials are approved, sets user variable
	public void setUser(User u){
		this.user = u;
	}
	
	public User getUser(){
		return this.user;
	}
	
}
