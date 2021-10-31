package model.dao;

import model.pojo.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;

    public void connect(Connection con){
        connection = con;
    }


    public Customer findCustomerByID(int ID) {
        Customer customer = null;
        try {
            PreparedStatement str = connection.prepareStatement("select * from customer inner join users on customer.USER_ID = users.ID where customer.id = ?");
            str.setInt(1, ID);
            ResultSet rs = str.executeQuery();

            while (rs.next())
            {  //iterate through response creating new drivers and adding to list
                customer = new Customer(rs.getInt("id"),rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"),rs.getString("address"));
                //break;
            }

            str.close();


        } catch (Exception e)
        {
            System.out.println(e);
        }

        return customer;
    }

    public Customer findCustomerByUserID(int ID) {
        Customer customer = null;
        try {

            String query = "SELECT * from CUSTOMER inner join USERS on CUSTOMER.USER_ID = USERS.ID where USERS.ID = ?";
            PreparedStatement str = connection.prepareStatement(query);
            str.setInt(1, ID);
            ResultSet rs = str.executeQuery();

            //Statement to find all drivers
            //PreparedStatement str = connection.prepareStatement("SELECT * from CUSTOMER inner join USERS on CUSTOMER.USER_ID = USERS.ID where USERS.ID = ?");
            //str.setInt(1, ID);
            // rs = str.executeQuery();

            //iterate through response creating new drivers and adding to list
            while (rs.next()) {
                customer = new Customer(rs.getInt("id"),rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"),rs.getString("address"));
                //break;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return customer;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {

            //Statement to find all customers
            PreparedStatement str = connection.prepareStatement("select * from customer inner join users on customer.USER_ID = users.ID");
            ResultSet rs = str.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response creating new customers and adding to list
                Customer d = new Customer(rs.getInt("id"),rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"),rs.getString("address"));
                customers.add(d);
            }

            str.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }



        return customers;
    }

    public List<Customer> getAllCustomersPagination(int start,int total) {
        List<Customer> customers = new ArrayList<>();
        try {

            //Statement to find all customers
            PreparedStatement str = connection.prepareStatement("select * from customer  inner join users on customer.USER_ID = users.ID offset "+(start-1)+" rows fetch first "+total+" rows only");
            ResultSet rs = str.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response creating new customers and adding to list
                Customer d = new Customer(rs.getInt("id"),rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"),rs.getString("address"));
                customers.add(d);
            }

            str.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }



        return customers;
    }

    public void updateCustomer(Customer c)
    {
        try
        {
            //Update users table

            PreparedStatement str = connection.prepareStatement("UPDATE users set username = ?, first_name = ?, last_name = ? WHERE id = ?");
            str.setString(1, c.getUsername());
            str.setString(2, c.getFirstName());
            str.setString(3, c.getLastName());
            str.setInt(4, c.getUser_id());

            // execute insert SQL stetement
            str.executeUpdate();

            str.close();

            //Update customer table
            PreparedStatement str2 = connection.prepareStatement("UPDATE customer set address = ? WHERE id = ?");
            str2.setString(1, c.getAddress());
            str2.setInt(2, c.getID());

            str2.executeUpdate();

            str2.close();


        } catch (SQLException ex)
        {

        }
    }

    public void removeCustomer(Customer c)
    {
        try
        {

            PreparedStatement str = connection.prepareStatement("DELETE FROM customer WHERE id = ?");
            str.setInt(1, c.getID());
            str.executeUpdate();
            str.close();

            PreparedStatement str2 = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            str2.setInt(1, c.getUser_id());
            str2.executeUpdate();
            str2.close();


        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
