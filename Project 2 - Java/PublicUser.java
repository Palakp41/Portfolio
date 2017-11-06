/**
 * @author person B - Michael Watkinson
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class PublicUser extends User
{
  private static int customerID;
  private String name = "";
  private String creditCardNumber = "";
  private String email = "";
  private int[] confirmationID = new int[50];
  private int confirmationNumber = 0;
  
  public static final String FILE_PATH = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\PublicUser.txt";
  
  public PublicUser(String iName,String iCreditCardNumber,String iEmail,String iUsername,String iPassword,String iStreet,String iCity,String iState,int iZipcode)
  {
	  super(iUsername,iPassword,iStreet,iCity,iState,iZipcode);  //calls the super class User constructor that takes 6 variables as input
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
			customerID = Integer.parseInt(uID)+1;
		}
    String lineToInsert = "\n".concat(customerID+"").concat("|").concat(iName).concat("|").concat(iCreditCardNumber).concat("|").concat(iEmail).concat("|").concat("\n");
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
    setName(iName);
    setCreditCardNumber(iCreditCardNumber);
    setEmail(iEmail);
  }
  
  private void setName(String iName)
  {
    while(iName.equals(null))  //validates that name is not a null value
    {
      iName = JOptionPane.showInputDialog(null,"Error! Name cannot be left empty. Please enter name again.");
    }
    name = iName;
  }
  
  private void setCreditCardNumber(String iCreditCardNumber)
  {
    while(!(iCreditCardNumber.length() == 16))  //validates that credit card number is 16 digits long
    {
      iCreditCardNumber = JOptionPane.showInputDialog(null,"Error! Invalid credit card number. Please enter the credit card number again.");
    }
    creditCardNumber = iCreditCardNumber;
  }
  
  private void setEmail(String iEmail)
  {
      while(!(iEmail.indexOf("@")>=0 && iEmail.indexOf(".")>=0))  //validates that email address is in correct format
      {
        iEmail = JOptionPane.showInputDialog(null,"Error! Invalid email address. Please enter email again.");
      }
      email = iEmail;
  }
  
  public void setConfirmationID(int iConfirmationID)
  {
    confirmationID[confirmationNumber] = iConfirmationID;  //adds a new confirmationID to this user's array
    confirmationNumber = confirmationNumber + 1;
  }
  
  public int getCustomerID()
  {
    return customerID;
  }
  
  public String getUsername()
  {
    return super.getUsername();
  }
  
  public String getPassword()
  {
    return super.getPassword();
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getAddress()
  {
    return super.getAddress();
  }
  
  public String getCreditCardNumber()
  {
    return creditCardNumber;
  }
  
  public String getEmail()
  {
    return email;
  }
  
  public int[] getConfirmationID()
  {
    return confirmationID;
  }
  
  public String toString()
  {
    String confirmationIDs = "";
    for(int i=0;i < confirmationNumber;i++)
    {
      confirmationIDs = confirmationIDs + "\n" + confirmationID[i];  //adds confirmationIDs to a string to be output
    }
    
    String output = "Customer ID: " + customerID + "\nName: " + name + "\n" + super.toString() 
      + "\nCredit Card Number: " + creditCardNumber + "\nEmail: " + email + "\nConfirmation IDs: " + confirmationIDs;
    return output;
  }
  
}