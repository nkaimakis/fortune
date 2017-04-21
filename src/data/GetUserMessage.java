package data;

import java.io.Serializable;

public class GetUserMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	public String username;
	
	public GetUserMessage(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return this.username;
	}
}
