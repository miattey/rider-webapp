package web;

import model.dao.DriverDAO;
import model.dao.UserDAO;
import model.pojo.Driver;
import model.pojo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "ManageDriversServlet", value = "/ManageDriversServlet")
public class ManageDriversServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connection");

        DriverDAO driverdao = new DriverDAO();
        driverdao.connect(connection);

        List<Driver> drivers = driverdao.getAllDrivers();

        request.setAttribute("all_drivers", drivers);

        request.getRequestDispatcher("admin/managedrivers.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //establish database connection
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        DriverDAO driverdao = new DriverDAO();
        driverdao.connect(connection);
        UserDAO userdao = new UserDAO();
        userdao.connect(connection);

        if (request.getParameter("delete_id") != null) {

            int driverID= Integer.parseInt(request.getParameter("delete_id"));

            Driver toBeDeleted = driverdao.findDriverByID(driverID);
            driverdao.removeDriver(toBeDeleted);

            //Redirect the user
            response.sendRedirect(request.getContextPath()+"/managedrivers");



        }














    }
}
