package web;

import model.dao.UserDAO;
import model.pojo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw e;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get the user details from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //returns the connection object from the dblkister
        Connection connection = (Connection) getServletContext().getAttribute("connection");

        //instance of userdao and database connection set for user login authentication
        UserDAO userdao = new UserDAO();
        userdao.connect(connection);

        User user = userdao.authenticate(username,password);

        if (!(username.isEmpty() || password.isEmpty())) {
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(600);
                response.sendRedirect(request.getContextPath() + "/dashboard");

            } else {
                String message = "<div class=\"alert alert-danger\" role=\"alert\">Invalid username or password </div>";
                request.setAttribute("message", message);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            String message = "<div class=\"alert alert-danger\" role=\"alert\">Please fill in all fields.</div>";
            request.setAttribute("message", message);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }















    }
}
