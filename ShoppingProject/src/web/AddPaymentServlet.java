package web;

import domain.Basket;
import domain.Product;
import domain.ProductService;
import domain.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddPaymentServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher view = null;
		HttpSession HttpSession = request.getSession();
		ArrayList<Basket> baskets = (ArrayList<Basket>) HttpSession.getAttribute("baskets");
		User user = (User) HttpSession.getAttribute("user");
		ProductService ProductService;
		ProductService = new ProductService();
		ArrayList<Product> products = null;
		products = ProductService.getAllProduct();
		int totalprice = 0;
		ArrayList<Product> myproducts = new ArrayList<Product>();
		for (int i = 0; i < baskets.size(); i++) {
			for (int j = 0; j < products.size(); j++) {
				if (baskets.get(i).getProductid() == products.get(j).getProductid()) {
					totalprice += products.get(j).getPrice() * baskets.get(i).getNumbers();
					myproducts.add(products.get(j));
				}
			}
		}
		request.setAttribute("baskets", baskets);
		request.setAttribute("user", user);
		request.setAttribute("myproducts", myproducts);
		request.setAttribute("totalprice", totalprice);
		view = request.getRequestDispatcher("addpayment.jsp");
		view.forward(request, response);
	}
}