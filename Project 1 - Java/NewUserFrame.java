import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class NewUserFrame extends JFrame{
	JPanel panel;
	
	String[] states = {"Alabama", "Arkansas", "California", "Conneticut", "Delaware", "Florida", "Georgia", "Iliinois", "Indiana", "Iowa", "Kansas", "Kentucky",
			"Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada",	"New Hampshire",
			"New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", 
			"South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
	
	JLabel nameLabel;
	JLabel newUserLabel;
	JLabel newPassLabel;
	JLabel addressLabel;
	JLabel phoneLabel;
	JTextField nameField;
	JTextField newUserField;
	JTextField newPassField;
	JTextArea addressArea;
	JTextField phoneField;
	
	JButton submitButton;
	JButton resetButton;
	
	public NewUserFrame(){
		super("Kiosk System");
		setSize(400,300);
		setResizable(false);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2, 5, 5));
		nameLabel = new JLabel("Name:");
		newUserLabel = new JLabel("Username:");
		newPassLabel = new JLabel("Password:");
		addressLabel = new JLabel("Address:");
		phoneLabel = new JLabel("Phone Number:");
		nameField = new JTextField(10);
		newUserField = new JTextField(10);
		newPassField = new JTextField(10);
		addressArea = new JTextArea(5, 5);
		addressArea.setLineWrap(true);
		addressArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(addressArea);
		phoneField = new JTextField(10);
		
		submitButton = new JButton("Submit");
		resetButton = new JButton("Reset");
		
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(newUserLabel);
		panel.add(newUserField);
		panel.add(newPassLabel);
		panel.add(newPassField);
		panel.add(addressLabel);
		panel.add(addressArea);
		panel.add(phoneLabel);
		panel.add(phoneField);
		
		panel.add(submitButton);
		panel.add(resetButton);
		
		add(panel);
		
		SubmitButtonListener submitListener = new SubmitButtonListener();
		submitButton.addActionListener(submitListener);
		
		ResetButtonListener resetListener = new ResetButtonListener();
		resetButton.addActionListener(resetListener);
	}
	
	private class SubmitButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			UserDB user = new UserDB();
			PublicUserDB publicUser = new PublicUserDB();
			if(nameField.getText().trim().length() > 0 && newUserField.getText().trim().length() > 0 && newPassField.getText().trim().length() > 0
					&& addressArea.getText().trim().length() > 0 && phoneField.getText().trim().length() > 0){
				PublicUser pu = new PublicUser(newUserField.getText(), newPassField.getText(), nameField.getText(), addressArea.getText(), Float.parseFloat(phoneField.getText()));
				user.addUser(newUserField.getText(), newPassField.getText());
				publicUser.addPublicUser(user.getID(newUserField.getText(), newPassField.getText()), nameField.getText(), addressArea.getText(), Float.parseFloat(phoneField.getText()));
				PublicUserFrame puFrame = new PublicUserFrame();
				puFrame.setVisible(true);
				NewUserFrame.this.setVisible(false);
			}
		}
	}
	private class ResetButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			nameField.setText("");
			newUserField.setText("");
			newPassField.setText("");
			addressArea.setText("");
			phoneField.setText("");
		}
	}
}
