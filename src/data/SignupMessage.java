package data;

import java.io.Serializable;

public class SignupMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7904098250761976384L;
	private User user;
	public boolean usernamenotTaken;

	public SignupMessage(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isUsernamenotTaken() {
		return usernamenotTaken;
	}

	public void setUsernamenotTaken(boolean usernamenotTaken) {
		this.usernamenotTaken = usernamenotTaken;
	}

}
