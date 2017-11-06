/**
 * @author person B - Michael Watkinson
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class User
{
  private String username = "";
  private String password = "";
  private static int userID = 1;
  private Address address;
  public static final String FILE_PATH = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\User.txt";
  public static final String FILE_PATH_ADMIN = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\AdminUser.txt";
  
  //for public users
  
  public User(String iUsername,String iPassword,String iStreet,String iCity,String iState,int iZipcode)
  {
	  	String uID = null;
			try {
			//Read file using buffered reader.
			BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
			String sCurrentLine =br.readLine();
				while (sCurrentLine != null) {					
					//Parse each line in the text file using "|" 
					StringTokenizer st = new StringTokenizer(sCurrentLine,"|");
					
					//Set User ID - First string is user ID 
					if(st.hasMoreTokens()) {
						uID = st.nextToken();
						System.out.println(uID);
					}
					sCurrentLine = br.readLine();
					br.close();
				}
			} catch (Exception FILENOTFOUND) {
			}
			if(uID!=null){
				userID = Integer.parseInt(uID) + 1;
			}
	  String lineToInsert = "".concat(userID+"").concat("|").concat(iUsername).concat("|").concat(iPassword).concat("|").concat("\n");
	  boolean fileWriteSuccess = false;
	  try {
		  	FileWriter bw = new FileWriter(FILE_PATH, true);
			if(lineToInsert != null && !"".equals(lineToInsert)) {
				bw.write(lineToInsert);
			}
			bw.close();
			fileWriteSuccess = true;
			} catch (Exception FILENOTFOUND) {
			System.out.println("File not found!");
		}
    //setUsername(iUsername);
    //setPassword(iPassword);
    address = new Address(userID, iStreet,iCity,iState,iZipcode);
  }
  
  //for admin users
  public User(String iUsername,String iPassword)
  {
	  String lineToInsert = "\n".concat(userID+"").concat("|").concat(iUsername).concat("|").concat(iPassword);
	  boolean fileWriteSuccess = false;
	  try {
			FileWriter fw = new FileWriter(FILE_PATH,true);
			if(lineToInsert != null && !"".equals(lineToInsert)) {
				fw.write(lineToInsert) ;
			}
			fw.close();
			fileWriteSuccess = true;
			} catch (Exception FILENOTFOUND) {
			System.out.println("File not found!");
		}
    //setUsername(iUsername);
    //setPassword(iPassword);
    userID = userID + 1;
  }
  
  private void setUsername(String iUsername)
  {
    while(iUsername.equals(null))
    {
      iUsername = JOptionPane.showInputDialog(null,"Error! Invalid username. Please enter username again.");
    }
    username = iUsername;
  }
  
  private void setPassword(String iPassword)
  {
    while(iPassword.equals(null))
    {
      iPassword = JOptionPane.showInputDialog(null,"Error! Invalid password. Please enter password again.");
    }
    password = iPassword;
  }
  
  public String getUsername()
  {
    return username;
  }
  
  public String getPassword()
  {
    return password;
  }
  
  public String getAddress()
  {
    return address.toString();
  }
  public static boolean validatePublicUser(String iUsername, String iPassword){
	  String userIDFound = null;
  	boolean isUserExists = false;
		try {
		//Read file using buffered reader.
		BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
		String sCurrentLine =br.readLine();
			while (sCurrentLine != null) {
				String uID = null;
				String uname = null;
				String upassword = null;
				
				//Parse each line in the text file using "|" 
				StringTokenizer st = new StringTokenizer(sCurrentLine,"|");
				
				//Set User ID - First string is user ID 
				if(st.hasMoreTokens()) {
					uID = st.nextToken();
					System.out.println(uID);
				}
				
				//Set User Name - Second string is user name
				if(st.hasMoreTokens()) {
					uname = st.nextToken();
					System.out.println(uname);
				}
				
				//Set password - Third String is password
				if(st.hasMoreTokens()) {
					upassword = st.nextToken();
					System.out.println(upassword);
				}
				
				//If user name and password match in each line, then return true
				if(uname != null && iUsername.equals(uname) && upassword != null && iPassword.equals(upassword)) {
					userIDFound = uID;
					isUserExists = true;
					break;
				}
				
				//Read next line
				sCurrentLine = br.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
		return isUserExists;
  }
		public static boolean validateAdminUser(String iUsername, String iPassword){
			  String userIDFound = null;
		  	boolean isUserExists = false;
				try {
				//Read file using buffered reader.
				BufferedReader br = new BufferedReader(new FileReader(FILE_PATH_ADMIN));
				String sCurrentLine =br.readLine();
					while (sCurrentLine != null) {
						String uID = null;
						String uname = null;
						String upassword = null;
						
						//Parse each line in the text file using "|" 
						StringTokenizer st = new StringTokenizer(sCurrentLine,"|");
						
						//Set User ID - First string is user ID 
						if(st.hasMoreTokens()) {
							uID = st.nextToken();
							System.out.println(uID);
						}
						
						//Set User Name - Second string is user name
						if(st.hasMoreTokens()) {
							uname = st.nextToken();
							System.out.println(uname);
						}
						
						//Set password - Third String is password
						if(st.hasMoreTokens()) {
							upassword = st.nextToken();
							System.out.println(upassword);
						}
						
						//If user name and password match in each line, then return true
						if(uname != null && iUsername.equals(uname) && upassword != null && iPassword.equals(upassword)) {
							userIDFound = uID;
							isUserExists = true;
							break;
						}
						
						//Read next line
						sCurrentLine = br.readLine();
					}
				} catch (Exception FILENOTFOUND) {
					System.out.println("File not found!");
				}
		return isUserExists;
  }
  public String toString()
  {
    String output = "Username: " + username + "\nPassword: " + password + "\n" + getAddress();
    return output;
  }
  
}