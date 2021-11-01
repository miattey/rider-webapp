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

@WebServlet(name = "ManageCustomersServlet", value = "/ManageCustomersServlet")
public class ManageCustomersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connection");

        CustomerDAO customerdao = new CustomerDAO();
        customerdao.connect(connection);

        String spageid=request.getParameter("page");
        int pageid=Integer.parseInt(spageid);
        int total=10;

        if(pageid==1){}
        else{
            pageid=pageid-1;
            pageid=pageid*total+1;
        }


        List<Customer> customers = customerdao.getAllCustomersPagination(pageid, total);
        List<Customer> customertotal = customerdao.getAllCustomers();

        int totalNumber = customertotal.size();

        int totalpages  = (totalNumber + total - 1) / total;



        request.setAttribute("all_customers", customers);
        request.setAttribute("pageID", pageid);
        request.setAttribute("pages", totalpages);

        request.getRequestDispatcher("admin/managecustomers.jsp").forward(request, response);


        /**
        List<Customer> customers = customerdao.getAllCustomers();

        request.setAttribute("all_customers", customers);

        request.getRequestDispatcher("admin/managecustomers.jsp").forward(request, response);
        **/


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
