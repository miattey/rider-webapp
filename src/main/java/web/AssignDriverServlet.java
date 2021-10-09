package web;

import model.dao.BookingDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "AssignDriverServlet", value = "/assigndriver")
public class AssignDriverServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Connection connection = (Connection) getServletContext().getAttribute("connection");


        int bookingid = Integer.parseInt(request.getParameter("bookingid"));
        int selectedDriverID = Integer.parseInt(request.getParameter("isTitles"));

        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.connect(connection);

        bookingDAO.updateDriverID(bookingid,selectedDriverID);

        response.sendRedirect(request.getContextPath() + "/admindashboard");






    }
}
