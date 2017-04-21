package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Client;
import data.ProfileUpdateMessage;

/**
 * Servlet implementation class ProfileUpdateServlet
 */
@WebServlet("/ProfileUpdateServlet")
public class ProfileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Client local = (Client) request.getSession().getAttribute("client");
		String fname = (String) request.getParameter("firstname");
		String lname = (String) request.getParameter("lastname");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		System.out.println("got everything");
		if (!fname.equals("")) {
			local.getUser().setFname(fname.trim());
		}
		if (!lname.equals("")) {
			local.getUser().setLname(lname.trim());
		}
		if (!email.equals("")) {
			local.getUser().setEmail(email.trim());
		}
		if (!password.equals("")) {
			local.getUser().setPassword(password.trim());
		}
		local.sendObject(new ProfileUpdateMessage(local.getUser()));
		String redirect = response.encodeRedirectURL(request.getContextPath() + "profile.jsp");
		response.sendRedirect(redirect);
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
