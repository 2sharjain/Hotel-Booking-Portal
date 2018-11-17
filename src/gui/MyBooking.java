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
	setSize(550,385);
	createWidget();
	}
public void createWidget(){
	JLabel confirmed = new JLabel("*BOOKINGS*");
	confirmed.setBounds(220,30,100,25);
	add(confirmed);
	
	Booking[] allBookings = this.loggedInUser.getBookings();
	int len = 0;
    for(Booking b:allBookings){
        try{
            String lol = b.refID;
            len++;
        }
        catch(Exception e){
            break;
        }
    }

	String[] nameArray = new String[len];
	String[] roomsArray = new String[len];
	String[] fromArray = new String[len];
	String[] toArray = new String[len];
	String[] priceArray = new String[len];

	for(int i = 0; i <len; i++){
		nameArray[i] = allBookings[i].refID ;
		roomsArray[i] = "(" + allBookings[i].noOfRooms+") "+allBookings[i].getHotel().name;
		fromArray[i] = allBookings[i].checkIn;
		toArray[i] = allBookings[i].checkOut;
		priceArray[i] = "" + allBookings[i].totalCost*allBookings[i].noOfRooms +"(" + allBookings[i].status + ")";
	}

	JLabel hotelName = new JLabel("REF ID");
	hotelName.setBounds(20,75,100,20);
	add(hotelName);

	JLabel rooms = new JLabel("Rooms");
	rooms.setBounds(120,75,60,20);
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
	
	JList hotelNameList = new JList(nameArray);
	hotelNameList.setVisibleRowCount(5);
	hotelNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	hotelNameList.setFixedCellWidth(100);
	hotelNameList.setFixedCellHeight(40);

	JPanel hotelNamePanel = new JPanel();
	hotelNamePanel.setBounds(20,95,100,200);
	hotelNamePanel.add(new JScrollPane(hotelNameList));
	add(hotelNamePanel);

	JList hotelRoomsList = new JList(roomsArray);
	hotelRoomsList.setVisibleRowCount(5);
	hotelRoomsList.setSelectionModel(new NoSelectionModel());
	hotelRoomsList.setFixedCellWidth(110);
	hotelRoomsList.setFixedCellHeight(40);
	JPanel hotelRoomsPanel = new JPanel();
	hotelRoomsPanel.setBounds(120,95,110,200);
	hotelRoomsPanel.add(new JScrollPane(hotelRoomsList));
	add(hotelRoomsPanel);
	

	JList hotelFromList = new JList(fromArray);
	hotelFromList.setVisibleRowCount(5);
	hotelFromList.setSelectionModel(new NoSelectionModel());
	hotelFromList.setFixedCellWidth(70);
	hotelFromList.setFixedCellHeight(40);
	JPanel hotelFromPanel = new JPanel();
	hotelFromPanel.setBounds(230,95,70,200);
	hotelFromPanel.add(new JScrollPane(hotelFromList));
	add(hotelFromPanel);
	
	JList hotelToList = new JList(toArray);
	hotelToList.setVisibleRowCount(5);
	hotelToList.setSelectionModel(new NoSelectionModel());
	hotelToList.setFixedCellWidth(70);
	hotelToList.setFixedCellHeight(40);
	JPanel hotelToPanel = new JPanel();
	hotelToPanel.setBounds(300,95,70,200);
	hotelToPanel.add(new JScrollPane(hotelToList));
	add(hotelToPanel);
	
	JList hotelPriceList = new JList(priceArray);
	hotelPriceList.setVisibleRowCount(5);
	hotelPriceList.setSelectionModel(new NoSelectionModel());
	hotelPriceList.setFixedCellWidth(150);
	hotelPriceList.setFixedCellHeight(40);
	JPanel hotelPricePanel = new JPanel();
	hotelPricePanel.setBounds(370,95,150,200);
	hotelPricePanel.add(new JScrollPane(hotelPriceList));
	add(hotelPricePanel);
	
	JButton logout = new JButton("LOGOUT");
	logout.setBounds(430,20,90,35);
	add(logout);
	logout.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			Login window = new Login();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);

            dispose();
		}
	});

	JButton home = new JButton("HOME");
	home.setBounds(20,20,80,35);
	add(home);
	home.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent event){
		Home ff = new Home(loggedInUser);
		ff.setSize(630,200);
		ff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ff.setVisible(true);
		dispose();
	}
	});

	JButton modify = new JButton("MODIFY");
	modify.setBounds(430,300,90,30);
	add(modify);
	modify.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String ref = hotelNameList.getSelectedValue().toString();
			Modify window = new Modify(new Booking(ref));
			// window.setDefaultCloseOperation();
			window.setVisible(true);
		}
	});
	JButton delete = new JButton("CANCEL");
	delete.setBounds(320,300,90,30);
	add(delete);
	delete.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			Booking b = new Booking(hotelNameList.getSelectedValue().toString());
			b.delete();
			JOptionPane.showMessageDialog(null, "CANCELLED SUCCESSFULLY");

		}
	});

}

private static class NoSelectionModel extends DefaultListSelectionModel{
	// This is a selection mode which makes the list item unselectable

	@Override
	public void setAnchorSelectionIndex(final int anchorIndex){
		// Left Blank
	}

	@Override
	public void setLeadAnchorNotificationEnabled(final boolean flag){
		// Left Blank
	}

	@Override
	public void setLeadSelectionIndex(final int leadIndex){
		// Left Blank
	}

	@Override
	public void setSelectionInterval(final int index0, final int index1){
		// Left Blank
	}

}

}