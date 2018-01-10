package web;

import domain.Basket;
import domain.BasketService;
import domain.PaymentService;
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

public class ProcessPaymentServlet extends HttpServlet {
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
		int userid = user.getUserid();
		String address = user.getAddress();
		String contact = user.getContact();
		String creditcardnumber = request.getParameter("creditcardnumber");
		String creditcardpassword = request.getParameter("creditcardpassword");
		ArrayList<Product> products = null;
		ProductService ProductService = new ProductService();
		products = ProductService.getAllProduct();
		request.setAttribute("products", products);
		request.setAttribute("user", HttpSession.getAttribute("user"));
		PaymentService PaymentService = new PaymentService();
		for (int i = 0; i < baskets.size(); i++) {
			PaymentService.paymentAdd(userid, baskets.get(i).getProductid(), baskets.get(i).getNumbers(), address,
					contact, creditcardnumber, creditcardpassword);
		}
		BasketService BasketService = new BasketService();
		BasketService.cleanBasket(userid);

		view = request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
	}
}