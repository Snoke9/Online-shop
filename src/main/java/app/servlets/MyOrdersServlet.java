package app.servlets;

import app.entities.Order;
import app.entities.User;
import app.model.Store;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/my_orders")
public class MyOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        List<Order> userOrders = Store.getOrders().stream()
                .filter(order -> order.getUser().equals(user))
                .collect(Collectors.toList());

        req.setAttribute("orders", userOrders);
        req.getRequestDispatcher("view/my_orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // false — чтобы не создавать новую сессию
        String action = req.getParameter("action");
        if ("exit".equals(action)) {
            session.invalidate();
            resp.sendRedirect("/login");
            return;
        }
        String orderIdStr = req.getParameter("orderId");
        if (orderIdStr != null) {
            int orderId = Integer.parseInt(orderIdStr);
            Store.cancelOrderById(orderId);
        }
        resp.sendRedirect("/my_orders");
    }
}
