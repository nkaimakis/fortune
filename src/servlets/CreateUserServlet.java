package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Client;
import data.GetHistoryMessage;
import data.GetStockUpdatesMessage;
import data.SignupMessage;
import data.Stock;
import data.Transaction;
import data.User;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateUserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (request.getParameter("firstname").equals("")) {
			PrintWriter pw = response.getWriter();
			pw.print("Please enter your first name");
			pw.flush();
		} else if (request.getParameter("lastname").equals("")) {
			PrintWriter pw = response.getWriter();
			pw.print("Please enter your last name");
			pw.flush();
		} else if (request.getParameter("username").equals("")) {
			PrintWriter pw = response.getWriter();
			pw.print("Please enter a username");
			pw.flush();
		} else if (request.getParameter("password").equals("")) {
			PrintWriter pw = response.getWriter();
			pw.print("Please enter a password");
			pw.flush();
		} else if (request.getParameter("email").equals("")) {
			PrintWriter pw = response.getWriter();
			pw.print("Please enter your email");
			pw.flush();
		} else { // if successful, update database
			User user = new User(request.getParameter("firstname"), request.getParameter("lastname"),
					request.getParameter("username"), request.getParameter("password"), request.getParameter("email"),
					5000);
			SignupMessage sm = new SignupMessage(user);
			Client local = (Client) request.getSession().getAttribute("client");
			System.out.println("sending create user message");
			boolean valid = local.sendSignupMessage(sm);
			if (valid) {
				System.out.println("validated on sign up servlet");
				GetStockUpdatesMessage gsum = local
						.sendGetStockUpdatesMessage(new GetStockUpdatesMessage(local.getUser().getUsername()));
				List<Stock> owned = gsum.getOwnedStocks();
				List<Stock> other = gsum.getOtherStocks();
				request.getSession().setAttribute("owned", owned);
				request.getSession().setAttribute("other", other);
				GetHistoryMessage ghm = local
						.sendGetHistoryMessage(new GetHistoryMessage());
				List<Transaction> history = ghm.getHistory();
				request.getSession().setAttribute("history", history);

			} else {
				PrintWriter pw = response.getWriter();
				pw.print("That username is taken");
				pw.flush();
			}
		}

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
