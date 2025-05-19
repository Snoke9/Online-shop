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

@WebServlet("/")
public class LoginServlet extends HttpServlet {
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
                resp.sendRedirect("/products");
            }
            else if (userName != null && password != null) {
                for (User user : Store.getUsers()) {
                    if (user.getName().equals(userName) && user.getPassword().equals(password)) {
                        session.setAttribute("user", user); // где user — это объект User
                        found = true;
                        resp.sendRedirect("/orders");
                        break;
                    }
                }
                if (!found) {
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }
}
