package gui;

import javax.swing.*;
import java.awt.Font; // Unused until now.
import java.awt.event.*;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.*;
import java.text.*;

import db.*;


public class Register extends JFrame{

    public Register(){
        super("Registration Page");
        setSize(500, 500);
        setLayout(null);
        createWidgets();
    }

    private void createWidgets(){
        
        // Title
        JLabel title = new JLabel("Register!");
        title.setBounds(220, 50, 60, 25);
        add(title);

        // Full Name
        JLabel nameLabel = new JLabel("Full Name: ");
        JTextField name = new JTextField("John Doe", 20);
        nameLabel.setBounds(50, 100, 70, 25);
        name.setBounds(120, 100, 100, 25);
        add(nameLabel);
        add(name);

        // Username
        JLabel usernameLabel = new JLabel("Username: ");
        JTextField username  = new JTextField("johndoe123", 10);
        usernameLabel.setToolTipText("Should have alphanumeric and dots and underscores only");
        usernameLabel.setBounds(280, 100, 70, 25);
        username.setBounds(350, 100, 100, 25);
        add(usernameLabel);
        add(username);

        // Password
        JLabel passwordLabel1 = new JLabel("Password: ");
        JPasswordField password1 = new JPasswordField("password", 10);
        password1.setToolTipText("Must be a strong password. Can have special characters");
        passwordLabel1.setBounds(50, 150, 70, 25);
        password1.setBounds(120, 150, 100, 25);
        add(passwordLabel1);
        add(password1); 
        // Password Confirmation
        JLabel passwordLabel2 = new JLabel("Confirm Password: ");
        JPasswordField password2 = new JPasswordField("password", 10);
        password2.setToolTipText("Re-Enter Password to confirm");
        passwordLabel2.setBounds(230, 150, 120, 25);
        password2.setBounds(350, 150, 100, 25);
        add(passwordLabel2);
        add(password2);

        // Email
        JLabel emailLabel = new JLabel("Email: ");
        JTextField email = new JTextField("johndoe@domain.com", 30);
        emailLabel.setBounds(50, 200, 70, 25);
        email.setBounds(120, 200, 140, 25);
        add(emailLabel);
        add(email);

        // Date Of Birth
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setToolTipText("YYYY/MM/DD");
        JTextField dob = new JTextField("1999/08/19");
        dobLabel.setBounds(265, 200, 75, 25);
        dob.setBounds(350, 200, 100, 25);
        add(dobLabel);
        add(dob);
        // TODO : change this to 3 comboboxes for month, date and year
        
        // Address
        JLabel addressLabel = new JLabel("Address: ");
        JTextArea address = new JTextArea("In your heart <3");
        addressLabel.setBounds(50, 250, 70, 25);
        address.setBounds(120, 250, 330, 75);
        add(addressLabel);
        add(address);

        // Submit button
        JButton register = new JButton("Register");
        register.addActionListener(
            new ActionListener(){
                // Will be called when button pressed
                // Processes the entered info
                public void actionPerformed(ActionEvent e){
                    
                    // Checks if entered info is correct and registers a new user

                    String u = username.getText().trim();
                    String em = email.getText().trim();
                    String ad = address.getText().trim();
                    String na = name.getText().trim();
                    String d = dob.getText().trim();
                    System.out.println(u);

                    String p1 = new String(password1.getPassword()); // converting to string as they are parsed as char[]
                    String p2 = new String(password2.getPassword());
                    
                    // If any field is left blank
                    if(u.length() == 0 || em.length() == 0 || ad.length() == 0 || na.length() == 0 || d.length() == 0 || p1.length() == 0 || p2.length() == 0){
                        System.out.println("Uh Oh");
                        errorHandler("Please Fill All the Fields");
                        return;
                    }

                    // Password Validator
                    if(p1.compareTo(p2) != 0){
                        errorHandler("Passwords Don't match");
                        return;
                    }

                    // Date Validator
                    String[] db = d.split("/");
                    if(db.length != 3){
                        errorHandler("Enter correct date");
                        return;
                    }
                    
                    // Username Validator
                    try{
                        Connection conn = User.connectToDatabase();
                        Statement check = conn.createStatement();
                        ResultSet r = check.executeQuery(String.format("SELECT * from user WHERE username='%s'",u));
                        if(r.next()){
                            errorHandler("Username already exists");
                            System.out.println("User Already exists");
                            return;
                        }
                    }
                    catch(SQLException se){
                        errorHandler("Something wrong happened");
                        return;
                    }

                User.registerUser(u, p1, ad, em, d, na);
                System.out.println("User Registered SuccessFully");
                userRegistered();
                return;
                }
            }
        );
        register.setBounds(200, 350, 100, 25);
        add(register);
    }

    private void errorHandler(String errorMessage){
        System.out.println(errorMessage);
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
    }

    private void userRegistered(){
        JOptionPane.showMessageDialog(this, "User Registered Successfully");
    }

}
