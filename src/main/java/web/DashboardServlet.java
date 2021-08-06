package web;

import model.pojo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DashboardServlet", value = "/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user  = (User) session.getAttribute("user");

        String type = user.getType().toUpperCase();

        switch (type) {
            case "ADMIN":
                request.getRequestDispatcher("/admindashboard.jsp").forward(request, response);
                break;
            case "USER":
                request.getRequestDispatcher("/customerdashboard.jsp").forward(request, response);
                break;
            case "DRIVER":
                request.getRequestDispatcher("/driverdashboard.jsp").forward(request, response);
                break;


        }



        /**
        if  (user.getType().equals("admin")) {
            request.getRequestDispatcher("/admindashboard.jsp").forward(request, response);
        } else if (user.getType().equals("user")) {
            request.getRequestDispatcher("/customerdashboard.jsp").forward(request, response);
        } else if (user.getType().equals("driver")) {
            request.getRequestDispatcher("/driverdashboard.jsp").forward(request, response);
        }
         **/


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
