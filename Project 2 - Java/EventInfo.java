import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

import javax.swing.JOptionPane; 
  
/** 
 * @author person C - Vishal Balani 
 */
  
public class EventInfo extends EventSeries { 
      
    private int eventID; 
    private String eventType; 
    private double price; 
    private int availSeats = 10000; 
    private Location location; 
    
    public static final String FILE_PATH = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\EventInfo.txt";
  
    public EventInfo() 
    { 
    } 
    
    public EventInfo(String stadium, String eventName){
    	//add the new stadium to the file
    	String lineToInsert = "\n".concat(stadium).concat("|").concat(eventName);
  	  	boolean fileWriteSuccess = false;
  	  	try {
  		  	FileWriter bw = new FileWriter(FILE_PATH, true);
  			if(lineToInsert != null && !"".equals(lineToInsert)) {
  				bw.write(lineToInsert);
  			}
  			bw.close();
  			fileWriteSuccess = true;
  		} catch (Exception e) {
  			System.out.println("Error: " + e.getMessage());
  		}
    }
    public EventInfo(int eventID, String locName, int zipCode, String state, String eventType, double price, int availSeats,int numberSold, double passPrice, int capacity) 
    { 
        this.eventID++; 
        this.location = new Location(locName, zipCode, state,numberSold, price, capacity); 
        this.setEventType(eventType); 
        this.setPrice(price); 
        this.setAvailSeats(availSeats); 
    } 
    
    public boolean updateEvent(String stadium, String oldEvent, String newEvent){
    	String lineToInsert = "\n".concat(stadium).concat("|").concat(newEvent);
    	String s = null;
    	String event = null;
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
            	  event = st.nextToken();
            	  System.out.println(event);
              }
              if (s != null && s.equals(stadium) && event != null && event.equals(oldEvent)) {
            	  //call the delete location method to delete the line
            	  deleteEvent(stadium, oldEvent);
            	  //then add the updated line
            	  bw.write(lineToInsert);
                  fileWriteSuccess = true;
                  bw.close();
                  System.out.println(fileWriteSuccess);
              }
              sCurrentLine = br.readLine();
          }
      } catch (Exception e) {//Catch exception if any
          System.err.println("Error: " + e.getMessage());
      }
  	
  	  	return fileWriteSuccess;
    }
    public boolean deleteEvent(String stadium, String event){
    	boolean deleteSuccess = false;
    	//create a new temp file
        File tempFile = new File("temp2.txt");
    	try {
    		//set reader as the current stadium file
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            //and the writer as the new file
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            
            //trim the line that is being deleted
            String lineToRemove = "".concat(stadium).concat("|").concat(event.trim());
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
    		  JOptionPane.showMessageDialog(null, "Could not file the stadium or event you enterred, Please try again!");
    	  }
    	return deleteSuccess;
    }
    public int getEventID()  
    { 
        return eventID; 
    } 
  
    /*    
     *  public void setLocation(Location location) 
     *  { 
            this.location = location; 
        } 
    */
    
    public String getEventType()  
    { 
        return eventType; 
    } 
  
    public void setEventType(String eventType)  
    { 
        while(eventType.equals(null)) 
        { 
            eventType = JOptionPane.showInputDialog(null,"Error! Invalid Event Type. Please enter the Event Type again."); 
        } 
        this.eventType = eventType;         
    } 
  
    public double getPrice()  
    { 
        return price; 
    } 
  
    public void setPrice(double price) 
    { 
        while(Double.isNaN(price)|| price<0) 
        { 
            price = Double.parseDouble(JOptionPane.showInputDialog(null,"Error! Invalid price. Please enter the price again.")); 
        } 
        this.price = price; 
    } 
  
    public int getAvailSeats() 
    { 
        return availSeats; 
    } 
  
    public void setAvailSeats(int availSeats)  
    { 
        while(availSeats>this.availSeats) 
        { 
            availSeats = Integer.parseInt(JOptionPane.showInputDialog(null,"Error! Invalid number of seats. Please enter number of seats available less than "+this.availSeats)); 
        } 
        this.availSeats = this.availSeats - availSeats; 
    } 
  
    public String toString()  
    { 
        String output = ""; 
        output += "Event ID: "+eventID+"\n"; 
        output += "Location: "+location+"\n"; 
        output += "Event Type: "+eventType+"\n"; 
        output += "Price: "+price+"\n"; 
        output += "Avaliable Seats: "+availSeats+"\n"; 
        return output; 
    }     
} 