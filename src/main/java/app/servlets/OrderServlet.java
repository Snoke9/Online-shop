package app.servlets;

import app.entities.Order;
import app.entities.Product;
import app.entities.User;
import app.model.Store;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/orders.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        String action = req.getParameter("action");
        Map<Product, Integer> products = new HashMap<>();

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
                for (Product product : Store.getProducts()) {
                    String paramName = "quantity_" + product.getId();
                    String qtyStr = req.getParameter(paramName);
                    if (qtyStr != null) {
                        try {
                            int quantity = Integer.parseInt(qtyStr);
                            if (quantity > 0) {
                                products.put(product, quantity);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
                Store.createOrder(new Order(user, products, firstName, lastName, phoneNumber, address, totalPrice));
            }
            resp.sendRedirect("/orders");
        }
        else if ("exit".equals(action)) {
            session.invalidate();
            resp.sendRedirect("/login");
        }
    }
}
