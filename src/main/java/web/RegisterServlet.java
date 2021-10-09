package web;

import model.dao.UserDAO;
import model.pojo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw e;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connection");

        //Get stuff from form
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String address = request.getParameter("address");

        if (username == null && firstName == null && lastName == null && password == null && password2 == null && address == null)
        {
            request.setAttribute("errMessageRegister", "Fields cannot be left empty");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } else
        {
            UserDAO userdao = new UserDAO();
            userdao.connect(connection);

            User user = userdao.findByName(username);
            if (user != null)
            {
                request.setAttribute("errMessageRegister", "The username " + username + " is already taken");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            } else
            {
                if (password.equals(password2) == false)
                { // Passwords do not match
                    request.setAttribute("errMessageRegister", "Passwords do not match");
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                } else
                {
                    //Create user - users table
                    //Create customer - customers table
                    user = userdao.addCustomer(firstName, lastName, password, username, address);

                    //Log user in
                    if (user != null)
                    {
                        try
                        {
                            //Redirect to the home page
                            response.sendRedirect(request.getContextPath()+"/login");
                        } catch (IOException ex)
                        {
                        }
                    }
                    else
                    {
                        request.setAttribute("errMessageRegister", "An Error has occured");
                        request.getRequestDispatcher("/register.jsp").forward(request, response);
                        System.out.println("Login error - no user");
                    }

                }

            }
        }

    }
}
