/**
 * @author person C - Vishal Balani
 */
import java.io.FileWriter;

import javax.swing.JOptionPane;  
    
public class Address  
{  
  private String street = "";  
  private String city = "";  
  private String state = "";  
  private int zipcode = 0;  
      
  public static final String FILE_PATH = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\Address.txt";
  
  public Address(int userID, String iStreet,String iCity,String iState,int iZipcode)  
  {
	String lineToInsert = "\n".concat(userID+"").concat("|").concat(iStreet).concat("|").concat(iCity).concat("|").concat((String)iState).concat("|").concat(""+iZipcode).concat("|");
	boolean fileWriteSuccess = false;
	  try {
			FileWriter fw = new FileWriter(FILE_PATH,true);
			if(lineToInsert != null && !"".equals(lineToInsert)) {
				fw.write(lineToInsert) ;
			}
			fw.close();
			fileWriteSuccess = true;
			} catch (Exception FILENOTFOUND) {
			System.out.println("File not found!789");
		}
    setStreet(iStreet);  
    setCity(iCity);  
    setState(iState);  
    setZipcode(iZipcode);  
  }  
      
  private void setStreet(String iStreet)  
  {  
    while(iStreet.equals(null))  
    {  
      iStreet = JOptionPane.showInputDialog(null,"Error! Invalid Street address. Please enter the street again.");  
    }  
    street = iStreet;  
  }  
      
  private void setCity(String iCity)  
  {  
    while(iCity.equals(null))  
    {  
      iCity = JOptionPane.showInputDialog(null,"Error! Invalid city name. Please enter the city again.");  
    }  
    city = iCity;  
  }  
      
  private void setState(String iState)  
  {  
    while(iState.equals(null))  
    {  
      iState = JOptionPane.showInputDialog(null,"Error! Invalid state name. Please enter state again.");  
    }  
    state = iState;  
  }  
      
  private void setZipcode(int iZipcode)  
  {  
    String zip = "" + iZipcode;  
    while(zip.length() != 5)  
    {  
      iZipcode = Integer.parseInt(JOptionPane.showInputDialog(null,"Error! Invalid zipcode. Please enter the zipcode again."));  
      zip = "" + iZipcode;  
    }  
    zipcode = iZipcode;  
  }  
      
  public String toString()  
  {  
    String output = "\nAddress: " + street + ", " + city + ", " + state + " " + zipcode + ".";  
    return output;  
  }  
      
}