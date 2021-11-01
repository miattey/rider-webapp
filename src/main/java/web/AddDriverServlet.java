package web;

import model.dao.DriverDAO;
import model.dao.UserDAO;
import model.pojo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "AddDriverServlet", value = "/AddDriverServlet")
public class AddDriverServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("admin/addnewdriver.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        DriverDAO driverdao = new DriverDAO();
        driverdao.connect(connection);
        UserDAO userdao = new UserDAO();
        userdao.connect(connection);

        if  (request.getParameter("add_new") != null) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String registrationNo = request.getParameter("vehicleregno");



            if (userdao.findByName(username) != null) {
                request.setAttribute("errormessage", "The username " + username + " is already assigned for a driver");
                request.getRequestDispatcher("admin/addnewdriver.jsp").forward(request, response);

            } else {
                userdao.addUser(firstName, lastName, password, username, "DRIVER");
                User u = userdao.findByName(username);
                driverdao.newDriver(registrationNo, u.getID());
                response.sendRedirect(request.getContextPath() + "/managedrivers");

            }
        }

    }
}
