package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import data.Client;
import data.GetHistoryMessage;
import data.GetStockUpdatesMessage;
import data.Stock;
import data.StockFetcher;
import data.Transaction;

/**
 * Servlet implementation class TransactionHistoryServlet
 */
@WebServlet("/TransactionHistoryServlet")
public class TransactionHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client local = (Client) request.getSession().getAttribute("client");
		System.out.println("got to history servlet");
		GetHistoryMessage ghm = local
				.sendGetHistoryMessage(new GetHistoryMessage());
		List<Transaction> history = ghm.getHistory();
		request.getSession().setAttribute("history", history);
		JsonArray jsonHistory = new JsonArray();

		Iterator<Transaction> it = history.iterator();
		while (it.hasNext()) {
			String jsonStock = new Gson().toJson(it.next());
			System.out.println(jsonStock);
			jsonHistory.add(jsonStock);
		}
		
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		pw.print(jsonHistory);
		pw.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
