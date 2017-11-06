/**
 * @author person A - Palak Patel
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	JLabel emailLabel;
	JLabel creditCardLabel;
	JLabel streetLabel;
	JLabel cityLabel;
	JLabel stateLabel;
	JLabel zipCodeLabel;
	JTextField nameField;
	JTextField newUserField;
	JTextField newPassField;
	JTextField emailField;
	JTextField creditCardField;
	JTextField streetField;
	JTextField cityField;
	JComboBox stateComboBox; //maybe combobox
	JTextField zipCodeField;
	
	JButton submitButton;
	JButton resetButton;
	
	public NewUserFrame(){
		super("New User Frame");
		setSize(400,300);
		setResizable(false);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(10, 2, 5, 5));
		nameLabel = new JLabel("Name:");
		newUserLabel = new JLabel("Username:");
		newPassLabel = new JLabel("Password:");
		emailLabel = new JLabel("Email:");
		creditCardLabel = new JLabel("CreditCard #:");
		streetLabel = new JLabel("Street:");
		cityLabel = new JLabel("City:");
		stateLabel = new JLabel("State");
		zipCodeLabel = new JLabel("Zip Code:");
		nameField = new JTextField(10);
		newUserField = new JTextField(10);
		newPassField = new JTextField(10);
		emailField = new JTextField(20);
		creditCardField = new JTextField(20);
		streetField = new JTextField(20);
		cityField = new JTextField(10);
		stateComboBox = new JComboBox();
		for(int i=0; i<states.length; i++){
			stateComboBox.addItem(states[i]);
		}
		zipCodeField = new JTextField(5);
		
		submitButton = new JButton("Submit");
		resetButton = new JButton("Reset");
		
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(newUserLabel);
		panel.add(newUserField);
		panel.add(newPassLabel);
		panel.add(newPassField);
		panel.add(emailLabel);
		panel.add(emailField);
		panel.add(creditCardLabel);
		panel.add(creditCardField);
		panel.add(streetLabel);
		panel.add(streetField);
		panel.add(cityLabel);
		panel.add(cityField);
		panel.add(stateLabel);
		panel.add(stateComboBox);
		panel.add(zipCodeLabel);
		panel.add(zipCodeField);
		
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
			if(nameField.getText().trim().length() > 0 && newUserField.getText().trim().length() > 0 && newPassField.getText().trim().length() > 0 
					&& emailField.getText().trim().length() > 0 && creditCardField.getText().trim().length() > 0 && streetField.getText().trim().length() > 0
					&& cityField.getText().trim().length() > 0 && stateComboBox.getSelectedIndex() >= 0 && zipCodeField.getText().trim().length() > 0){
				PublicUser puser = new PublicUser(nameField.getText(), creditCardField.getText(), emailField.getText(), newUserField.getText(),
						newPassField.getText(), streetField.getText(), cityField.getText(), (String)stateComboBox.getSelectedItem(), Integer.parseInt(zipCodeField.getText()));
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
			emailField.setText("");
			creditCardField.setText("");
			streetField.setText("");
			cityField.setText("");
			stateComboBox.setSelectedIndex(0);
			zipCodeField.setText("");
		}
	}
}
