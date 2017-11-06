import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.swing.JOptionPane; 
  
/** 
 * @author person C - Vishal Balani 
 */
  
public class Location { 
    private static int locationID =0; 
    private String locName; 
    private int zipCode; 
    private String state; 
    private ParkingPass pass; 
    
    public static final String FILE_PATH = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\Stadiums.txt";
  
    public Location()  
    { 
    }
    
    public Location(String state, String locName){
    	//add the new stadium to the file
    	String lineToInsert = "\n".concat(state).concat("|").concat(locName);
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
    }
    public Location(String locName, int zipCode, String state, int numberSold, double price, int capacity) 
    { 
        this.locationID++; 
        this.setLocName(locName); 
        this.setZipCode(zipCode); 
        this.setState(state); 
        this.pass = new ParkingPass(numberSold, price, capacity); 
    } 
      
    public Location(String locName, int zipCode, String state) 
    { 
        this.locationID++; 
        this.setLocName(locName); 
        this.setZipCode(zipCode); 
        this.setState(state); 
    } 
    
    public boolean updateLocation(String state, String oldStadium, String newStadium){
    	String lineToInsert = "\n".concat(state).concat("|").concat(newStadium);
    	String s = null;
    	String s1 = null;
  	  	boolean fileWriteSuccess = false;
  	  try {
          // Open the file that is the first
          // command line parameter
          BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
          String sCurrentLine =br.readLine();
          FileWriter bw = new FileWriter(FILE_PATH, true);
          //Read File Line By Line
          while ((sCurrentLine = br.readLine()) != null) {
              // Print the content on the console
              System.out.println(sCurrentLine);
              StringTokenizer st = new StringTokenizer(sCurrentLine,"|");
              if(st.hasMoreTokens()){
            	  s = st.nextToken();
            	  System.out.println(s);
              }
              if(st.hasMoreTokens()){
            	  s1 = st.nextToken();
            	  System.out.println(s1);
              }
              if (s != null && s.equals(state) && s1 != null && s1.equals(oldStadium)) {
            	  //call the delete location method to delete the line
            	  deleteLocation(state, oldStadium);
            	  //then add the updated line
            	  bw.write(lineToInsert);
                  fileWriteSuccess = true;
                  System.out.println(fileWriteSuccess);
              }
              sCurrentLine = br.readLine();
              bw.close();
          }
      } catch (Exception e) {//Catch exception if any
          System.err.println("Error: " + e.getMessage());
      }
  	  return fileWriteSuccess;
    }
    public boolean deleteLocation(String state, String stadium){
    	boolean deleteSuccess = false;
    	//create a new temp file
        File tempFile = new File("temp1.txt");
    	try {
    		//set reader as the current stadium file
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            //and the writer as the new file
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            
            //trim the line that is being deleted
            String lineToRemove = "".concat(state).concat("|").concat(stadium.trim());
            String currentLine;

            while((currentLine = reader.readLine()) != null)
            {
                //trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                //if the line to remove is same as the trimmed line, then don't add it to the new temp file
                if(!trimmedLine.contains(lineToRemove))
                {
                    // if current line not start with lineToRemove then write to file
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
            writer.close();
            reader.close();
        }
        catch(Exception e)
        {
            System.out.println("Error reading to file '" + FILE_PATH + "'");

        }
    	try{
    		//do the opposite here
            BufferedReader reader = new BufferedReader(new FileReader(tempFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

            String currentLine;
            //add each line to the stadium files
            while((currentLine = reader.readLine()) != null)
            {
                    writer.write(currentLine);
                    writer.newLine();
                    deleteSuccess = true;
            }
            writer.close();
            reader.close();
    	}
    	catch(Exception e){
    		
    	}
    	if(deleteSuccess == false){
    		  JOptionPane.showMessageDialog(null, "Could not file the state or stadium you enterred, Please try again!");
    	  }
    	return deleteSuccess;
    }
    public int getLocationID() 
    { 
        return locationID; 
    } 
  
    public int getZipCode()  
    { 
        return zipCode; 
    } 
  
    public void setZipCode(int zipCode) 
    {    
        String zip = ""+zipCode; 
        while(zip.length() != 5) 
        { 
            zipCode = Integer.parseInt(JOptionPane.showInputDialog(null,"Error! Invalid zipcode. Please enter the zipcode again.")); 
            zip = "" + zipCode; 
        } 
        this.zipCode = zipCode; 
    } 
  
    public String getState()  
    { 
        return state; 
    } 
  
    public void setState(String state) 
    { 
        while(state.equals(null)) 
        { 
            state = JOptionPane.showInputDialog(null,"Error! Invalid state name. Please enter state again."); 
        } 
        this.state = state; 
    } 
  
    public String getLocName() 
    { 
        return locName; 
    } 
  
    public void setLocName(String locName)  
    { 
        while(locName.equals(null) || locName.isEmpty()) 
        { 
            locName = JOptionPane.showInputDialog(null,"Error! Invalid Location name. Please enter Location Name again."); 
        } 
        this.locName = locName; 
    } 
  
    public ParkingPass getPass() { 
        return pass; 
    } 
  
    public void setPass(ParkingPass pass) { 
        this.pass = pass; 
    } 
  
    public String toString()  
    { 
        String output = ""; 
        output += "Location ID: "+locationID+"\n"; 
        output += "ZipCode: "+zipCode+"\n"; 
        output += "State: "+state+"\n"; 
        return output; 
    } 
} 