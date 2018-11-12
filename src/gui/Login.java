package gui;

import javax.swing.*;
import java.awt.event.*;
import db.*;

// include class path while compiling i.e add the command :
//    ```
//        javac -cp {{Path to db folder}}; db/lib/mysql-connector-java-8.0.13.jar Login.java
//    ```

public class Login extends JFrame{

    public Login(){
        super("Login Page");
        setSize(500, 500);
        setLayout(null);
        createWidgets();
    }

    private void createWidgets(){

        // Title
        JLabel title = new JLabel("Welcome! "); // Add some name to the title
        title.setBounds(200, 75, 100, 25);
        add(title);

        // Username
        JLabel usernameLabel = new JLabel("Username: ");
        JTextField username  = new JTextField("johndoe123", 10);
        usernameLabel.setBounds(150, 150, 90, 30);
        username.setBounds(250, 150, 100, 30);
        add(usernameLabel);
        add(username);

        // Password
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField password = new JPasswordField("password", 10);
        passwordLabel.setBounds(150, 210, 90, 30);
        password.setBounds(250, 210, 100, 30);
        add(passwordLabel);
        add(password); 

        // Submit button
        JButton login = new JButton("Log In");
        login.addActionListener(
            new ActionListener(){
                // Will be called when button pressed
                public void actionPerformed(ActionEvent e){

                    String p = new String(password.getPassword());
                    String u = username.getText();
                    User us = User.login(u, p); // returns null if username or passsword wrong
                    System.out.println(String.format("Entered password = %s username = %s",p, u));
                    if (us!= null){
                        // Do something
                        System.out.println("Logged in");
                    }
                    else{
                        errorHandler("Wrong username or password");
                    }

                }
            }
        );
        login.setBounds(180, 280, 100, 25);
        add(login);
        
        // Register 
        JLabel registerLabel = new JLabel("Don't have an account? Register here :");
        registerLabel.setBounds(100, 350, 220, 30);
        add(registerLabel);
        JButton register = new JButton("Register");
        register.addActionListener(
            new ActionListener(){
                // Will be called when button pressed
                public void actionPerformed(ActionEvent e){
                    // Register Window
                    Register window = new Register();
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    window.setVisible(true);

                    dispose();
                }
            }
        );
        register.setBounds(320, 355, 90, 20);
        add(register);
    }

    private void errorHandler(String errorMessage){
        System.out.println(errorMessage);
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
    }

}