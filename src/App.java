import db.*;
import gui.*;
import javax.swing.*;

/*    INSTRUCTIONS FOR RUNNING
 *
 *    Run using the following command:
 * 
 *    ```
 *    javac -cp .; db/lib/mysql-connector-java-8.0.13.jar Login.java
 *    ```
 */  


public class App{
    public static void main(String[] args){
        Login home = new Login();
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
    }
}