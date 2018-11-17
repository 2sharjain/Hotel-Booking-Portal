package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.Date;
import java.text.*;
import db.*;

public class Modify extends JFrame{
	Booking booking;
public Modify(Booking b){

	super("MODIFY");
	booking=b;
	setLayout(null);
	setSize(630,200);
	createWidget();
		}
public void createWidget(){
	JLabel title = new JLabel("*MODIFY WINDOW*");
	title.setBounds(255,15,200,50);
	add(title);
	JLabel from = new JLabel("Check-in:");
	from.setBounds(115,80,70,25);
	add(from);
	JTextField fromDate = new JTextField("YYYY/MM/DD");
	fromDate.setBounds(195,80,80,25);
	add(fromDate);
	JLabel to = new JLabel("Check-out:");
	to.setBounds(315,80,70,25);
	add(to);
	JTextField toDate = new JTextField("YYYY/MM/DD");
	toDate.setBounds(395,80,80,25);
	add(toDate);
	JLabel no_of_People = new JLabel("People :");
	no_of_People.setBounds(115,120,50,25);
	add(no_of_People);
	JTextField no_of_Peoplef = new JTextField();
	no_of_Peoplef.setBounds(195,120,80,25);
	add(no_of_Peoplef);
	JLabel no_of_Rooms = new JLabel("No of Rooms:");
	no_of_Rooms.setBounds(295,120,90,25);
	add(no_of_Rooms);
	
	JTextField no_of_Roomsf = new JTextField();
	no_of_Roomsf.setBounds(395,120,80,25);
    add(no_of_Roomsf);
    
	JButton modify = new JButton("MODIFY");
	modify.setBounds(495,97,90,30);
	add(modify);
	modify.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent event){
                int price = booking.totalCost;
                Date now  = new Date();
                Date b = new Date();
                Date c = new Date();
                SimpleDateFormat fq = new SimpleDateFormat("yyyy/MM/dd");
                try{
                    b = fq.parse(booking.checkIn);
                    c = fq.parse(booking.checkOut);
                }
                catch(Exception se){
                    se.printStackTrace();
                }
                if(((b.getTime()-now.getTime())/86400000 <3) &&((b.getTime()-now.getTime())/86400000 >=0)){
                    price += 1000;
                    int dialogResult = JOptionPane.showConfirmDialog (null, "You will be charged for additional modifications, u cool? Additional 1000 rs per room will be charged","Warning", JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.NO_OPTION){
                        return;
                    }
                }
		        try{
                SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
                Date d1	= f.parse(fromDate.getText());
                Date d2	= f.parse(toDate.getText());
                if(d2.compareTo(d1)<=0){
                    JOptionPane.showMessageDialog(null,"ENTER CORRECT DATES Pls we worked very hard for this to work","ERROR",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                }
                catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"ENTER CORRECT DATE FORMAT","ERROR",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println("" + no_of_Roomsf.getText());
                System.out.println(booking.getHotel().available(fromDate.getText(),toDate.getText()));
                if(booking.getHotel().available(fromDate.getText(),toDate.getText()) + booking.noOfRooms >= Integer.parseInt(no_of_Roomsf.getText())){
                	booking.modify(fromDate.getText(), toDate.getText(), Integer.parseInt(no_of_Roomsf.getText()), price);
                	JOptionPane.showMessageDialog(null,"Modified Successfully","CONGRATS",JOptionPane.INFORMATION_MESSAGE);
                	dispose();
                }
                else{
                	JOptionPane.showMessageDialog(null,"Rooms Not available","ERROR",JOptionPane.INFORMATION_MESSAGE);
                }
}
});
	JButton bookings = new JButton("BOOKINGS");
		bookings.setBounds(20,20,80,35);
		add(bookings);
		bookings.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				MyBooking ff = new MyBooking(booking.getUser());
				ff.setSize(630,200);
				ff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ff.setVisible(true);
				dispose();
				}
		});


		
	JButton home = new JButton("HOME");
	home.setBounds(495,20,90,35);
	add(home);
	home.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			Home ff = new Home(booking.getUser());
				ff.setSize(630,200);
				ff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ff.setVisible(true);
				dispose();
		}
	});
}
}