import java.awt.BorderLayout; 
import java.awt.GridLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.ItemEvent; 
import java.awt.event.ItemListener; 
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.util.ArrayList;
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
    JPanel panel01 = new JPanel();
    JPanel panel02 = new JPanel();
      
    JLabel searchLabel;
    JComboBox categoryBox;
    
    JLabel searchLabel2;
    JComboBox subCategoryBox;
      
    JButton submitButton; 
    JButton logOutButton;
    
    BorderLayout bLayout = new BorderLayout(); 
      
    public PublicUserFrame(){ 
        super("Kiosk System"); 
        setSize(250,150); 
        setLayout(bLayout); 
        
        panel01.setLayout(new GridLayout(1, 2, 5, 5));
        searchLabel = new JLabel("Search Item:");
        categoryBox = getCategory();
        
        panel01.add(searchLabel); 
        panel01.add(categoryBox);
        add(panel01, bLayout.NORTH);
          
        ComboBoxListener cbListener = new ComboBoxListener();
        categoryBox.addItemListener(cbListener);
    }
    private JComboBox getCategory(){
		CategoryDB category = new CategoryDB();
		ArrayList<Category> cat = category.getCategory();
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Select");
		for (int i = 0; cat != null && i < cat.size(); i++){
			if (cat.get(i) != null)
				comboBox.addItem(cat.get(i).getCategoryName());
		}
		return comboBox;
	}
    private JComboBox getSubCategory(int catID){
		SubCategoryDB subCategory = new SubCategoryDB();
		ArrayList<SubCategory> subCat = subCategory.getSubCategory(catID);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Select");
		for (int i = 0; subCat != null && i < subCat.size(); i++){
			if (subCat.get(i) != null)
				comboBox.addItem(subCat.get(i).getSubCategoryName());
		}
		return comboBox;
	}
    public class ComboBoxListener implements ItemListener {
    	public void itemStateChanged(ItemEvent e) {
    		if (!categoryBox.getSelectedItem().toString().equals("Select")){
    			PublicUserFrame.this.setSize(250, 250);
    			searchLabel2 = new JLabel("Search Item:");
    			subCategoryBox = getSubCategory(categoryBox.getSelectedIndex());
    			submitButton = new JButton("Submit"); 
    	        logOutButton = new JButton("Log Out");
    			panel02 = new JPanel();
    			panel02.setLayout(new GridLayout(2, 2, 5, 5));
    			panel02.add(searchLabel2);
    			panel02.add(subCategoryBox);
    	        panel02.add(submitButton); 
    	        panel02.add(logOutButton); 
    			add(panel02, bLayout.CENTER);
    			
    			SubmitButtonListener submitListener = new SubmitButtonListener(); 
    	        submitButton.addActionListener(submitListener); 
    	          
    	        LogOutButtonListener logoutListener = new LogOutButtonListener(); 
    	        logOutButton.addActionListener(logoutListener); 
    		}
    	}
    }
    public class SubmitButtonListener implements ActionListener{
    	String name;
    	int id;
		public void actionPerformed(ActionEvent arg0) {
			name = subCategoryBox.getSelectedItem().toString();
			SubCategoryDB subCat = new SubCategoryDB();
        	id = subCat.getSubCategoryID(subCategoryBox.getSelectedItem().toString());
        	ItemFrame iFrame = new ItemFrame(id);
        	iFrame.setVisible(true);
        	PublicUserFrame.this.setVisible(false);
		}
    }//end action listener 
    public class RadioButtonListener implements ItemListener{ 
        public void itemStateChanged(ItemEvent e){
        	
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