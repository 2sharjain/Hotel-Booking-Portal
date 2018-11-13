package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*; 
import java.awt.event.*;

import db.*;

public class MyBooking extends JFrame{
	User loggedInUser;
public MyBooking(User user){
	super("My Bookings");
	this.loggedInUser = user;
	setLayout(null);
	setSize(450,370);
	createWidget();
	}
public void createWidget(){
	JLabel confirmed = new JLabel("*BOOKINGS*");
	confirmed.setBounds(170,30,100,25);
	add(confirmed);

	

	JLabel hotelName = new JLabel("HOTEL NAME");
	hotelName.setBounds(20,75,150,20);
	add(hotelName);

	JLabel rooms = new JLabel("Rooms");
	rooms.setBounds(170,75,60,20);
	add(rooms);
	JLabel from = new JLabel("FROM");
	from.setBounds(230,75,60,20);
	add(from);
	JLabel to = new JLabel("to");
	to.setBounds(300,75,60,20);
	add(to);
	JLabel price = new JLabel("Price");
	price.setBounds(370,75,60,20);
	add(price);
	
	JList hotelNameList = new JList(x);
	hotelNameList.setVisibleRowCount(5);
	hotelNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	hotelNameList.setFixedCellWidth(150);
	hotelNameList.setFixedCellHeight(40);
	JPanel hotelNamePanel = new JPanel();
	hotelNamePanel.setBounds(20,95,150,200);
	hotelNamePanel.add(new JScrollPane(hotelNameList));
	add(hotelNamePanel);

	JList hotelRoomsList = new JList(x);
	hotelRoomsList.setVisibleRowCount(5);
	hotelRoomsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	hotelRoomsList.setFixedCellWidth(60);
	hotelRoomsList.setFixedCellHeight(40);
	JPanel hotelRoomsPanel = new JPanel();
	hotelRoomsPanel.setBounds(170,95,60,200);
	hotelRoomsPanel.add(new JScrollPane(hotelRoomsList));
	add(hotelRoomsPanel);
	
	JList hotelFromList = new JList(x);
	hotelFromList.setVisibleRowCount(5);
	hotelFromList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	hotelFromList.setFixedCellWidth(70);
	hotelFromList.setFixedCellHeight(40);
	JPanel hotelFromPanel = new JPanel();
	hotelFromPanel.setBounds(230,95,70,200);
	hotelFromPanel.add(new JScrollPane(hotelFromList));
	add(hotelFromPanel);
	
	JList hotelToList = new JList(x);
	hotelToList.setVisibleRowCount(5);
	hotelToList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	hotelToList.setFixedCellWidth(70);
	hotelToList.setFixedCellHeight(40);
	JPanel hotelToPanel = new JPanel();
	hotelToPanel.setBounds(300,95,70,200);
	hotelToPanel.add(new JScrollPane(hotelToList));
	add(hotelToPanel);
	
	JList hotelPriceList = new JList(x);
	hotelPriceList.setVisibleRowCount(5);
	hotelPriceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	hotelPriceList.setFixedCellWidth(60);
	hotelPriceList.setFixedCellHeight(40);
	JPanel hotelPricePanel = new JPanel();
	hotelPricePanel.setBounds(370,95,60,200);
	hotelPricePanel.add(new JScrollPane(hotelPriceList));
	add(hotelPricePanel);
	
	JButton logout = new JButton("LOGOUT");
	logout.setBounds(340,20,90,35);
	add(logout);
	logout.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent event){

	}
	});

	JButton home = new JButton("HOME");
	home.setBounds(20,20,80,35);
	add(home);
	home.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent event){
		searchResult ff = new searchResult();
		ff.setSize(630,200);
		ff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ff.setVisible(true);
		dispose();
	}
	});

	JButton modify = new JButton("MODIFY");
	modify.setBounds(345,300,90,30);
	add(modify);
	JButton delete = new JButton("DELETE");
	delete.setBounds(235,300,90,30);
	add(delete);
}
}