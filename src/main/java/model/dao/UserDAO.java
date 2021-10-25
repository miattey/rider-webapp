package model.dao;

import model.pojo.Customer;
import model.pojo.User;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    //String query = null;


    public UserDAO(String query){
        //this.query = query;
    }

    public UserDAO() {
        //this.query = "";
    }

    public void connect(Connection con){
        connection = con;
    }




    public User authenticate(String username, String password) {
        User user = null;
        try  {

            String query = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
            PreparedStatement str = connection.prepareStatement(query);

            str.setString(1, username);
            str.setString(2, password);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                user = new User();


                user.setID(rs.getInt("ID"));
                user.setUsertype(rs.getString("USERTYPE"));
                user.setUsername(rs.getString("USERNAME"));
                user.setFirstName(rs.getString("FIRST_NAME"));
                user.setLastName(rs.getString("LAST_NAME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User findByName(String username)
    {
        User user = null;

        try
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT * from USERS where USERNAME = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                user = new User(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("USERTYPE"));
                user.setID(rs.getInt("ID"));
            }


        } catch (Exception e)
        {
            System.out.println(e);
        }
        return user;
    }

    public void addUser(String fn, String ln, String pass, String un, String r)
    {

        try
        {

            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users(USERNAME, PASSWORD, USERTYPE, FIRST_NAME, LAST_NAME) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, un);
            stmt.setString(2, pass);
            stmt.setString(3, r);
            stmt.setString(4, fn);
            stmt.setString(5, ln);

            // execute insert SQL stetement
            stmt.executeUpdate();

            stmt.close();


        } catch (SQLException ex)
        {
            System.out.println("FAILED IN CREATING USER");
        }
    }

    public User addCustomer(String fn, String ln, String pass, String un, String add)
    {
        this.addUser(fn, ln, pass, un, "CUSTOMER");
        User user = this.findByName(un);

        try
        {

            PreparedStatement stmt = connection.prepareStatement("INSERT INTO customer(ADDRESS, USER_ID) VALUES (?, ?)");
            stmt.setString(1, add);
            stmt.setInt(2, user.getID());

            // execute insert SQL stetement
            stmt.executeUpdate();

            stmt.close();


        } catch (SQLException ex)
        {
            System.out.println("FAILED IN CREATING CUSTOMER");
        }

        return user;
    }

    public void updateUser(User user) {
        try
        {

            PreparedStatement stmt = connection.prepareStatement("UPDATE users set username = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getLastName());
            stmt.setInt(5, user.getID());

            // execute insert SQL stetement
            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException ex)
        {
            System.out.println("FAILED IN CREATING CUSTOMER");
        }
    }





}
