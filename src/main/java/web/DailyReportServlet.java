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

@WebServlet(name = "DailyReportServlet", value = "/dailyreport")
public class DailyReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //establish database connection
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        BookingDAO bookingdao = new BookingDAO();
        bookingdao.connect(connection);

        //get todays date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(new Date());
        //Long today = new Long(format.format(new Date()));



        List<Booking> allBookings =bookingdao.bookingsByDate(strDate);
        List<Booking> dailyBookings = new ArrayList<>();
        int totalBookings = allBookings.size();

        for (Booking dJ : allBookings) {
                dailyBookings.add(dJ);
        }


        request.setAttribute("dailybookings", dailyBookings);
        request.setAttribute("totalbookings", totalBookings);
        request.setAttribute("date", strDate);

        request.getRequestDispatcher("admin/generatedailyreport.jsp").forward(request,response);







    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
