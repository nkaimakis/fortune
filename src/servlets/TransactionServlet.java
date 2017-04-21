package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Client;
import data.Stock;
import data.StockFetcher;
import data.Transaction;

@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TransactionServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// this works assuming the transactions are created on the front end
		// may have to create transactions here instead
		try {
			String ticker = (String) request.getParameter("ticker");
			Boolean isSell = false;
			if (ticker.contains("BUY")) {
				ticker = ticker.substring(3);
				System.out.println("got ticker: " + ticker);
			} else {
				ticker = ticker.substring(4);
				System.out.println("got ticker: " + ticker);
				isSell = true;
			}
			int amount = Integer.parseInt((String) request.getParameter("amount"));
			Long millis = System.currentTimeMillis();
			Timestamp ts = new Timestamp(millis);
			double price = StockFetcher.getStock(ticker).getPrice();
			Client local = (Client) request.getSession().getAttribute("client");
			String username = local.getUser().getUsername();

			Transaction trans = new Transaction(username, ticker, amount, price, isSell, ts);
			if (trans != null) {
				Stock s = StockFetcher.getStock(trans.getTicker());

				// update user and database
				// sending transaction message updates the database
				if (trans.isSell()) {
					local.getUser().sellStock(s, amount);
					local.sendObject(trans);
				} else {
					local.getUser().buyStock(s, amount);
					local.sendObject(trans);
				}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// } else if (lt != null) {
		// String toInvestIn = lt.getInvestee_username();
		// int amount = lt.getAmount_invested();
		// Client client = (Client)
		// request.getSession().getAttribute("client");
		// User u = client.user;
		//
		// if (lt.isSell()) {
		// u.sellUser(toInvestIn, amount);
		// LeaderTransactionMessage ltm = new LeaderTransactionMessage(lt);
		// client.sendObject(ltm);
		// } else {
		// u.buyUser(toInvestIn, amount);
		// LeaderTransactionMessage ltm = new LeaderTransactionMessage(lt);
		// client.sendObject(ltm);
		// }
		// }

		// resets attributes for next transaction
		// redirect to main page which will update displays
		response.sendRedirect("mainPage.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
