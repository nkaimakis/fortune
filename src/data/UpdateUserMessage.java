package data;

import java.io.Serializable;

public class UpdateUserMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	public String username;
	public String password;
	public String email;
	public String fname;
	public String lname;

	public UpdateUserMessage(String un, String pw, String email, String fname, String lname) {
		this.username = un;
		this.password = pw;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
	}
}
