package web;

import domain.Basket;
import domain.BasketService;
import domain.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public final class RetrieveBasketServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher view = null;
        BasketService BasketService = null;

        HttpSession HttpSession = request.getSession();
        int userid = Integer.parseInt(request.getParameter("userid"));

        ArrayList<Basket> baskets = null;
        BasketService = new BasketService();
        baskets = BasketService.getBasket(userid);

        request.setAttribute("user", HttpSession.getAttribute("user"));
        request.setAttribute("baskets", baskets);

        view = request.getRequestDispatcher("basket.jsp");
        view.forward(request, response);
    }
}