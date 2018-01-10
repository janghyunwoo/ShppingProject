package web;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class UpdateProductServlet extends HttpServlet {
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
        HttpSession HttpSession = request.getSession();
        int productid = Integer.parseInt(request.getParameter("productid"));
        request.setAttribute("productid", productid);
        request.setAttribute("user", HttpSession.getAttribute("user"));
        view = request.getRequestDispatcher("admin/update.jsp");
        view.forward(request, response);
    }
}