package web;

import model.dao.BookingDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "EditBookingServlet", value = "/EditBookingServlet")
public class EditBookingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.connect(connection);


        if (request.getParameter("approve") != null) {

            int booking_id = Integer.parseInt(request.getParameter("approve"));
            bookingDAO.updateBookingStatus(booking_id,1);


        }else if (request.getParameter("reject") != null) {
            int booking_id = Integer.parseInt(request.getParameter("reject"));
            bookingDAO.updateBookingStatus(booking_id,3);

        } else if (request.getParameter("paid") != null) {
            int booking_id = Integer.parseInt(request.getParameter("paid"));
            bookingDAO.updateBookingStatus(booking_id,2);
        }

        response.sendRedirect(request.getContextPath()+"/driverdashboard");





    }



}
