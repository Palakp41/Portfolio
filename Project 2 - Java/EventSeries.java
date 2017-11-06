import javax.swing.JOptionPane; 
  
/** 
 * @author person C - Vishal Balani 
*/
  
public class EventSeries { 
  
    private int seriesID; 
    private String seriesName; 
    private String [] performers; 
  
    public EventSeries()  
    { 
    } 
  
    public EventSeries(String seriesName, String[] performers) { 
        this.seriesID++; 
        this.setSeriesName(seriesName); 
        this.setPerformers(performers); 
    } 
      
    public int getSeriesID()  
    { 
        return seriesID; 
    } 
  
    public void setSeriesID(int seriesID)  
    { 
        this.seriesID = seriesID; 
    } 
  
    public String getSeriesName()  
    { 
        return seriesName; 
    } 
  
    public void setSeriesName(String seriesName)  
    { 
        while(seriesName.equals(null) || seriesName.isEmpty()) 
        { 
            seriesName = JOptionPane.showInputDialog(null,"Error! Invalid Series name. Please enter Series Name again."); 
        } 
        this.seriesName = seriesName; 
    } 
  
    public String[] getPerformers()  
    { 
        return performers; 
    } 
  
    public void setPerformers(String[] performers)  
    {    
        int i = 0; String name = ""; 
        while(performers.length<= 0 ||performers.equals(null)) 
        { 
            name = JOptionPane.showInputDialog(null,"Error! Invalid Performer's names. Please enter name of Performer #"+(i+1)+"\nNote: enter Done to stop."); 
            if(name.equalsIgnoreCase("Done")) 
                break; 
            performers[i] = name; 
        } 
        this.performers = performers; 
    } 
     
    public String toString()  
    { 
        String output = ""; 
        output += "Series ID: "+seriesID+"\n"; 
        output += "Series Name: "+seriesName+"\n"; 
        output += "Performers:\n"; 
        for(int i = 0; i<performers.length;i++) 
        { 
            output += (i+1) +"."+performers[i]+"\n"; 
        } 
        return output; 
    } 
  
} 