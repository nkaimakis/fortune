package data;

import java.io.Serializable;

public class ProfileUpdateMessage implements Serializable {
	private User user;

	public ProfileUpdateMessage(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}
}
