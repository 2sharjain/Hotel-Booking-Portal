package db;

import java.sql.*;
import java.util.Date;
import java.text.*;

public class Hotel{
    // Hotel manager class to access a Hotel's info

    public String name;
    public String location;
    public String features;  // extra ameneties that it offers eg. Wifi
    public String[] reviews;  // All reviews to the hotel
    public int id;
    public int price;
    public int rooms;

    public Hotel(int hotel_id){
        this.id = hotel_id;

        try{
            Connection conn = connectToDatabase();
            Statement getHotel = conn.createStatement();
            ResultSet r = getHotel.executeQuery(String.format("SELECT * FROM hotel WHERE hotelid='%d'", this.id));

            while(r.next()){
                name = r.getString("name");
                location = r.getString("location");
                price = r.getInt("price");
                rooms = r.getInt("rooms");
                features = r.getString("features");

            }
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }

    public Hotel(String name){
        this.name = name;

        try{
            Connection conn = connectToDatabase();
            Statement getHotel = conn.createStatement();
            ResultSet r = getHotel.executeQuery(String.format("SELECT * FROM hotel WHERE name='%s'", this.name));

            while(r.next()){
                name = r.getString("name");
                location = r.getString("location");
                price = r.getInt("price");
                rooms = r.getInt("rooms");
                features = r.getString("features");
                id = r.getInt("hotelid");

            }
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
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
            System.out.println("Connecting to Database // Hotel and Reviews");
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
            }
            return null;
        }
    }

    public static Hotel[] getHotels(String location){
        Hotel[] results = new Hotel[20];
        try{
            Connection conn = connectToDatabase();
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(String.format("SELECT * FROM hotel WHERE location='%s'",location));
            System.out.println(location);
            int i = 0;

            while(r.next()){
                System.out.println(r.getString("name"));
                results[i] = new Hotel(r.getInt("hotelid"));
                i++;
            }
            conn.close();
            return results;

        }
        catch(SQLException se){
            System.out.println("SQL ");
            se.printStackTrace();
        }
        return null;
    }

    public Booking[] getBoookings(){
        Booking[] results = new Booking[10];
        int i = 0;

        try{
            Connection conn = connectToDatabase();
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(String.format("SELECT * FROM booking WHERE hotelid='%d'", this.id));

            while(r.next()){
                results[i] = new Booking(r.getString("ref"));
                i++;
            }

            return results;
        }
        catch(SQLException se){
            se.printStackTrace();
            return null;
        }

    }

    public String[] getReviews(){
        String[] results = new String[10];
        int i =0;
        try{
            Connection conn = connectToDatabase();
        Statement s = conn.createStatement();
        ResultSet r = s.executeQuery(String.format("SELECT * FROM review WHERE hotelid='%d'", this.id));
        while(r.next()){
            results[i] = r.getString("review");
            i++;
        }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        return results;
    }

    public int available(String checkin, String checkout){
        Date cin, cout;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        try{
            cin = f.parse(checkin);
            cout = f.parse(checkout);
        }
        catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        
        int roomsAvailable = this.rooms; int  i = 0;
        Booking[] allBookings = new Booking[15];
        allBookings = getBoookings();
        for(Booking ho:allBookings){
            try{
                String lol = ho.refID;
                i++;
            }
            catch(Exception e){
                break;
            }
        }

        try{
            Connection conn = connectToDatabase();
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(String.format("SELECT * FROM booking where hotelid='%d'", this.id));

            for(int j = 0; j<i; j++){
                Date bcin = new Date(), bcout = new Date(); // Initialize

                try{
                    bcin = f.parse(allBookings[j].checkIn);
                    bcout = f.parse(allBookings[j].checkOut);
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                if(((cin.compareTo(bcin)<0 && cout.compareTo(bcout)>0)||(cin.compareTo(bcin)>=0 && cout.compareTo(bcout)<=0)|| (cin.compareTo(bcin)<0 && cout.compareTo(bcin)>0 && cout.compareTo(bcout)<=0) || (cin.compareTo(bcin)>0 && cin.compareTo(bcout)<0 && cout.compareTo(bcout)>0))&&(allBookings[j].status.compareTo("confirmed") == 0)){
                    roomsAvailable = roomsAvailable - allBookings[j].noOfRooms;
                    System.out.print("sfbhvbvkbv");
                }

            }
            return roomsAvailable;
        }
        catch(SQLException se){
            se.printStackTrace();
            return 0;
        }
    }
}