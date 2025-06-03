package app.servlets;

import app.entities.Product;
import app.entities.User;
import app.model.Store;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public void init() throws ServletException {
        Store.addProduct(new Product("Андроид", "Робот работает за тебя", 999999f));
        Store.addProduct(new Product("Мерседес GLE Coupe d450", "Авто для хасла", 1499999f));
        Store.addProduct(new Product("Телевизор", "52-дюймовый 4K Smart TV Optima OS WW Xiaomi LG", 499999f));
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("login")) {
            HttpSession session = req.getSession();
            String userName = req.getParameter("username");
            String password = req.getParameter("password");
            boolean found = false;
            if (userName.equals("admin") && password.equals("admin")) {
                session.setAttribute("user", new User("admin", "admin", "admin@admin.com"));
                resp.sendRedirect("/products");
            }
            else if (userName != null && password != null) {
                for (User user : Store.getUsers()) {
                    if (user.getName().equals(userName) && user.getPassword().equals(password)) {
                        session.setAttribute("user", user);
                        found = true;
                        resp.sendRedirect("/orders");
                        break;
                    }
                }
                if (!found) {
                    session.setAttribute("errorMessage", "Неверное имя пользователя или пароль");
                    resp.sendRedirect("/login");
                }
            }
        }
    }
}
