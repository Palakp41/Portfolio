/** 
 * @author person A - Palak Patel 
 */
import java.awt.BorderLayout; 
import java.awt.GridLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.ItemEvent; 
import java.awt.event.ItemListener; 
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.util.StringTokenizer; 
  
import javax.swing.ButtonGroup; 
import javax.swing.ButtonModel; 
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JRadioButton; 
import javax.swing.JTextField; 
  
  
public class EventFrame extends JFrame{ 
    JPanel panel03; 
    JPanel panel04; 
    JPanel panel05; 
      
    int counter = 0; 
    int eventPrice = 0; 
      
    JRadioButton[] eventRadioButton; 
    ButtonGroup eventButtonGroup = new ButtonGroup(); 
      
    public String[] allEvents = new String[15]; 
    int countEvent = 0; 
      
    public String[] allPrices = new String[15]; 
    int countPrice=0; 
      
    JRadioButton[] prices; 
    ButtonGroup priceButtonGroup = new ButtonGroup(); 
    ButtonGroup eventPriceButtonGroup = new ButtonGroup(); 
      
    JButton purchaseButton = new JButton("Purchase"); 
    JButton goBackButton; 
    JButton logOutButton; 
    BorderLayout bLayout = new BorderLayout(); 
      
    JLabel confirmationID = new JLabel(""); 
      
    public static final String FILE_PATH_EVENTINFO = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\EventInfo.txt"; 
    public static final String FILE_PATH_EVENTPRICE = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\EventInfoPrice.txt"; 
      
