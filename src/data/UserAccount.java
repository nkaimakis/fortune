//Whitney Sit
//calling these methods will change the SQL database
//following the design doc, if we have made modifications to that we should do that by crossing off from the doc

/*
Not Completed. 

*/

package data;

import java.io.Serializable;

public class UserAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String email;
	private int userID;
	private String fname;
	private String lname;
	private double balance;

	/*
	 * private boolean watcher;
	 * 
	 * void togglerWatcher() { watcher = true; // is this right?? }
	 */
	public UserAccount(String fname, String lname, String username, String password, String email) { // constructor?
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.balance = 1000000;
	}

	public UserAccount(String fname, String lname, String username, String password, String email, double balance) { // constructor?
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.balance = balance;
	}

	public void printInfo() {
		System.out.println(fname);
		System.out.println(lname);
		System.out.println(username);
		System.out.println(password);
		System.out.println(email);
	}
}
