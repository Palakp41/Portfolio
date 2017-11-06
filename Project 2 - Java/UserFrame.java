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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class UserFrame extends JFrame{
	JPanel panel;
	
	User user;
	
	JRadioButton publicRButton;
	JRadioButton adminRButton;
	private ButtonGroup buttonGroup;
	
	JLabel usernameLabel;
	JLabel passwordLabel;
	JTextField usernameField;
	JTextField passwordField;
	
	JButton loginButton;
	JButton newUserButton;
	
	public static final String FILE_PATH_PUBLIC = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\User.txt";
	public static final String FILE_PATH_ADMIN = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\AdminUser.txt";
	
	public UserFrame(){
		super("PMV Event Ticketing");
		setSize(250,190);
		setResizable(false);
		
		publicRButton = new JRadioButton("Public User");
		adminRButton = new JRadioButton("Admin User");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(publicRButton);
		buttonGroup.add(adminRButton);
		
		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		usernameField = new JTextField(10);
		passwordField = new JTextField(10);
		loginButton = new JButton("Login");
		newUserButton = new JButton("New User?");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 5, 5));
		panel.setBorder(new TitledBorder(new EtchedBorder(), "User"));
		add(panel, BorderLayout.NORTH);
		
		JPanel northUpperPanel1 = new JPanel();
		northUpperPanel1.setLayout(new GridLayout(1, 2, 5, 5));
		northUpperPanel1.add(publicRButton);
		northUpperPanel1.add(adminRButton);
		panel.add(northUpperPanel1);
		
		JPanel northUpperPanel = new JPanel();
		northUpperPanel.setLayout(new GridLayout(1, 2, 5, 5));
		northUpperPanel.add(usernameLabel);
		northUpperPanel.add(passwordLabel);
		panel.add(northUpperPanel);
		
		JPanel northMiddlePanel = new JPanel();
		northMiddlePanel.setLayout(new GridLayout(1, 2, 5, 5));
		northMiddlePanel.add(usernameField);
		northMiddlePanel.add(passwordField);
		panel.add(northMiddlePanel);
		
		JPanel northLowerPanel = new JPanel();
		northLowerPanel.setLayout(new GridLayout(1, 2, 5, 5));
		northLowerPanel.add(loginButton);
		northLowerPanel.add(newUserButton);
		panel.add(northLowerPanel);
		
		LoginButtonListener loginButtonListener = new LoginButtonListener();
		loginButton.addActionListener(loginButtonListener);
		
		NewUserButtonListener newButtonListener = new NewUserButtonListener();
		newUserButton.addActionListener(newButtonListener);
	}
	
	public class LoginButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//validate whether public user or admin user
			//if public user, show
			if(publicRButton.isSelected() == true){
				//validate credentials
				if(usernameField.getText().trim().length() > 0 && passwordField.getText().trim().length() > 0){
					boolean check = User.validatePublicUser(usernameField.getText(), passwordField.getText());
					if(check == true){
						PublicUserFrame puFrame = new PublicUserFrame();
						puFrame.setVisible(true);
						UserFrame.this.setVisible(false);
					}
				}
			}
			else if(adminRButton.isSelected() == true){
				//validate credentials
				if(usernameField.getText().trim().length() > 0 && passwordField.getText().trim().length() > 0){
					boolean check = User.validateAdminUser(usernameField.getText(), passwordField.getText());
					if(check == true){
						AdminFrame auFrame = new AdminFrame();
						auFrame.setVisible(true);
						UserFrame.this.setVisible(false);
					}
				}
			}
		}//end listener method
	}//end Listener
	public class NewUserButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			NewUserFrame nuFrame = new NewUserFrame();
			nuFrame.setVisible(true);
		}
	}
}
