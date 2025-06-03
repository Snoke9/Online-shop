package app.servlets;

import app.entities.Order;
import app.entities.Product;
import app.entities.User;
import app.model.Store;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/orders.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // false — чтобы не создавать новую сессию
        User user = (User) session.getAttribute("user");;

        String action = req.getParameter("action");
        List<Product> products = new ArrayList<>();

        if ("create".equals(action)) {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String phoneNumber = req.getParameter("phone");
            String address = req.getParameter("address");
            float totalPrice = Float.parseFloat(req.getParameter("totalPrice"));

            if (totalPrice == 0f) {
                session.setAttribute("errorMessage", "Добавьте хотя бы один товар");
            }
            else {
                Store.createOrder(new Order(user, products, firstName, lastName, phoneNumber, address, totalPrice));
                System.out.println(Store.getOrders().get(0).getTotalPrice());
                System.out.println(Store.getOrders().get(0).getUser().getName());
                System.out.println(user.getName());
            }
            resp.sendRedirect("/orders");
        }
        else if ("exit".equals(action)) {
            session.invalidate();
            resp.sendRedirect("/login");
        }
    }
}
