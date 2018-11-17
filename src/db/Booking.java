package db;

import java.util.*; // for date
import java.text.*; // for parsing string to date
import java.sql.*;
import java.lang.*;

public class Booking{
   
    // Booking Manager Class to access a booking of a user.
    // ref ID is used to search for a booking in a database.

    public String refID;
    public String checkIn;
    public String checkOut;
    public String username;
    public int hotelID;
    public int totalCost;
    public int noOfRooms;
    public int noOfPeople;
    public String status;

    public Booking(String refID){
        this.refID = refID;

        Connection conn  = connectToDatabase();
        if (conn!=null){
            try{
                Statement getData = conn.createStatement();
                ResultSet r = getData.executeQuery(String.format("SELECT * FROM BOOKING WHERE ref='%s'", refID));
                while(r.next()){
                    refID = r.getString("ref");
                    checkIn = r.getString("checkin");
                    checkOut = r.getString("checkout");
                    hotelID = r.getInt("hotelid");
                    totalCost = r.getInt("cost");
                    noOfRooms = r.getInt("rooms");
                    noOfPeople = r.getInt("people");
                    username = r.getString("username");
                    status = r.getString("status");

                }
            conn.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static Connection connectToDatabase(){
        /*
         * CLOSE THE CONNECTION OBJECT RETURNED FROM THIS METHOD AFTER USE.
         * NAME OF THE DATABASE FILE IS USER. NAME OF BOOKINGS TABLE IS 'booking'
         */

        String USER = "root"; // MySQl server username
        String PASS = "root"; // MySQl server password
        String DB_URL = "jdbc:mysql://localhost/user";  
        Connection conn = null;
        try{
            // JDBC Driver
            // Needs help
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to Database // Booking");
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

    public static int createBooking(String cin, String cout, String username, int hotel, int cost, int people, int rooms){

        Random rndm = new Random();
        String ref = String.format("%d", Math.abs(rndm.nextInt()));
        try{
            Connection addBooking = connectToDatabase();
            String stmt = String.format("INSERT INTO BOOKING(ref, checkin, checkout, hotelid, username, cost, people, rooms)VALUES('%s', '%s', '%s', %d, '%s', %d, %d, %d)", ref, cin, cout, hotel, username, cost, people, rooms);
            Statement s = addBooking.createStatement();
            s.execute(stmt);
            addBooking.close();
            System.out.println("YAY CCREATED BOOKING");
            return 1;
        }
        catch(SQLException se){
            se.printStackTrace();
            return 0;
        }

    }

    public void modify(String checkin, String checkout, int rooms, int price){
        try{
            Connection conn = connectToDatabase();
            String modify = String.format("UPDATE booking SET checkin='%s', checkout='%s', rooms='%d', cost='%d' WHERE ref='%s'", checkin, checkout,rooms, price, this.refID);
            Statement s = conn.createStatement();
            s.execute(modify);
            
            // Update for this object as well
            this.checkIn = checkin;
            this.checkOut = checkout;
            int i =0;
            Booking[] all = new Booking[10];
            Statement w = conn.createStatement();
            String getWait = String.format("SELECT * FROM booking where status='waiting'");
            ResultSet rs = w.executeQuery(getWait);
            while(rs.next()){
                all[i] = new Booking(rs.getString("ref"));
                i++;
            }

            for(int j =0; j<i; j++){
                if(all[j].getHotel().available(all[j].checkIn, all[j].checkOut) >= all[j].noOfRooms){
                    String modify1 = String.format("UPDATE booking SET status='confirmed' WHERE ref='%s'", all[j].refID);
                    Statement s1 = conn.createStatement();
                    s1.execute(modify1);
                }
            }
        }
        catch(SQLException se){
            se.printStackTrace();
            return;    
        }

    }

    public User getUser(){
        return new User(username);
    }

    public void delete(){
        try{
            Connection conn = connectToDatabase();
            Statement s = conn.createStatement();
            s.execute(String.format("DELETE FROM booking WHERE (ref = '%s')", this.refID));
                int i =0;
                Booking[] all = new Booking[10];
                Statement w = conn.createStatement();
                String getWait = "SELECT * FROM booking where status='waiting'";
                ResultSet rs = w.executeQuery(getWait);
                while(rs.next()){
                    all[i] = new Booking(rs.getString("ref"));
                    i++;
                }
    
                for(int j =0; j<i; j++){
                    if(all[j].getHotel().available(all[j].checkIn, all[j].checkOut) >= all[j].noOfRooms){
                        String modify1 = String.format("UPDATE booking SET status='confirmed' WHERE ref='%s'", all[j].refID);
                        Statement s1 = conn.createStatement();
                        s1.execute(modify1);
                    }
                }
        }
        catch(SQLException se){
            se.printStackTrace();
            return;
        }
    }

    public static void addToWaiting(String cin, String cout, String username, int hotel, int cost, int people, int rooms){
        Random rndm = new Random();
        String ref = String.format("%d", Math.abs(rndm.nextInt()));
        try{
            Connection addBooking = connectToDatabase();
            String stmt = String.format("INSERT INTO BOOKING(ref, checkin, checkout, hotelid, username, cost, people, rooms, status)VALUES('%s', '%s', '%s', %d, '%s', %d, %d, %d, '%s')", ref, cin, cout, hotel, username, cost, people, rooms, "waiting");
            Statement s = addBooking.createStatement();
            s.execute(stmt);
            addBooking.close();
            System.out.println("YAY CCREATED BOOKING");
            return;
        }
        catch(SQLException se){
            se.printStackTrace();
            return;
        }
    }

    public Hotel getHotel(){
        try{
            Connection conn = connectToDatabase();
            Statement getHotel = conn.createStatement();
            ResultSet r = getHotel.executeQuery(String.format("SELECT * FROM booking WHERE ref='%s'", this.refID));
            
            while(r.next()){
                System.out.println(r.getString("username"));
                return new Hotel(r.getInt("hotelid"));
            }
            return null;
        }
        catch(SQLException se){
            se.printStackTrace();
            return null;
        }
    }

}