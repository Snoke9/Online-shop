package app.servlets;

import app.entities.Product;
import app.model.Store;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

//@WebServlet("/products")

public class ProductServlet extends HttpServlet {
    public void init() throws ServletException {
        Store.addProduct(new Product("Андроид", "Робот работает за тебя", 999999f));
        Store.addProduct(new Product("Мерседес GLE Coupe d450", "Авто для хасла", 1499999f));
        Store.addProduct(new Product("Телевизор", "52-дюймовый 4K Smart TV Optima OS WW Xiaomi LG", 499999f));
    }

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
