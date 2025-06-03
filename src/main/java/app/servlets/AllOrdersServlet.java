package app.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import app.entities.Order;
import app.model.Store;

@WebServlet("/all_orders")
public class AllOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        List<Order> orders = Store.getOrders();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("view/all_orders.jsp").forward(req, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String orderIdStr = req.getParameter("orderId");
        String action = req.getParameter("action");

        if ("exit".equals(action)) {
            session.invalidate();
            resp.sendRedirect("/login");
            return;
        }
        if (orderIdStr != null && action != null) {
            int orderId = Integer.parseInt(orderIdStr);
            switch (action) {
                case "complete":
                    Store.updateStatus(orderId, "Выполнен");
                    break;
                case "reject":
                    Store.updateStatus(orderId, "Отклонен");
                    break;
                case "delete":
                    Store.deleteOrder(orderId);
                    break;
            }
        }
        resp.sendRedirect("/all_orders");
    }
}
