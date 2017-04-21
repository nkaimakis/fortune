package data;

import java.io.Serializable;

public class TransactionMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7904098250761976384L;
	private Transaction transaction;

	public TransactionMessage(Transaction transaction) {
		super();
		this.transaction = transaction;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}
