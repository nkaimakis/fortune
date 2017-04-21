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
import data.GetStockUpdatesMessage;
import data.Stock;
import data.StockFetcher;

/**
 * Servlet implementation class StockUpdateServlet
 */
@WebServlet("/StockUpdateServlet")
public class StockUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StockUpdateServlet() {
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
		JsonArray stockArray = new JsonArray();
		System.out.println("got to stockupdate servlet");
		if (local.isGuest()) {
			System.out.println("got guest");
			Map<String, Stock> stock_map = StockFetcher.getStocks();
			List<Stock> stock_list = new ArrayList<Stock>();
			for (Map.Entry<String, Stock> entry : stock_map.entrySet()) {
				stock_list.add(entry.getValue());
				System.out.println(entry.getKey());
			}
			Iterator<Stock> stock_it = stock_list.iterator();
			while (stock_it.hasNext()) {
				String jsonStock = new Gson().toJson(stock_it.next());
				stockArray.add(jsonStock);
			}
		} else {
			GetStockUpdatesMessage gsum = local
					.sendGetStockUpdatesMessage(new GetStockUpdatesMessage(local.getUser().getUsername()));
			JsonArray allStocks = new JsonArray();
			JsonArray ownedStocks = new JsonArray();
			List<Stock> owned = gsum.getOwnedStocks();
			List<Stock> other = gsum.getOtherStocks();
			request.getSession().setAttribute("owned", owned);
			request.getSession().setAttribute("other", other);

			Iterator<Stock> owned_it = owned.iterator();
			Iterator<Stock> all_it = other.iterator();
			while (owned_it.hasNext()) {
				String jsonStock = new Gson().toJson(owned_it.next());
				ownedStocks.add(jsonStock);
			}
			while (all_it.hasNext()) {
				String jsonStock = new Gson().toJson(all_it.next());
				allStocks.add(jsonStock);
			}
			JsonObject otherObj = new JsonObject();
			otherObj.add("all", allStocks);
			JsonObject ownedObj = new JsonObject();
			ownedObj.add("owned", ownedStocks);
			stockArray.add(ownedObj);
			stockArray.add(otherObj);
		}
		request.setAttribute("stockupdates", stockArray);
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		pw.print(stockArray);
		pw.flush();
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
