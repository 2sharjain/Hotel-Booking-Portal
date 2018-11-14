import db.User;
import db.Booking;
import db.Hotel;

import gui.Home;
import gui.ConfirmBooking;
import gui.Login;
import gui.MyBooking;
import gui.Modify;
import gui.Register;
import gui.HotelDetail;
import javax.swing.*;

/*    INSTRUCTIONS FOR RUNNING
 *
 *    Run using the following command:
 * 
 *    ```
 *    javac -cp .; db/lib/mysql-connector-java-8.0.13.jar App.java
 *    ```
 */  


public class App{
    public static void main(String[] args){
        Login home = new Login();
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
    }
}