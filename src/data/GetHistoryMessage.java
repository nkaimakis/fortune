package data;

import java.io.Serializable;
import java.util.List;

public class GetHistoryMessage implements Serializable{
	public List<Transaction> history;
	
	public GetHistoryMessage(){
		
	}

	public List<Transaction> getHistory() {
		return history;
	}

	public void setHistory(List<Transaction> history) {
		this.history = history;
	}
	
	
}
