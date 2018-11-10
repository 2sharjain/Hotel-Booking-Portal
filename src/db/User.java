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

    static Connection connectToDatabase(){
        /*
         * CLOSE THE CONNECTION OBJECT RETURNED FROM THIS METHOD AFTER USE.
         */

        String USER = "root"; // MySQl server username
        String PASS = "root"; // MySQl server password
        String DB_URL = "jdbc:mysql://localhost/user";
        Statement createUserDB = null;
        Connection conn = null;
        try{
            // JDBC Driver
            // Needs help
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to User");
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
            //finally block used to return connection object
            try{
                if(createUserDB!=null)
                createUserDB.close();
            }
            catch(SQLException se2){
                // pass
            }
            try{
                if(conn!=null){
                    return conn;
                }
                else{
                    return null;
                }
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
        
    }

    public Booking[] getBooking(){
        // returns array of all Bookings
        // code
        return null;
    }

    public Booking getBooking(String ref){
        // returns booking by the reference ID
        // code
        return null;
    }

    public static void createBooking(){
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
        return null;
    }

    public static void registerUser(String username, String password,
                               String address, String email, String dob,
                               String name)
    {
        // Creates a new User in user object.
        // code
    }
}