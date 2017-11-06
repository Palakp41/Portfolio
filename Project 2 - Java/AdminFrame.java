/**
 * @author person A - Palak Patel
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class AdminFrame extends JFrame{
	
	JPanel panel01;
	JPanel panel02;
	
	JLabel stadiums;
	JLabel events;
	
	JButton addStadium;
	JButton updateStadium;
	JButton deleteStadium;
	
	JButton addEvent;
	JButton updateEvent;
	JButton deleteEvent;
	
	JButton logOut;
		
	public static final String FILE_PATH_LOCATION = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\Location.txt";
	public static final String FILE_PATH_STADIUMS = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\Stadiums.txt";
	public static final String FILE_PATH_PUBLIC = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\User.txt";
	public static final String FILE_PATH_ADMIN = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\AdminUser.txt";
	
	BorderLayout bLayout = new BorderLayout();
	
	public AdminFrame()
	{
		super("PMV Event Ticketing");
		setSize(250,180);
		setLayout(bLayout);
		setResizable(false);
		
		panel01 = new JPanel();
		panel01.setLayout(new GridLayout(4, 2, 5, 5));
		panel02 = new JPanel();
		panel02.setLayout(new GridLayout(1,1,5,5));
		stadiums = new JLabel("For Stadiums:");
		events = new JLabel("For Events:");
		
		addStadium = new JButton("Add Stadium");
		updateStadium = new JButton("Update Stadium");
		deleteStadium = new JButton("Delete Stadium");
		
		addEvent = new JButton("Add Event");
		updateEvent = new JButton("Update Event");
		deleteEvent = new JButton("Delete Event");
		
		logOut = new JButton("LogOut");
		
		panel01.add(stadiums);
		panel01.add(events);
		panel01.add(addStadium);
		panel01.add(addEvent);
		panel01.add(updateStadium);
		panel01.add(updateEvent);
		panel01.add(deleteStadium);
		panel01.add(deleteEvent);
		
		add(panel01, bLayout.NORTH);
		
		panel02.add(logOut);
		add(panel02, bLayout.SOUTH);
		
		AddStadiumButtonListener asButtonListener = new AddStadiumButtonListener();
		addStadium.addActionListener(asButtonListener);
		UpdateStadiumButtonListener usButtonListener = new UpdateStadiumButtonListener();
		updateStadium.addActionListener(usButtonListener);
		DeleteStadiumButtonListener dsButtonListener = new DeleteStadiumButtonListener();
		deleteStadium.addActionListener(dsButtonListener);
		
		AddEventButtonListener aeButtonListener = new AddEventButtonListener();
		addEvent.addActionListener(aeButtonListener);
		UpdateEventButtonListener ueButtonListener = new UpdateEventButtonListener();
		updateEvent.addActionListener(ueButtonListener);
		DeleteEventButtonListener deButtonListener = new DeleteEventButtonListener();
		deleteEvent.addActionListener(deButtonListener);
		
		LogOutButtonListener lobListener = new LogOutButtonListener();
		logOut.addActionListener(lobListener);
		
	}
	public class LogOutButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			UserFrame uFrame = new UserFrame();
			uFrame.setVisible(true);
			AdminFrame.this.setVisible(false);
		}
	}
	
	public class AddStadiumButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String state = null;
			String stadium = null;
			state = JOptionPane.showInputDialog(null, "Enter the state where the stadium is located:");
			stadium = JOptionPane.showInputDialog(null, "Enter the name of the stadium that you would like to add:");
			//add the stadium to the text file using data definition class
			
			Location loc = new Location(state, stadium);
		}
	}
	public class UpdateStadiumButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String state = null;
			state = JOptionPane.showInputDialog(null, "Enter the state where the stadium is located:");
			String oldStadium = null;
			oldStadium = JOptionPane.showInputDialog(null, "Enter the name of the stadium that you would like to update:");
			String newStadium = null;
			newStadium = JOptionPane.showInputDialog(null, "Enter the new name of the stadium:");
			//update the name of the stadium to the text file using data definition class
			
		Location loc = new Location();
		boolean success = loc.updateLocation(state, oldStadium, newStadium);
		if(success){
			JOptionPane.showMessageDialog(null, "Stadium Updated");
		}
		
		}
	}
	public class DeleteStadiumButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String state = null;
			String stadium = null;
			state = JOptionPane.showInputDialog(null, "Enter the state where the stadium is located:");
			stadium = JOptionPane.showInputDialog(null, "Enter the name of the stadium that you would like to delete:");
			//delete the stadium from the text file using the data definition class
			
			Location loc = new Location();
			boolean success = loc.deleteLocation(state, stadium);
			if(success){
				JOptionPane.showMessageDialog(null, "Stadium Deleted");
			}
			else {
				JOptionPane.showMessageDialog(null, "Stadium Not Deleted, Please Try Again!");
			}
		}
	}
	public class AddEventButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String stadium = null;
			String event = null;
			stadium = JOptionPane.showInputDialog(null, "Enter the name of the stadium:");
			event = JOptionPane.showInputDialog(null, "Enter the name of the event that you would like to add:");
			//add the even to the text file using the data definition class
			
			EventInfo eInfo = new EventInfo(stadium, event);
		}
	}
	public class UpdateEventButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String stadium = null;
			stadium = JOptionPane.showInputDialog(null, "Enter the stadium where the event is scheduled:");
			String oldEvent = null;
			oldEvent = JOptionPane.showInputDialog(null, "Enter the name of the event that you would like to update:");
			String newEvent = null;
			newEvent = JOptionPane.showInputDialog(null, "Enter the new name of the event:");
			//update the event to the text file using the data definition clss
			
			EventInfo eInfo = new EventInfo();
			boolean success = eInfo.updateEvent(stadium, oldEvent, newEvent);
			if(success){
				JOptionPane.showMessageDialog(null, "Event Updated");
			}
		}
	}
	public class DeleteEventButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String stadium = null;
			String event = null;
			stadium = JOptionPane.showInputDialog(null, "Enter the stadium where the event is scheduled:");
			event = JOptionPane.showInputDialog(null, "Enter the name of the event that you would like to delete:");
			//delete the event from the text file using the data definition class
			
			EventInfo eInfo = new EventInfo();
			boolean success = eInfo.deleteEvent(stadium, event);
			if(success){
				JOptionPane.showMessageDialog(null, "Event Deleted");
			}
			else {
				JOptionPane.showMessageDialog(null, "Event Not Deleted, Please Try Again!");
			}
		}
	}
}
