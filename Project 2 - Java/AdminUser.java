import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/** 
 * @author person B - Michael Watkinson 
 */
  
public class AdminUser extends User  
{  
  private static int adminID = 0;  
  private Location location;  
  
  public static final String FILE_PATH = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\AdminUser.txt";
      
  public AdminUser(String iUsername,String iPassword, Location location)  
  {  
    super(iUsername,iPassword); //calls a super class User constructor that takes two variables 
    this.setLocation(location);  
    adminID = adminID + 1;  
  }  
      
  public int getAdminID()  
  {  
    return adminID;  
  }  
      
  public Location getLocation() {  
    return location;  
}  
    
public void setLocation(Location location)   
{  
    if(!location.equals(null)) //validates that location is not a null value 
        this.location = location;  
}
//public static boolean validateUser(String iUsername, String iPassword){
//	  String userIDFound = null;
//	boolean isUserExists = false;
//		try {
//		//Read file using buffered reader.
//		BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
//		String sCurrentLine =br.readLine();
//			while (sCurrentLine != null) {
//				String userID = null;
//				String userName = null;
//				String password = null;
//				
//				//Parse each line in the text file using "|" 
//				StringTokenizer st = new StringTokenizer(sCurrentLine,"|");
//				
//				//Set User ID - First string is user ID 
//				if(st.hasMoreTokens()) {
//					userID = st.nextToken();
//					System.out.println(userID);
//				}
//				
//				//Set User Name - Second string is user name
//				if(st.hasMoreTokens()) {
//					userName = st.nextToken();
//					System.out.println(userName);
//				}
//				
//				//Set password - Third String is password
//				if(st.hasMoreTokens()) {
//					password = st.nextToken();
//					System.out.println(password);
//				}
//				
//				//If user name and password match in each line, then return true
//				if(userName != null && iUsername.equals(userName) && password != null && iPassword.equals(password)) {
//					userIDFound = userID;
//					isUserExists = true;
//					break;
//				}
//				
//				//Read next line
//				sCurrentLine = br.readLine();
//				br.close();
//			}
//		} catch (Exception FILENOTFOUND) {
//			System.out.println("File not found!");
//		}
//		return isUserExists;
//}
public String toString()  
  {  
    String output = "Admin ID: " + adminID + "\n" + super.toString(); //calls the user superclass to also display its toString() 
    return output;  
  }  
      
}