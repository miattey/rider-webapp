package web;

import model.dao.BookingDAO;
import model.dao.DriverDAO;
import model.pojo.Booking;
import model.pojo.Driver;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "GenerateReportServlet", value = "/generatereport")
public class GenerateReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connection");

        DriverDAO driverdao = new DriverDAO();
        driverdao.connect(connection);

        List<Driver> alldrivers = driverdao.getAllDrivers();

        request.setAttribute("alldrivers", alldrivers);

        request.getRequestDispatcher("admin/generatereport.jsp").forward(request,response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //establish database connection
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        BookingDAO bookingdao = new BookingDAO();
        DriverDAO driverdao = new DriverDAO();

        driverdao.connect(connection);
        bookingdao.connect(connection);

        //get todays date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(new Date());


        //gets the driver id for the requested report
        int driverID = Integer.parseInt(request.getParameter("driverID"));


        Driver driver = driverdao.findDriverByID(driverID);






        List<Booking> allBookings =bookingdao.bookingsByDate(strDate);
        List<Booking> dailyBookings = new ArrayList<>();

        double totalTurnover = 0;

        for (Booking dJ : allBookings) {
            if (dJ.getDriver_id() == driverID) {
                if (dJ.getStatus() == 2) {
                    dailyBookings.add(dJ);
                    totalTurnover+= dJ.getFee();
                }
            }
        }

        int totalServed = dailyBookings.size();



        request.setAttribute("dailybookings",dailyBookings);
        request.setAttribute("driverdetails",driver);
        request.setAttribute("totalserved",totalServed);
        request.setAttribute("totalTurnover",totalTurnover);

        request.getRequestDispatcher("admin/viewreport.jsp").forward(request,response);




    }
}
