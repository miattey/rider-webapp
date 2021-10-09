package model.dao;

import model.pojo.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverDAO {


    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;

    public void connect(Connection con){
        connection = con;
    }

    public Driver findDriverByID(int ID)
    {
        Driver driver = null;

        try
        {

            //Statement to find all drivers
            PreparedStatement str = connection.prepareStatement("select * from driver inner join users on driver.USER_ID = users.ID where driver.id = ?");
            str.setInt(1, ID);
            ResultSet rs = str.executeQuery();

            while (rs.next())
            {  //iterate through response creating new drivers and adding to list
                driver = new Driver(rs.getInt("id"), rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("registration"));
                //break;
            }

            str.close();


        } catch (Exception e)
        {
            System.out.println(e);
        }

        return driver;
    }

    public Driver findDriverByUserID(int ID)
    {
        Driver driver = null;

        try
        {
            //Statement to find all drivers
            PreparedStatement str = connection.prepareStatement("select * from driver inner join users on driver.USER_ID = users.ID where driver.USER_ID = ?");
            str.setInt(1, ID);
            ResultSet rs = str.executeQuery();

            while (rs.next())
            {  //iterate through response creating new drivers and adding to list
                driver = new Driver(rs.getInt("id"), rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("registration"));
                //break;
            }

            str.close();


        } catch (Exception e)
        {
            System.out.println(e);
        }

        return driver;
    }

    public List<Driver> getAllDrivers()
    {
        List<Driver> drivers = new ArrayList<>();
        try
        {

            //Statement to find all drivers
            PreparedStatement str = connection.prepareStatement("select * from driver inner join users on driver.USER_ID = users.ID");
            ResultSet rs = str.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response creating new drivers and adding to list
                Driver d = new Driver(rs.getInt("id"), rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("registration"));
                drivers.add(d);
            }

            str.close();


        } catch (Exception e)
        {
            System.out.println(e);
        }

        return drivers;
    }

    public boolean removeDriver(Driver d)
    {
        boolean success = false;
        try
        {

            PreparedStatement str = connection.prepareStatement("DELETE FROM driver WHERE id = ?");
            str.setInt(1, d.getID());
            str.executeUpdate();
            str.close();

            PreparedStatement str2 = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            str2.setInt(1, d.getUser_id());
            str2.executeUpdate();
            str2.close();


        } catch (Exception e)
        {
            System.out.println(e);
        }

        return success;
    }

    public void updateDriver(Driver d)
    {
        try
        {
            //Update users table

            PreparedStatement str = connection.prepareStatement("UPDATE users set username = ?, first_name = ?, last_name = ? WHERE id = ?");
            str.setString(1, d.getUsername());
            str.setString(2, d.getFirstName());
            str.setString(3, d.getLastName());
            str.setInt(4, d.getUser_id());

            // execute insert SQL stetement
            str.executeUpdate();

            str.close();

            //Update driver table
            PreparedStatement str2 = connection.prepareStatement("UPDATE driver set registration = ? WHERE id = ?");
            str2.setString(1, d.getReg());
            str2.setInt(2, d.getID());

            str2.executeUpdate();

            str2.close();



        } catch (SQLException ex)
        {

            System.out.println("FAILED IN UPDATING DRIVER");
        }
    }

    public void newDriver(String reg, int userID)
    {
        try
        {

            PreparedStatement str = connection.prepareStatement("INSERT INTO driver(REGISTRATION, USER_ID) VALUES (?, ?)");
            str.setString(1, reg);
            str.setInt(2, userID);

            // execute insert SQL stetement
            str.executeUpdate();

            str.close();


        } catch (SQLException ex)
        {

            System.out.println("FAILED IN CREATING DRIVER");
        }
    }
}
