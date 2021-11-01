package web;


import model.dao.BookingDAO;
import model.dao.CustomerDAO;

import model.pojo.Booking;
import model.pojo.Customer;

import model.pojo.User;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;

@WebServlet(name = "BookingServlet", value = "BookingServlet")
public class BookingServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.connect(connection);

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        int user_id = u.getID();

        Customer customer = customerDAO.findCustomerByUserID(user_id);

        int customer_id = customer.getID();

        request.setCharacterEncoding("UTF-8");

        String stage = request.getParameter("stage_booking").toString();

        //Booking details retrieved from jsp
        String start = request.getParameter("start_address");

        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.connect(connection);


        String end = request.getParameter("destination_address");
        Date date = Date.valueOf(request.getParameter("date").toString());
        String timestr = request.getParameter("time");
        String distancetext = request.getParameter("distanceqw");
        String fee_amount = request.getParameter("fare_amounthidden");


        Time time = Time.valueOf(timestr + ":00");


        double distance = Double.parseDouble(distancetext);

        int fee = Integer.parseInt(fee_amount);

        //working on
        if (stage.equals("new")) {



            Time time2 = Time.valueOf(String.valueOf(time));


            Booking j = new Booking(start, end, distance, 0, time2, date, customer_id,  fee);

            bookingDAO.newJourney(j);
            //Redirect
            response.sendRedirect(request.getContextPath() + "/customerdashboard");

        }
        //ends here


        }

}
