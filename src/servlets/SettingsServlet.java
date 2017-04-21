package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Client;
import data.UpdateUserMessage;

/**
 * Servlet implementation class SettingsServlet
 */
@WebServlet("/SettingsServlet")
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SettingsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String password = (String) request.getParameter("password");
		String email = (String) request.getParameter("email");
		String fname = (String) request.getParameter("fname");
		String lname = (String) request.getParameter("lname");
		Client local = (Client) request.getSession().getAttribute("client");
		if (!password.equals(null)) {
			local.getUser().setPassword(password);
		}
		if (!email.equals(null)) {
			local.getUser().setEmail(email);
		}
		if (!fname.equals(null)) {
			local.getUser().setFname(fname);
		}
		if (!lname.equals(null)) {
			local.getUser().setLname(lname);
		}
		local.sendObject(new UpdateUserMessage(local.getUser().getUsername(), local.getUser().getPassword(),
				local.getUser().getEmail(), local.getUser().getFname(), local.getUser().getLname()));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
