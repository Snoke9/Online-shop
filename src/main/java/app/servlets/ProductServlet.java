package app.servlets;

import app.entities.Product;
import app.model.Store;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/products.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("addProduct")) {
            String productName = req.getParameter("productName");
            String productDescription = req.getParameter("description");
            Float productPrice = Float.parseFloat(req.getParameter("prodPrice"));
            if (productName != null && !productName.trim().isEmpty()) {
                Store.addProduct(new Product(productName, productDescription, productPrice));
            }

        }
        else if (action.equals("delete")) {
            String idParam = req.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                int id = Integer.parseInt(idParam);
                Store.deleteProduct(id);
            }
        }
        resp.sendRedirect("/products");
    }
}
