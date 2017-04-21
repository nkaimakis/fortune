package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Client;
import data.GetHistoryMessage;
import data.GetStockUpdatesMessage;
import data.Stock;
import data.Transaction;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");

		if (username.equals("")) {
			PrintWriter pw = response.getWriter();
			pw.print("Incorrect username");
			pw.flush();
		} else if (password.equals("")) {
			PrintWriter pw = response.getWriter();
			pw.print("Incorrect password");
			pw.flush();
		} else {
			// check if username and password are valid in database
			Client local = (Client) request.getSession().getAttribute("client");
			boolean verified = local.sendVerifyLoginMessage(username, password);
			if (verified) {
				GetHistoryMessage ghm = local
						.sendGetHistoryMessage(new GetHistoryMessage());
				List<Transaction> history = ghm.getHistory();
				request.getSession().setAttribute("history", history);
				GetStockUpdatesMessage gsum = local
						.sendGetStockUpdatesMessage(new GetStockUpdatesMessage(local.getUser().getUsername()));
				List<Stock> owned = gsum.getOwnedStocks();
				List<Stock> other = gsum.getOtherStocks();
				request.getSession().setAttribute("owned", owned);
				request.getSession().setAttribute("other", other);
			} else {
				PrintWriter pw = response.getWriter();
				pw.print("Incorrect username/password combination");

				pw.flush();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
