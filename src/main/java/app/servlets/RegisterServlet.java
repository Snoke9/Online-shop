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

import java.io.Console;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("register")) {
            String userName = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            User newUser = new User(userName, password, email);

            if (userName != null && password != null && email != null) {
                Store.addUser(newUser);
            }
        }
        resp.sendRedirect("/login");
    }
}
