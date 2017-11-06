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
import javax.swing.JButton; 
import javax.swing.JComboBox; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JRadioButton; 
import javax.swing.JTextField; 
import javax.swing.border.EtchedBorder; 
import javax.swing.border.TitledBorder; 
  
public class PublicUserFrame extends JFrame{ 
    JPanel panel01; 
    JPanel panel02; 
    JPanel panel03; 
      
    JButton loginButton; 
    JButton resetButton; 
    JButton newUserButton; 
      
    JLabel stateLabelEventBooking; 
    JComboBox stateComboBox; 
      
    JLabel stadiumListLabel; 
    JRadioButton[] stadiumRadioButton; 
    ButtonGroup stadiumButtonGroup = new ButtonGroup(); 
      
    JRadioButton[] eventRadioButton; 
    ButtonGroup eventButtonGroup = new ButtonGroup(); 
      
    JButton submitButton; 
    JButton logOutButton; 
      
    JLabel blankLabel = new JLabel(""); 
      
    public static final String FILE_PATH_LOCATION = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\Location.txt"; 
    public static final String FILE_PATH_STADIUMS = "C:\\Users\\Palak\\Documents\\GMU\\Fall 2013\\Workspace\\IT306Project\\src\\Stadiums.txt"; 
      
    public String[] allStadiums = new String[15]; 
    int countStadium = 0; 
    public String[] allEvents = new String[15]; 
    int countEvent = 0; 
    BorderLayout bLayout = new BorderLayout(); 
      
    public PublicUserFrame(){ 
        super("PMV Event Ticketing"); 
        setSize(250,150); 
        setLayout(bLayout); 
          
        panel01 = new JPanel(); 
        panel01.setLayout(new GridLayout(2, 2, 5, 5)); 
        stateLabelEventBooking = new JLabel("Select State:"); 
        stateComboBox = new JComboBox(); 
        try { 
            //Read file using buffered reader. 
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH_LOCATION)); 
            String sCurrentLine =br.readLine(); 
                while (sCurrentLine != null) { 
                    String state = null; 
                      
                    //Parse each line in the text file using "|"  
                    StringTokenizer st = new StringTokenizer(sCurrentLine,"|"); 
                      
                    //Set state 
                    if(st.hasMoreTokens()) { 
                        state = st.nextToken(); 
                        if(state != null && !state.equalsIgnoreCase("state") && state.equalsIgnoreCase("Virginia") ) 
                        { 
                            stateComboBox.addItem(state); 
                            System.out.println(state); 
                        } 
                          
                    } 
                    //Read next line 
                    sCurrentLine = br.readLine(); 
                } 
            } catch (Exception FILENOTFOUND) { 
                System.out.println("File not found!"); 
            } 
