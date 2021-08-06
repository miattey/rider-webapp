package model.dao;

import model.pojo.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import static java.sql.Types.NULL;
import java.util.ArrayList;
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

    private ArrayList rsToList() throws SQLException {
        ArrayList aList = new ArrayList();

        int cols = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            String[] s = new String[cols];
            for (int i = 1; i <= cols; i++) {
                s[i-1] = rs.getString(i);
            }
            aList.add(s);
        } // while
        return aList;
    } //rsToList

    private String makeHtmlTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        for (Object s : list) {
            b.append("<tr>");
            row = (String[]) s;
            for (String row1 : row) {
                b.append("<td>");
                b.append(row1);
                b.append("</td>");
            }
            b.append("</tr>\n");
        } // for
        b.append("</table>");
        return b.toString();
    }//makeHtmlTable

    private String makeTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append(String.format("%-12s %-12s\n","Username","Password"));
        b.append("================================");
        for (Object s : list) {
            b.append("\n");
            row = (String[]) s;
            for (String row1 : row) {
                //b.append("\t");
                b.append(String.format("%-12s",row1));
                //b.append("\t");
            }//for
            // b.append("\n");
        } // for
        b.append("\n");
        b.append("================================");
        return b.toString();
    }//makeTable

    private void select(String query){
        //Statement statement = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            //statement.close();
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
    }
    public String retrieve(String query) throws SQLException {
        String results="";
        select(query);

        return makeTable(rsToList());//results;
    }

    public boolean exists(String user) {
        boolean bool = false;
        try  {
            select("select username from users where username='"+user+"'");
            if(rs.next()) {
                System.out.println(user+" exits in the DB");
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
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

                user.setName(rs.getString("FULLNAME"));
                user.setType(rs.getString("TYPE"));
                user.setUsername(rs.getString("USERNAME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public void insert(String[] str){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO users VALUES (?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim());
            ps.setString(2, str[1]);
            ps.executeUpdate();

            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void update(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Users Set password=? where username=?",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[1].trim());
            ps.setString(2, str[0].trim());
            ps.executeUpdate();

            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(String user){

        String query = "DELETE FROM users " +
                "WHERE username = '"+user.trim()+"'";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
    }
    public void closeAll(){
        try {
            rs.close();
            statement.close();
            //connection.close();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }





}