    public EventFrame(String rb){ 
        super("PMV Event Ticketing"); 
        setSize(500,300); 
          
        String userIDFound = null; 
        try { 
        //Read file using buffered reader. 
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH_EVENTINFO)); 
        String sCurrentLine =br.readLine(); 
            while (sCurrentLine != null) { 
                String stadium = null; 
                String event = null; 
                  
                //Parse each line in the text file using "|"  
                StringTokenizer st = new StringTokenizer(sCurrentLine,"|"); 
                  
                //Set User ID - First string is user ID  
                if(st.hasMoreTokens()) { 
                    stadium = st.nextToken(); 
                    System.out.println(stadium); 
                } 
                  
                //Set User Name - Second string is user name 
                if(st.hasMoreTokens()) { 
                    event = st.nextToken(); 
                    System.out.println(event); 
                } 
                                          
                //If user name and password match in each line, then return true 
                if(stadium != null && rb.equalsIgnoreCase(stadium)) { 
                    allEvents[countEvent] = event; 
                    countEvent++; 
                } 
                  
                //Read next line 
                sCurrentLine = br.readLine(); 
            } 
        } catch (Exception FILENOTFOUND) { 
            System.out.println("File not found!"); 
        } 
            //PublicUserFrame.this.setSize(250, 600); 
            panel03 = new JPanel(); 
            panel03.setLayout(new GridLayout(countEvent, 1, 5, 5)); 
            eventRadioButton = new JRadioButton[15]; 
            for(int i=0; i<allEvents.length; i++){ 
            if(allEvents[i] != null){ 
                System.out.println("Test3:" + allEvents[i]); 
                eventRadioButton[i] = new JRadioButton(allEvents[i]); 
                panel03.add(eventRadioButton[i]); 
                eventButtonGroup.add(eventRadioButton[i]); 
                System.out.println(i); 
                } 
            } 
            System.out.println("Before RadioButtonListener :"); 
            add(panel03, bLayout.NORTH); 
            RadioButtonListener rbListener = new RadioButtonListener(); 
            for(int i=0; i<eventRadioButton.length; i++){ 
                if(eventRadioButton[i] != null){ 
                    eventRadioButton[i].addItemListener(rbListener); 
                } 
            } 
    } 
    public class RadioButtonListener implements ItemListener{ 
        public void itemStateChanged(ItemEvent e){ 
            System.out.println("RadioButtonListener"); 
            String rb; 
            for(int i=0; i<allEvents.length; i++){ 
                if(allEvents[i] != null){ 
                    if(eventRadioButton[i].isSelected()){ 
                        rb = allEvents[i]; 
                    } 
                } 
            } 
            try { 
            //Read file using buffered reader. 
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH_EVENTPRICE)); 
            String sCurrentLine =br.readLine(); 
                while (sCurrentLine != null) { 
                    String price = null; 
                    String seatingLevel = null; 
                      
                    //Parse each line in the text file using "|"  
                    StringTokenizer st = new StringTokenizer(sCurrentLine,"|"); 
                      
                    //Set User ID - First string is user ID  
                    if(st.hasMoreTokens()) { 
                        price = st.nextToken(); 
                        System.out.println(price); 
                    } 
                      
                    //Set User Name - Second string is user name 
                    if(st.hasMoreTokens()) { 
                        seatingLevel = st.nextToken(); 
                        System.out.println(seatingLevel); 
                    } 
                                              
                    //If user name and password match in each line, then return true 
                    if(price != null && seatingLevel != null) { 
                        allPrices[countPrice] = price + " " + seatingLevel; 
                        countPrice++; 
                    } 
                      
                    //Read next line 
                    sCurrentLine = br.readLine(); 
                } 
            } catch (Exception FILENOTFOUND) { 
                System.out.println("File not found!"); 
            } 
                String label = ""; 
                EventFrame.this.setSize(350, 600); 
                panel04 = new JPanel(); 
                panel04.setLayout(new GridLayout(countPrice+2, 2, 5, 5)); 
                prices = new JRadioButton[15]; 
                for(int i=0; i<allPrices.length; i++){ 
                if(allPrices[i] != null){ 
                    System.out.println("Test3:" + allPrices[i]); 
                    prices[i] = new JRadioButton(allPrices[i]); 
                    panel04.add(prices[i]); 
                    eventPriceButtonGroup.add(prices[i]); 
                    System.out.println(i); 
                    } 
                } 
              
                  
                purchaseButton = new JButton("Purchase"); 
                panel04.add(purchaseButton); 
                logOutButton = new JButton("Log Out"); 
                panel04.add(logOutButton); 
                add(panel04, bLayout.SOUTH); 
                  
                SubmitButtonListener pricesbListener = new SubmitButtonListener(); 
                purchaseButton.addActionListener(pricesbListener); 
                  
                LogOutButtonListener logoutListener = new LogOutButtonListener(); 
                logOutButton.addActionListener(logoutListener); 
                add(panel04, bLayout.CENTER); 
  
        } 
    } 
    public class SubmitPriceButtonListener implements ActionListener{ 
        public void actionPerformed(ActionEvent e){ 
            System.out.println("SubmitPriceButtonListener"); 
  
//              purchaseButton = new JButton("Purchase"); 
//              panel04.add(purchaseButton); 
//              logOutButton = new JButton("Log Out"); 
//              panel04.add(logOutButton); 
//              add(panel04, bLayout.SOUTH); 
                  
                SubmitButtonListener sbListener = new SubmitButtonListener(); 
                purchaseButton.addActionListener(sbListener); 
                  
                LogOutButtonListener logoutListener = new LogOutButtonListener(); 
                logOutButton.addActionListener(logoutListener); 
                System.out.println("Exit SubmitPriceButtonListener"); 
  
        } 
    }//end item listener 
      
    public class SubmitButtonListener implements ActionListener{ 
        public void actionPerformed(ActionEvent e){ 
  
            String eventP = e.getActionCommand(); 
            System.out.println("SubmitButtonListener: "+eventP); 
            EventFrame.this.setSize(350, 750); 
            panel05 = new JPanel(); 
            panel05.setLayout(new GridLayout(countPrice+1, 1, 5, 5)); 
            ++counter; 
            confirmationID.setText("Confirmation Number: " + counter); 
            //counter++; 
            panel05.add(confirmationID); 
            add(panel05, bLayout.SOUTH); 
        } 
    } 
    public class LogOutButtonListener implements ActionListener{ 
        public void actionPerformed(ActionEvent e){ 
            UserFrame uFrame = new UserFrame(); 
            uFrame.setVisible(true); 
            EventFrame.this.setVisible(false); 
        } 
    } 
}//end class