//      stateLabelEventBooking = new JLabel("Enter State:"); 
//      stateComboBox = new JComboBox(); 
//      stateComboBox.addItem("VA"); 
        stateComboBox.addItem("Washington DC"); 
        stateComboBox.addItem("Maryland"); 
        stateComboBox.addItem("Deleware"); 
        stateComboBox.setSelectedIndex(0); 
          
        submitButton = new JButton("Submit"); 
        logOutButton = new JButton("Log Out"); 
          
        panel01.add(stateLabelEventBooking); 
        panel01.add(stateComboBox); 
        panel01.add(submitButton); 
        panel01.add(logOutButton); 
          
        add(panel01, bLayout.NORTH); 
          
        SubmitButtonListener submitListener = new SubmitButtonListener(); 
        submitButton.addActionListener(submitListener); 
          
        LogOutButtonListener logoutListener = new LogOutButtonListener(); 
        logOutButton.addActionListener(logoutListener); 
    } 
    public class SubmitButtonListener implements ActionListener{ 
        public void actionPerformed(ActionEvent e){ 
            //show all the stadiums with the radio button 
            if(stateComboBox.getSelectedIndex() >= 0){ 
                String userIDFound = null; 
                boolean isStateExists = false; 
                try { 
                //Read file using buffered reader. 
                BufferedReader br = new BufferedReader(new FileReader(FILE_PATH_LOCATION)); 
                String sCurrentLine =br.readLine(); 
                    while (sCurrentLine != null) { 
                        String state = null; 
                        String zipCode = null; 
                          
                        //Parse each line in the text file using "|"  
                        StringTokenizer st = new StringTokenizer(sCurrentLine,"|"); 
                          
                        //Set User Name - Second string is user name 
                        if(st.hasMoreTokens()) { 
                            state = st.nextToken(); 
                            System.out.println(state); 
                        } 
                          
                        //Set password - Third String is password 
                        if(st.hasMoreTokens()) { 
                            zipCode = st.nextToken(); 
                            System.out.println(zipCode); 
                        } 
                        String comboBoxState = (String) stateComboBox.getSelectedItem(); 
                        //If user name and password match in each line, then return true 
                        if(state != null && comboBoxState.equalsIgnoreCase(state)) { 
                            isStateExists = true; 
                            break; 
                        } 
                        sCurrentLine = br.readLine(); 
                    } 
                    } 
                        catch (Exception FILENOTFOUND) { 
                            System.out.println("File not found!"); 
                        } 
                            try { 
                                BufferedReader br2 = new BufferedReader(new FileReader(FILE_PATH_STADIUMS)); 
                                String sCurrentLine2 = br2.readLine(); 
                                while(sCurrentLine2 != null) { 
                                    String stateString = null; 
                                    String stadium = null; 
                                      
                                    //Parse each line in the text file using "|"  
                                    StringTokenizer st2 = new StringTokenizer(sCurrentLine2,"|"); 
                                      
                                    //Set User Name - Second string is user name 
                                    if(st2.hasMoreTokens()) { 
                                        stateString = st2.nextToken(); 
                                        System.out.println(stateString); 
                                    } 
                                      
                                    //Set password - Third String is password 
                                    if(st2.hasMoreTokens()) { 
                                        stadium = st2.nextToken(); 
                                        System.out.println(stadium); 
                                    } 
                                    String comboBoxState = (String) stateComboBox.getSelectedItem(); 
                                    if(stateString != null && comboBoxState.equalsIgnoreCase(stateString)){ 
                                        System.out.println("Test1"); 
                                            allStadiums[countStadium] = stadium; 
                                            countStadium++; 
  
                                            System.out.println("Test2"); 
                                    } 
  
                                    //Read next line 
                                    sCurrentLine2 = br2.readLine(); 
                            } 
                    } 
                            catch (Exception FILENOTFOUND) { 
                                System.out.println("File not found!"); 
                            } 
                } 
            PublicUserFrame.this.setSize(250, 500); 
            panel02 = new JPanel(); 
            panel02.setLayout(new GridLayout(countStadium,1,5,5)); 
            stadiumRadioButton = new JRadioButton[15]; 
            for(int i=0; i<allStadiums.length; i++){ 
                if(allStadiums[i] != null){ 
                    System.out.println("Test3:" + allStadiums[i]); 
                    stadiumRadioButton[i] = new JRadioButton(allStadiums[i]); 
                    panel02.add(stadiumRadioButton[i]); 
                    stadiumButtonGroup.add(stadiumRadioButton[i]); 
                    System.out.println(i); 
                } 
            } 
            add(panel02, bLayout.CENTER); 
            RadioButtonListener rbListener = new RadioButtonListener(); 
            for(int i=0; i<stadiumRadioButton.length; i++){ 
                if(stadiumRadioButton[i] != null){ 
                    stadiumRadioButton[i].addItemListener(rbListener); 
                } 
            } 
        } 
    }//end action listener 
    public class RadioButtonListener implements ItemListener{ 
        public void itemStateChanged(ItemEvent e){ 
            for(int i=0; i<allStadiums.length; i++){ 
                if(allStadiums[i] != null){ 
                    if(stadiumRadioButton[i].isSelected()){ 
                        EventFrame eFrame = new EventFrame(allStadiums[i]); 
                        eFrame.setVisible(true); 
                        PublicUserFrame.this.setVisible(false); 
                    } 
                } 
            } 
        } 
    }//end item listener 
    public class LogOutButtonListener implements ActionListener{ 
        public void actionPerformed(ActionEvent e){ 
            UserFrame uFrame = new UserFrame(); 
            uFrame.setVisible(true); 
            PublicUserFrame.this.setVisible(false); 
        } 
    } 
}//end class 