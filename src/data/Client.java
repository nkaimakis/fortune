package data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class Client {
	public User user;
	public Socket s;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Vector<Stock> toDisplay;

	public Client() {
		this.toDisplay = new Vector<Stock>();
		// throws ioexception to main
		try {
			toDisplay = new Vector<Stock>();
			this.s = new Socket("localhost", 6969);
			this.oos = new ObjectOutputStream(this.s.getOutputStream());
			this.ois = new ObjectInputStream(this.s.getInputStream());
			user = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendObject(Object obj) {
		try {
			this.oos.writeObject(obj);
			this.oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		// here receive updates from ServerListener
		try {
			while (true) {
				Object received = ois.readObject();

				if (received instanceof User) {
					this.user = (User) received;

				} else if (received instanceof Vector<?>) {
					Vector<?> objects = (Vector<?>) received;
					if (objects.get(0) instanceof Stock) {
						toDisplay = (Vector<Stock>) objects;
					}

				}
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
	}

	public Vector<Stock> getToDisplay() {
		// sends message
		return toDisplay;
	}

	// returns true if verified, false if user dne
	public boolean sendVerifyLoginMessage(String username, String password) {
		boolean verified = false;
		VerifyLoginMessage vlm = new VerifyLoginMessage(username, password);
		sendObject(vlm);
		while (true) {
			try {
				Object received = ois.readObject();
				if (received instanceof VerifyLoginMessage) {
					vlm = (VerifyLoginMessage) received;
					if (vlm.isVerified()) {
						this.user = vlm.getUser();
						verified = true;
						break;
					} else {
						verified = false;
						break;
					}

				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return verified;
	}

	public boolean sendSignupMessage(SignupMessage sm) {
		sendObject(sm);
		while (true) {
			try {
				Object received = ois.readObject();
				if (received instanceof SignupMessage) {
					sm = (SignupMessage) received;
					if (sm.isUsernamenotTaken()) {
						this.user = sm.getUser();
						return true;
					} else {
						return false;
					}

				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public GetStockUpdatesMessage sendGetStockUpdatesMessage(GetStockUpdatesMessage gsum) {
		sendObject(gsum);
		while (true) {
			try {
				Object received = ois.readObject();
				if (received instanceof GetStockUpdatesMessage) {
					gsum = (GetStockUpdatesMessage) received;
					return gsum;
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public GetHistoryMessage sendGetHistoryMessage(GetHistoryMessage thm) {
		sendObject(thm);
		while (true) {
			try {
				Object received = ois.readObject();
				if (received instanceof GetHistoryMessage) {
					thm = (GetHistoryMessage) received;
					return thm;
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean isGuest() {
		if (this.user == null) {
			return true;
		} else {
			return false;
		}

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
