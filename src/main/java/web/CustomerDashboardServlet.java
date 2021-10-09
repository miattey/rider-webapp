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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerDashboardServlet", value = "/customerdashboard")
public class CustomerDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");



        Connection connection = (Connection) getServletContext().getAttribute("connection");

        CustomerDAO customerDAO = new CustomerDAO();
        BookingDAO bookingDAO = new BookingDAO();
        customerDAO.connect(connection);
        bookingDAO.connect(connection);

        DriverDAO driverDAO = new DriverDAO();
        driverDAO.connect(connection);

        Customer customer = customerDAO.findCustomerByUserID(user.getID());

        int customer_id = customer.getID();



        if (user.getUsertype().equals("CUSTOMER")) {
            List<Booking> cJs;
            cJs = bookingDAO.findJourneysByCustomerID(customer_id);

            List<Booking> mybookings = new ArrayList<>();

            for (Booking dJ : cJs) {
                if (dJ.getDriver_id() != 0) {
                    Driver driver = driverDAO.findDriverByID(dJ.getDriver_id());

                    dJ.setDriver(driver);
                }
                mybookings.add(dJ);
            }

            request.setAttribute("mybookings", mybookings);

            request.getRequestDispatcher("customer/customerdashboard.jsp").forward(request, response);




        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
