package web;

import model.dao.BookingDAO;
import model.dao.CustomerDAO;
import model.dao.DriverDAO;
import model.pojo.Booking;
import model.pojo.Customer;
import model.pojo.Driver;
import model.pojo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminDashboardServlet", value = "/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connection");

        CustomerDAO customerDAO = new CustomerDAO();
        BookingDAO bookingDAO = new BookingDAO();
        customerDAO.connect(connection);
        bookingDAO.connect(connection);

        DriverDAO driverDAO = new DriverDAO();
        driverDAO.connect(connection);

            List<Booking> cJs;
            cJs = bookingDAO.findJourneysDriverNotAssigned();

            List<Booking> mybookings = new ArrayList<>();

            for (Booking dJ : cJs) {
                mybookings.add(dJ);
            }

            request.setAttribute("mybookings", mybookings);

            request.getRequestDispatcher("admin/admindashboard.jsp").forward(request, response);






    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Connection connection = (Connection) getServletContext().getAttribute("connection");

        int buttonid = Integer.parseInt(request.getParameter("assigndriver"));



        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.connect(connection);
        DriverDAO driversDAO = new DriverDAO();
        driversDAO.connect(connection);


        Booking selected = bookingDAO.findJourneyByID(buttonid);



        List<Driver> alldrivers = driversDAO.getAllDrivers();


        request.setAttribute("alldrivers", alldrivers);
        request.setAttribute("selectedbooking", selected);


        request.getRequestDispatcher("admin/assigndriver.jsp").forward(request, response);






    }
}
