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

@WebServlet(name = "DriverDashboardServlet", value = "/DriverDashboardServlet")
public class DriverDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get user details from the session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //establish database connection
        Connection connection = (Connection) getServletContext().getAttribute("connection");


        //connecting dao objects
        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.connect(connection);

        DriverDAO driverDAO = new DriverDAO();
        driverDAO.connect(connection);

        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.connect(connection);

        //fetching driver details from session user id
        Driver driver = driverDAO.findDriverByUserID(user.getID());

        //retrieve the driver id to fetch the bookings
        int driver_id = driver.getID();


        //check to see if a driver


            //list to store assigned bookings for the driver
            List<Booking> cJs;
            cJs = bookingDAO.findJourneysByDriverID(driver_id);

            List<Booking> myjobs = new ArrayList<>();
            List<String> dd = new ArrayList<>();

            for (Booking dJ : cJs) {
                if (dJ.getStatus() == 0 || dJ.getStatus() == 1) {

                    Driver drivers = driverDAO.findDriverByID(driver_id);

                    dJ.setDriver(drivers);

                    Customer customer = customerDAO.findCustomerByID(dJ.getCustomer_id());

                    String aa = "" + customer.getFirstName() + " " + customer.getLastName() + "(" + customer.getUsername() + ")";

                    dd.add(aa);

                    myjobs.add(dJ);

                }



            }

            request.setAttribute("myjobs", myjobs);



        request.getRequestDispatcher("driver/driverdashboard.jsp").forward(request, response);











    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
