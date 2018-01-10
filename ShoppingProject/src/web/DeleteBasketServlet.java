package web;

import domain.Basket;
import domain.BasketService;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteBasketServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher view = null;
        BasketService BasketService = new BasketService();
        HttpSession HttpSession = request.getSession();

        int basketid = Integer.parseInt(request.getParameter("basketid"));
        int userid = Integer.parseInt(request.getParameter("userid"));

        ArrayList<Basket> baskets = null;
        BasketService.deleteBasket(userid, basketid);
        baskets = BasketService.getBasket(userid);

        request.setAttribute("baskets", baskets);
        request.setAttribute("user", HttpSession.getAttribute("user"));

        view = request.getRequestDispatcher("basket.jsp");
        view.forward(request, response);
    }
}