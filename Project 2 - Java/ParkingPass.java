import javax.swing.JOptionPane; 
  
/** 
 * @author person C - Vishal Balani 
 */
public class ParkingPass { 
  
    private int parkingPassID; 
    private int numberSold; 
    private double price; 
    private int capacity; 
  
    public ParkingPass() 
    { 
    } 
      
    public ParkingPass(int numberSold, double price, int capacity) { 
        this.parkingPassID++;; 
        this.setNumberSold(numberSold); 
        this.setPrice(price); 
        this.setCapacity(capacity); 
    } 
  
    public int getParkingPassID() { 
        return parkingPassID; 
    } 
  
    public void setParkingPassID(int parkingPassID) 
    { 
        this.parkingPassID = parkingPassID; 
    } 
  
    public int getNumberSold()  
    { 
        return numberSold; 
    } 
  
    public void setNumberSold(int numberSold)  
    { 
        while(numberSold>this.numberSold) 
        { 
            numberSold = Integer.parseInt(JOptionPane.showInputDialog(null,"Error! Invalid number of Pass. Please enter number of parking Pass you want to purchase which are less than "+this.capacity)); 
        } 
        this.numberSold = numberSold; 
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
  
    public int getCapacity() 
    { 
        return capacity; 
    } 
  
    public void setCapacity(int capacity)  
    { 
        while(capacity>0) 
        { 
            capacity = Integer.parseInt(JOptionPane.showInputDialog(null,"Error! Invalid number of Parking Pass to sell. Please enter number of parking Pass again")); 
        } 
        this.capacity = capacity; 
    } 
      
     public String toString()  
    { 
        String output = ""; 
        output += "Parking Pass ID: "+parkingPassID+"\n"; 
        output += "NUmber of Parking pass sold: "+numberSold+"\n"; 
        output += "Parking Pass Price: "+price+"\n"; 
        output += "Capacity: "+capacity+"\n"; 
        return output; 
    }    
} 