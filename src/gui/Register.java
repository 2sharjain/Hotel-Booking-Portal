package gui;

import javax.swing.*;
import java.awt.Font; // Unused until now.
import java.awt.event.*;

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
                    // TODO:
                        // ADD VALIDATORS FOR ALL FIELDS
                        // Add methods for registering a new user (requires backend)
                }
            }
        );
        register.setBounds(200, 350, 100, 25);
        add(register);
    }

    private void errorHandler(String field){
        // Handler when a field has invalid info or left empty
    }

}
