package web;

import model.dao.CustomerDAO;
import model.dao.DriverDAO;
import model.pojo.Customer;
import model.pojo.Driver;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "ManageCustomersServlet", value = "/managecustomers")
public class ManageCustomersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connection");

        CustomerDAO customerdao = new CustomerDAO();
        customerdao.connect(connection);

        List<Customer> customers = customerdao.getAllCustomers();

        request.setAttribute("all_customers", customers);

        request.getRequestDispatcher("admin/managecustomers.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
