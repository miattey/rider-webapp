package model.dao;

import model.pojo.Booking;
import model.pojo.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {


    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;

    public void connect(Connection con){
        connection = con;
    }

    public Booking findJourneyByID(int id)
    {
        Booking j = null;

        try
        {
            //Statement to find all drivers
            PreparedStatement str = connection.prepareStatement("select * from BOOKING where BOOKING.ID = ?");
            str.setInt(1, id);
            ResultSet rs = str.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response and building journey list
                j = new Booking(rs.getInt("id"), rs.getInt("customer_id"),
                        rs.getInt("driver_id"), rs.getString("start"),
                        rs.getString("destination"), rs.getDouble("distance"),
                        rs.getInt("status"), rs.getTime("time"),
                        rs.getDate("date"), rs.getDouble("fee"));
            }

            str.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        return j;
    }

    public List<Booking> findJourneysByCustomerID(int customerID)
    {
        int id = customerID;

        ArrayList<Booking> js = new ArrayList<>();
        try
        {

            //Statement to find all drivers
            PreparedStatement str = connection.prepareStatement("select * from BOOKING inner join CUSTOMER on booking.CUSTOMER_ID "
                    + "= customer.ID where customer.id = ? order by booking.date DESC, booking.time");
            str.setInt(1, id);
            ResultSet rs = str.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response and building journey list
                js.add(new Booking(rs.getInt("id"), rs.getInt("customer_id"),
                        rs.getInt("driver_id"), rs.getString("start"),
                        rs.getString("destination"), rs.getDouble("distance"),
                        rs.getInt("status"), rs.getTime("time"),
                        rs.getDate("date"), rs.getDouble("fee")));
            }

            str.close();


        } catch (Exception e)
        {
            System.out.println(e);
        }
        return js;
    }

    public List<Booking> getJourneys()
    {
        ArrayList<Booking> js = new ArrayList<>();
        try
        {

            //Statement to find all drivers
            PreparedStatement str = connection.prepareStatement("select * from booking inner join driver on booking.DRIVER_ID = driver.id inner join customer on booking.CUSTOMER_ID = customer.ID order by booking.date, booking.time");
            ResultSet rs = str.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response and building journey list
                js.add(new Booking(rs.getInt("id"), rs.getInt("customer_id"),
                        rs.getInt("driver_id"), rs.getString("start"),
                        rs.getString("destination"), rs.getDouble("distance"),
                        rs.getInt("status"), rs.getTime("time"),
                        rs.getDate("date"), rs.getDouble("fee")));
            }

            str.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
        return js;
    }

    public List<Booking> findJourneysByDriverID(int driverID)
    {
        int id = driverID;

        ArrayList<Booking> js = new ArrayList<>();
        try
        {

            //Statement to find all drivers
            PreparedStatement str = connection.prepareStatement("select * from BOOKING inner join DRIVER on booking.DRIVER_ID "
                    + "= driver.ID where driver.ID = ? order by booking.date DESC, booking.time");
            str.setInt(1, id);
            ResultSet rs = str.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response and building journey list
                js.add(new Booking(rs.getInt("id"), rs.getInt("customer_id"),
                        rs.getInt("driver_id"), rs.getString("start"),
                        rs.getString("destination"), rs.getDouble("distance"),
                        rs.getInt("status"), rs.getTime("time"),
                        rs.getDate("date"), rs.getDouble("fee")));
            }

            str.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }

        return js;
    }

    public List<Driver> findAvailableDrivers(Date date, Time time)
    {

        List<Driver> drivers = new ArrayList<>();

        try
        {

            //Statement to find all drivers
            PreparedStatement str = connection.prepareStatement("select * from DRIVER inner join BOOKING on DRIVER.id "
                    + "=BOOKING.DRIVER_ID where BOOKING.time != ? AND BOOKING.date != ?");
            str.setDate(1, date);
            str.setTime(2, time);
            ResultSet rs = str.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {

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

    public List<Booking> findJourneysDriverNotAssigned()
    {


        ArrayList<Booking> js = new ArrayList<>();
        try
        {

            //Statement to find all drivers
            PreparedStatement str = connection.prepareStatement("select * from BOOKING where BOOKING.DRIVER_ID is NULL order by BOOKING.date, BOOKING.time");

            ResultSet rs = str.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response and building journey list
                js.add(new Booking(rs.getInt("id"), rs.getInt("customer_id"),
                        rs.getInt("driver_id"), rs.getString("start"),
                        rs.getString("destination"), rs.getDouble("distance"),
                        rs.getInt("status"), rs.getTime("time"),
                        rs.getDate("date"), rs.getDouble("fee")));
            }

            str.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }

        return js;
    }

    public List<Booking> allJourneys()
    {
        ArrayList<Booking> js = new ArrayList<>();

        try
        {

            //Statement to find all drivers
            PreparedStatement str = connection.prepareStatement("select * from BOOKING ");
            ResultSet rs = str.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response and building journey list
                js.add(new Booking(rs.getInt("id"), rs.getInt("customer_id"),
                        rs.getInt("driver_id"), rs.getString("start"),
                        rs.getString("destination"), rs.getDouble("distance"),
                        rs.getInt("status"), rs.getTime("time"),
                        rs.getDate("date"), rs.getDouble("fee")));
            }

            str.close();


        } catch (Exception e)
        {
            System.out.println(e);
        }

        return js;
    }

    public List<Booking> bookingsByDate(String date)
    {
        ArrayList<Booking> js = new ArrayList<>();

        try
        {

            //Statement to find all drivers
            PreparedStatement str = connection.prepareStatement("select * from BOOKING where Booking.date = ?");
            str.setString(1, date);
            ResultSet rs = str.executeQuery();
            System.out.println(rs.toString());
            while (rs.next())
            {  //iterate through response and building journey list
                js.add(new Booking(rs.getInt("id"), rs.getInt("customer_id"),
                        rs.getInt("driver_id"), rs.getString("start"),
                        rs.getString("destination"), rs.getDouble("distance"),
                        rs.getInt("status"), rs.getTime("time"),
                        rs.getDate("date"), rs.getDouble("fee")));
            }

            str.close();


        } catch (Exception e)
        {
            System.out.println(e);
        }

        return js;
    }

    public void newJourney(Booking j)
    {
        try
        {




            PreparedStatement stmt = connection.prepareStatement("INSERT INTO BOOKING(CUSTOMER_ID, START, DESTINATION, "
                    + "DISTANCE, DATE, TIME, FEE, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setInt(1, j.getCustomer_id());
            stmt.setString(2, j.getStart());
            stmt.setString(3, j.getEnd());
            stmt.setDouble(4, j.getDistance());
            stmt.setDate(5, (Date) j.getDate());
            stmt.setTime(6, j.getTime());
            stmt.setDouble(7, j.getFee());
            stmt.setInt(8, j.getStatus());
            stmt.executeUpdate();

            stmt.close();



        } catch (SQLException ex)
        {

            System.out.println("FAILED IN CREATING DRIVER");
        }
    }

    public void updateDriverID(int bookingid, int driverid) {
        try
        {

            PreparedStatement stmt = connection.prepareStatement("UPDATE BOOKING set BOOKING.DRIVER_ID = ? WHERE BOOKING.ID = ?");
            stmt.setInt(1, driverid);
            stmt.setInt(2, bookingid);


            // execute insert SQL stetement
            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException ex)
        {
            System.out.println("FAILED IN CREATING CUSTOMER");
        }
    }

    public void updateBookingStatus(int bookingid, int status) {
        try
        {

            PreparedStatement stmt = connection.prepareStatement("UPDATE BOOKING set BOOKING.STATUS = ? WHERE BOOKING.ID = ?");
            stmt.setInt(1, status);
            stmt.setInt(2, bookingid);


            // execute insert SQL stetement
            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException ex)
        {
            System.out.println("FAILED IN CREATING CUSTOMER");
        }
    }






}