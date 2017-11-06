import javax.swing.JOptionPane; 
  
/** 
 * @author person C - Vishal Balani 
 */
public class Ticket { 
      
    private int ticketID; 
    private Location location; 
    private double price; 
    private String date; 
    private String ticketTier; 
  
    public Ticket()  
    { 
    } 
  
    public Ticket(int ticketID, String locName, int zipCode, String state, double price, String date, String ticketTier) 
    { 
        this.ticketID++; 
        this.location = new Location(locName, zipCode, state); 
        this.setPrice(price); 
        this.setDate(date); 
        this.setTicketTier(ticketTier); 
    } 
      
    public Ticket(int ticketID, String locName, int zipCode, String state,int numberSold, double price, int capacity, double ticketPrice, String date, String ticketTier) 
    { 
        this.ticketID++; 
        this.setPrice(price); 
        this.setDate(date); 
        this.setTicketTier(ticketTier); 
        this.location = new Location(locName, zipCode, state, numberSold, price, capacity); 
    } 
      
    public int getTicketID()  
    { 
        return ticketID; 
    } 
  
    public double getPrice() 
    { 
        return price; 
    } 
  
    public void setPrice(double price)  
    { 
        while(Double.isNaN(price)|| price<0) 
        { 
            price = Double.parseDouble(JOptionPane.showInputDialog(null,"Error! Invalid ticket price. Please enter the Ticket price again.")); 
        } 
        this.price = price; 
    } 
  
    public String getDate() 
    { 
        return date; 
    } 
  
    public void setDate(String date) 
    { 
        while(date.equals(null) || date.length()<8) 
        { 
            date = JOptionPane.showInputDialog(null,"Error! Invalid Date. Please enter the date again in format: MMDDYYYY (no symbols)."); 
        } 
        this.date = date; 
    } 
  
    public Location getLocation() { 
        return location; 
    } 
  
    public String getTicketTier()  
    { 
        return ticketTier; 
    } 
  
    public void setTicketTier(String ticketTier) 
    { 
        while(ticketTier.equals(null) || ticketTier.isEmpty()) 
        { 
            ticketTier = JOptionPane.showInputDialog(null,"Error! Invalid ticketTier. Please enter the ticketTier again."); 
        } 
        this.ticketTier = ticketTier; 
    } 
  
    public String toString()  
    { 
        String output = ""; 
        output += "Ticket ID: "+ticketID+"\n"; 
        output += "Location: "+location+"\n"; 
        output += "Ticket Price: "+price+"\n"; 
        output += "Date: "+date+"\n"; 
        output += "Ticket Tier: "+ticketTier+"\n"; 
        return output; 
    } 
 } 