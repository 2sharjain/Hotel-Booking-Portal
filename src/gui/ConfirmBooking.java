package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*; 
import java.awt.event.*;
import db.*;

public class ConfirmBooking extends JFrame{
	Hotel hotel;
    int roomsRequired;
    String checkIn;
    String checkOut;
    User loggedInUser;
public ConfirmBooking(Hotel hotel, int roomsRequired, String checkIn, String checkOut, User user){

		super("hotel booking home");
		this.hotel = hotel;
        this.roomsRequired = roomsRequired;
        this.checkIn = checkIn; 
        this.checkOut = checkOut;
        this.loggedInUser = user;
		setLayout(null);
		setSize(400,340);
		createWidget();
}
public void createWidget(){
		JLabel confirmBooking = new JLabel("*Confirm Booking*");
		confirmBooking.setBounds(140,30,200,25);
		add(confirmBooking);
		JLabel price  = new JLabel(String.format("PRICE : %s",hotel.price));
		price.setBounds(270,75,150,25);
		add(price);
		JLabel hotelName = new JLabel(String.format("Hotel Name : %s",hotel.name));
		hotelName.setBounds(20,75,300,25);
		add(hotelName);
		JLabel to = new JLabel(String.format("TO : %s","*toDate*"));
		to.setBounds(270,110,150,25);
		add(to);
		JLabel from = new JLabel(String.format("FROM : %s",checkIn));
		from.setBounds(20,110,150,25);
		add(from);
		JLabel features = new JLabel(String.format("FEATURES : %s",hotel.features));
		features.setBounds(20,140,300,25);
		add(features);
		JLabel adhaar = new JLabel("Enter Adhaar Card Number:");
		adhaar.setBounds(20,170,180,25);
		add(adhaar);
		JTextField adhaarf = new JTextField("");
		adhaarf.setBounds(210,170,170,25);
		add(adhaarf);
		JLabel noop = new JLabel("Enter Number of People:");
		noop.setBounds(20,200,180,25);
		add(noop);
		JTextField noopf = new JTextField("");
		noopf.setBounds(210,200,170,25);
		add(noopf);

		JButton home = new JButton("HOME");
		home.setBounds(20,20,80,35);
		add(home);
		home.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Home window = new Home(loggedInUser);
				window.setSize(630,200);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.setVisible(true);
				dispose();
				}
		});


		
		JButton logout = new JButton("LOGOUT");
		logout.setBounds(290,20,90,35);
		add(logout);
		JButton confirm = new JButton("CONFIRM");
		confirm.setBounds(140,230,100,35);
		add(confirm);

		confirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(adhaarf.getText().length()!=12){
					JOptionPane.showMessageDialog(null,"ENTER CORRECT ADHAAR NUMBER","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    for(int i =0 ;i<adhaarf.getText().length(); i++){
                        try{
                            int c = Integer.parseInt(String.valueOf(adhaarf.getText().charAt(i)));
                        }
                        catch(Exception se){
                            JOptionPane.showMessageDialog(null,"ENTER CORRECT ADHAAR NUMBER","ERROR",JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                }
				System.out.println(hotel.id);
				int b = Booking.createBooking(checkIn,checkOut,loggedInUser.username,hotel.id,hotel.price,Integer.parseInt(noopf.getText()),roomsRequired);
				if(b==1){
					JOptionPane.showMessageDialog(null,"CONGRATULATIONS BOOKING SUCCESSFUL","CONGRATULATIONS",JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null,"BOOKING NOT SUCCESSFUL","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				}

		});

}
}