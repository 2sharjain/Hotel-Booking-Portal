package db;

import java.sql.*;
import java.util.*;

// Run command  - "java -cp .;lib/mysql-connector-java-8.0.13.jar {{CLASS NAME}}" to run with databse
// NEED HELP WITH THIS ^
// There are other ways

public class User{

    // User Manager class to access a User's information.
    // username is used to search for a user in the database.

    String username;
    String name;
    String address;
    String email;
    String dob;
    private Connection conn;

    public User(String username){
        this.username = username;
        // gets other info by making a query to database
        // code 
    }

    private Connection connectToDatabase(){
        /*
         * CLOSE THE CONNECTION OBJECT RETURNED FROM THIS METHOD AFTER USE.
         */

        private String USER = "root"; // MySQl server username
        private String PASS = "root"; // MySQl server password
        private String DB_URL = "jdbc:mysql://localhost/";
        private Statement createUserDB = null;
        try{
            // JDBC Driver
            // Needs help
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to User");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // This try block catches exception if database already exists
            try{
                // Creating a Database. Will be populated when users register.
                String createDB = "CREATE DATABASE USER";
                createUserDB = conn.createStatement();
                createUserDB.execute(createDB);
                System.out.println("Created Database Successfully");
            }
            catch(Exception se){
                // Handles Error
                se.printStackTrace();
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            //finally block used to return connection object
            try{
                if(createUserDB!=null)
                createUserDB.close();
            }
            catch(SQLException se2){
                // pass
            }
            try{
                if(conn!=null)
                return conn;
            }
            catch(SQLException se){
                se.printStackTrace();
                return null;
            }
        }
    }

    public Booking[] getBooking(){
        // returns array of all Bookings
        // code
    }

    public Booking getBooking(String ref){
        // returns booking by the reference ID
        // code
    }

    public Static void createBooking(){
        // TODO: add arguments

        // creates new booking for the user in the database and returns the Booking object for it
        // code
    }

    public void logout(){
        // logs the user out.
        // code
    }

    public static User login(String username, String password){
        // logs in the user and returns a new User object.
        // code
    }

    public static registerUser(String username, String password,
                               String address, String email, String dob,
                               String name)
    {
        // Creates a new User in user object.
        // code
    }
}