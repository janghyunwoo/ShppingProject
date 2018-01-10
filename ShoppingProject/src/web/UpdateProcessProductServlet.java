package web;
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
import util.Status;
public class UpdateProcessProductServlet extends HttpServlet {
    @Override
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }
    public void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher view = null;
        Status status = new Status();
        request.setAttribute("status", status);
        ProductService ProductService = new ProductService();
        HttpSession HttpSession = request.getSession();
        User user = (User) HttpSession.getAttribute("user");
        int productid = Integer.parseInt(request.getParameter("productid"));
        ArrayList<Product> products = new ArrayList<Product>();
        products = ProductService.getAllProduct();
        request.setAttribute("products", products);
        request.setAttribute("user", user);
        request.setAttribute("productid", productid);
        try {
            String producttype = request.getParameter("producttype");
            String productname = request.getParameter("productname");
            String explanation = request.getParameter("explanation");
            int price = Integer.parseInt(request.getParameter("price"));
            int inventory = Integer.parseInt(request.getParameter("inventory"));
            if ((producttype == null) || (producttype.length() == 0)) {
                status.addException(new Exception(
                        "Please enter your producttype"));
            }
            if ((productname == null) || (productname.length() == 0)) {
                status.addException(new Exception(
                        "Please enter your productname"));
            }
            if ((explanation == null) || (explanation.length() == 0)) {
                status.addException(new Exception(
                        "Please enter your explanation"));
            }
            if ((price == 0)) {
                status.addException(new Exception(
                        "Please enter your price"));
            }
            if ((inventory == 0)) {
                status.addException(new Exception(
                        "Please enter your inventory"));
            }
            try {
                ProductService.updateProduct(productid, producttype, productname, explanation, price, inventory);
                products = ProductService.getAllProduct();
                request.setAttribute("products", products);
                if (!status.isSuccessful()) {
                    view = request.getRequestDispatcher("admin/update.jsp");
                    view.forward(request, response);
                    return;
                }
                view = request.getRequestDispatcher("admin/login.jsp");
                view.forward(request, response);
            } catch (Exception e) {
                status.addException(e);
                view = request.getRequestDispatcher("admin/update.jsp");
                view.forward(request, response);
            }
        } catch (IllegalArgumentException e) {
            status.addException(e);
            view = request.getRequestDispatcher("admin/update.jsp");
            view.forward(request, response);
        }
    }
}