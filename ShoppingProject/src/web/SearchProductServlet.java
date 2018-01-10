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
public final class SearchProductServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }
    public void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher view = null;
        ProductService ProductService = null;
        String ProductName = request.getParameter("productname");
        HttpSession HttpSession = request.getSession();//여기에 login.jsp에서 세션 설정한 
        												//session.setAttribute("user", user); 이 들어가 있음.
        ArrayList<Product> products = null;
        ProductService = new ProductService();
        products = ProductService.getProduct(ProductName);
        request.setAttribute("products", products);
        request.setAttribute("user", HttpSession.getAttribute("user"));
        
        view = request.getRequestDispatcher("login.jsp");
        view.forward(request, response);
    }
}