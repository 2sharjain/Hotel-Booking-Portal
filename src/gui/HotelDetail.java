package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*; 
import java.awt.event.*;
import db.*;

public class HotelDetail extends JFrame{
    Hotel hotel;
    int roomsRequired;
    String checkIn;
    String checkOut;
    User loggedInUser;

    public HotelDetail(Hotel hotel, int roomsRequired, String checkIn, String checkOut, User user){
        super("hotel booking home");
        this.hotel = hotel;
        this.roomsRequired = roomsRequired;
        this.checkIn = checkIn; 
        this.checkOut = checkOut;
        this.loggedInUser = user;
        setLayout(null);
        setSize(600,450);
        createWidgets();
    }

    public void createWidgets(){
        // String[] reviews = this.hotel.getReviews();
        String[] x = this.hotel.getReviews(); 
        JLabel hotelName = new JLabel(this.hotel.name);
        hotelName.setBounds(250,30,150,25);
        add(hotelName);
            
        JLabel rating = new JLabel("Rooms : "+this.hotel.rooms);
        rating.setBounds(400,75,100,25);
        add(rating);
            
        JLabel location = new JLabel(this.hotel.location);
        location.setBounds(100,75,300,25);
        add(location);
            
        JLabel price = new JLabel(String.format("Price: Rs. %d",this.hotel.price));
        price.setBounds(400,110,100,25);
        add(price);
            
        JLabel features = new JLabel("Features: " + this.hotel.features);
        features.setBounds(100,110,300,25);
         add(features);
            
        JLabel review = new JLabel("REVIEWS:");
        review.setBounds(100,175,150,20);
        add(review);

        JList reviewList = new JList(x);
        reviewList.setVisibleRowCount(5);
        reviewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reviewList.setFixedCellWidth(400);
        reviewList.setFixedCellHeight(40);
        JPanel reviewPanel = new JPanel();
        reviewPanel.setBounds(100,190,420,200);
        reviewPanel.add(new JScrollPane(reviewList));
        add(reviewPanel);

        JButton logout = new JButton("LOGOUT");
        logout.setBounds(500,20,90,35);
        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Login window = new Login();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setVisible(true);

                dispose();
            }
        });
        add(logout);
        System.out.println(this.hotel.available(this.checkIn, this.checkOut));
        if(this.hotel.available(this.checkIn, this.checkOut) >= this.roomsRequired){
            JButton book = new JButton("BOOK");
            book.setBounds(250,145,120,30);
            book.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    ConfirmBooking window = new ConfirmBooking(hotel, roomsRequired, checkIn, checkOut, loggedInUser);
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    window.setVisible(true);

                    dispose();
                }
            });
            add(book);
        }
        else{
            JButton wait = new JButton("Add to waiting list");
            wait.setBounds(250,145,170,30);
            add(wait);
            wait.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Booking.addToWaiting(checkIn, checkOut, loggedInUser.username,hotel.id,hotel.price, 5, roomsRequired);
                    JOptionPane.showMessageDialog(null,"Added to waiting list","CONGRATULATIONS",JOptionPane.PLAIN_MESSAGE);
                }
            });
        }


        JButton home = new JButton("HOME");
        home.setBounds(20,20,80,35);
        add(home);
        home.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                Home window = new Home(loggedInUser);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setVisible(true);

                dispose();
            }
        });
    }
}
