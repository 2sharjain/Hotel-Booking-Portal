package db;

import java.util.*; // for date
import java.text.*; // for parsing string to date

public class Booking {
   
    // Booking Manager Class to acces a booking of a user.
    // ref ID is used to search for a booking in a database.

    String ref;
    int duration;
    String checkin;
    String checkout;
    int hotel_id;
    int cost;

    public Booking(String ref){
        this.ref = ref;
        // gets other info by making a query to database
    }

    public void modify(String ...){
        // code
        // modifies the booking if possible and saves changes to database
    }

    public void delete(){
        // code
        // deletes a booking from the database
    }

    public Hotel getHotel(){
        // returns the Hotel for this Booking
    }

}