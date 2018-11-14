package db;

import java.sql.*;
import java.util.*;

public class User{

    // User Manager class to access a User's information.
    // username is used to search for a user in the database.

    public String username;
    public String name;
    public String address;
    public String email;
    public String dob;
    
    public User(String username){
        // Constructor
        // Gets Data by querying the databse with username
        this.username = username;
        Connection conn  = connectToDatabase();

        if (conn!=null){
            try{
                Statement getData = conn.createStatement();
                ResultSet r = getData.executeQuery("SELECT * FROM USER WHERE username='" + username+"'");
                while(r.next()){
                    this.name = r.getString("name");
                    this.address = r.getString("address");
                    this.email = r.getString("email");
                    this.dob = r.getString("dob");
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static Connection connectToDatabase(){
        /*
         * CLOSE THE CONNECTION OBJECT RETURNED FROM THIS METHOD AFTER USE.
         * NAME OF THE DATABASE IS USER. DON'T CONFUSE IT WITH THE TABLE NAME.
         * NAME OF THE TABLE FOR USERS IS ALSO 'user'
         */

        String USER = "root"; // MySQl server username
        String PASS = "root"; // MySQl server password
        String DB_URL = "jdbc:mysql://localhost/user";  // database = user
        Connection conn = null;
        try{
            // JDBC Driver
            // Needs help
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to Database // User");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected");

        }
        catch(SQLException se){
            se.printStackTrace();   
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            //finally returns connection object
            try{
                return conn;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }

    public Booking[] getBookings(){
        Booking[] results = new Booking[10];
        int i = 0;
        try{
            Connection conn =  Booking.connectToDatabase();
            Statement getBookings = conn.createStatement();
            ResultSet r =getBookings.executeQuery(String.format("SELECT * FROM booking WHERE username='%s'", this.username));
            while(r.next()){
                results[i] = new Booking(r.getString("ref"));
                System.out.println(results[i]);
                i++;
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        return results;
    }

    public static User login(String username, String password){
        // This basically authenticates if the username and password is correct
        // and returns User object if correct
        try{
            Connection conn = connectToDatabase();
            Statement getUser = conn.createStatement();
            ResultSet r = getUser.executeQuery("SELECT * FROM user WHERE password=" + "'"  + password + "'" + " AND username=" + "'" + username + "'" );
            while(r.next()){
                if(r.getString("username") != null){
                    return new User(username);
                }
                else{
                    return null;
                }
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }

        return null;
    }

    public static void registerUser(String username, String password,
                                    String address, String email, String dob,
                                    String name)
    {   /*
         * Should have valid Arguments. Validate beforehand!
         */

        try{
            Connection conn = connectToDatabase();
            Statement addUser = conn.createStatement();
            // SQL STATEMENT
            String statement = "INSERT INTO user(username, password, name, email, address, dob)";
            String values = "VALUES('" + username + "','" + password + "','" + name + "','" + email + "','" + address + "','" + dob + "')";
            addUser.execute(statement+values);
        }

        catch(SQLException se){
            System.out.println(se.toString());
        }
    }
}