package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*; 
import java.awt.event.*;
import java.text.*; 
import java.util.*; 
import java.lang.*;

import db.*;

public class Home extends JFrame{
    User loggedInUser;

    public Home(User loggedInUser){
        super("User booking home");

        this.loggedInUser = loggedInUser;
        setLayout(null);
        setSize(630,200);
        createWidgets();
    }

    public void createWidgets(){

        // Title
        JPanel listpanel = new JPanel();
        JLabel title = new JLabel(String.format("Welcome, %s", this.loggedInUser.name.split(" ")[0]));
        title.setBounds(255,15,200,50);
        add(title);
        
        // Headings for the list
        JLabel hotelName = new JLabel("HOTEL NAME");
        hotelName.setBounds(115,160,150,20);
        JLabel features = new JLabel("FEATURES");
        features.setBounds(305,160,80,20);
        JLabel price = new JLabel("PRICE");
        price.setBounds(465,160,60,20);
        
        // Search Form
        JLabel from = new JLabel("Check-in:");
        from.setBounds(115,80,70,25);
        add(from);
        JTextField fromDate = new JTextField("1234/56/78");
        fromDate.setBounds(195,80,80,25);
        add(fromDate);

        JLabel to = new JLabel("Check-out:");
        to.setBounds(315,80,70,25);
        add(to);
        JTextField toDate = new JTextField("1234/56/78");
        toDate.setBounds(395,80,80,25);
        add(toDate);

        JLabel location = new JLabel("Location");
        location.setBounds(115,120,50,25);
        add(location);
        JTextField locationf = new JTextField();
        locationf.setBounds(195,120,80,25);
        add(locationf);

        JLabel no_of_Rooms = new JLabel("No of Rooms:");
        no_of_Rooms.setBounds(295,120,90,25);
        add(no_of_Rooms);
        JTextField no_of_Roomsf = new JTextField();
        no_of_Roomsf.setBounds(395,120,80,25);
        add(no_of_Roomsf);

        JButton logout = new JButton("Logout");
        logout.setBounds(495,20,90,35);
        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Login window = new Login();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setVisible(true);

                dispose();
            }
        });
        add(logout);

        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){

            }
        });
        
        JButton myBookings = new JButton("BOOKINGS");
        myBookings.setBounds(5,20,110,35);
        add(myBookings);
        myBookings.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                dispose();
                // myBookings myBookingsObject = new myBookings();
                // myBookingsObject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // myBookingsObject.setSize(450,370);
                // myBookingsObject.setVisible(true);
            }
        });

        // Search Results List
        
        // Features
        JList hotelFeaturesList = new JList();
        hotelFeaturesList.setVisibleRowCount(5);
        hotelFeaturesList.setSelectionModel(new NoSelectionModel());
        hotelFeaturesList.setFixedCellWidth(160);
        hotelFeaturesList.setFixedCellHeight(40);
        JPanel hotelFeaturesPanel = new JPanel();
        hotelFeaturesPanel.setBounds(305,180,160,200);
        hotelFeaturesPanel.add(hotelFeaturesList);
        
        // Price
        JList hotelPriceList = new JList();
        hotelPriceList.setVisibleRowCount(5);
        hotelPriceList.setSelectionModel(new NoSelectionModel());
        hotelPriceList.setFixedCellWidth(120);
        hotelPriceList.setFixedCellHeight(40);
        JPanel hotelPricePanel = new JPanel();
        hotelPricePanel.setBounds(465,180,120,200);
        hotelPricePanel.add(hotelPriceList);

        JList hotelNameList = new JList();
        hotelNameList.setVisibleRowCount(5);
        hotelNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hotelNameList.setFixedCellWidth(190);
        hotelNameList.setFixedCellHeight(40);

        JPanel hotelNamePanel = new JPanel();
        hotelNamePanel.setBounds(115,180,190,200);
        hotelNamePanel.add(hotelNameList);
        hotelNameList.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event){
                hotelFeaturesList.setSelectedIndex(hotelNameList.getSelectedIndex());
                hotelPriceList.setSelectedIndex(hotelNameList.getSelectedIndex()); 
            }

        });
        hotelNameList.addMouseListener(new MouseAdapter(){
            // Double Click Event opens Hotel DetailView
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList hotelNameToOpen = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    // Gets Name of hotel
                    String hotelName = hotelNameToOpen.getSelectedValue().toString();
                    HotelDetail window = new HotelDetail(new Hotel("star villa"), Integer.parseInt(no_of_Roomsf.getText()), fromDate.getText(), toDate.getText(), loggedInUser);
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    window.setVisible(true);

                    dispose();
                }
            }
        });

        JButton search = new JButton("SEARCH");
        search.setBounds(495,97,90,30);
        add(search);
        search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                //set the array values of the lists here
                try{
                SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
                Date d1	= f.parse(fromDate.getText());
                Date d2	= f.parse(toDate.getText());
                }
                catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"ENTER CORRECT DATE FORMAT","ERROR",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Hotel[] hotels = Hotel.getHotels(locationf.getText());
                int len = 0;
                for(Hotel ho:hotels){
                    try{
                        String lol = ho.name;
                        len++;
                    }
                    catch(Exception e){
                        break;
                    }
                }

                String[] hotelNameArray = new String[len];
                String[] featuresArray = new String[len];
                String[] hotelpriceArray = new String[len];
                for(int i =0; i<len; i++){
                    
                    hotelNameArray[i] = hotels[i].name;
                    featuresArray[i] = hotels[i].features;
                    hotelpriceArray[i] = "" + hotels[i].price;
                }

                hotelNameList.setListData(hotelNameArray);
                hotelFeaturesList.setListData(featuresArray);
                hotelPriceList.setListData(hotelpriceArray);
                if(len>0){
                    setSize(630,450);
                    add(hotelName);
                    add(features);
                    add(price);
                    add(hotelNamePanel);
                    add(hotelFeaturesPanel);
                    add(hotelPricePanel);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No Results Found", "ALERT", JOptionPane.INFORMATION_MESSAGE);
                }

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
