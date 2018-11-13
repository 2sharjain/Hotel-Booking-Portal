package db;
public class Hotel{

    // Hotel manager class to access a Hotel's info

    String name;
    String location;
    String amenities;  // extra ameneties that it offers eg. Wifi
    int rating;        // Average of all ratings by users
    String[] reviews;  // All reviews to the hotel
    int id;
    int price;

    public Hotel(int hotel_id){
        this.id = hotel_id;
        // gets other info by making a query to database
        // code
    }

    public static Hotel[] getHotel(String location){
        // gets all hotels for the location
        // code
        return null;
    }

    public Booking getBoooking(){
        // returns all of the bookings for the Hotel
        // code
        return null;
    }

    public Boolean available(String checkin, String checkout){
        // returns true if available else returns false
        // code
        return null;
    }

}