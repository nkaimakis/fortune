package data;

import java.io.Serializable;

public class LeaderTransactionMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LeaderTransaction leadertrans;

	public LeaderTransactionMessage(LeaderTransaction transaction) {
		super();
		this.leadertrans = transaction;
	}

	public LeaderTransaction getLeaderTransaction() {
		return leadertrans;
	}

	public void setTransaction(LeaderTransaction transaction) {
		this.leadertrans = transaction;
	}

}
