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
import javax.swing.JCheckBox;
import javax.swing.JComboBox; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.JRadioButton; 
import javax.swing.JTextField; 
import javax.swing.border.EtchedBorder; 
import javax.swing.border.TitledBorder;
  
public class ItemFrame extends JFrame{ 
    JPanel panel01 = new JPanel();
      
    JLabel itemLabel;
    JCheckBox[] itemBox;
      
    JButton addCartButton;
    JButton addWishListButton;
    JButton logOutButton;
    
    int l;
    
    ArrayList<Item> items;
    
    BorderLayout bLayout = new BorderLayout(); 
      
    public ItemFrame(int subCatID){ 
        super("Kiosk System"); 
        setSize(400,600); 
        setLayout(bLayout);
        
        ItemDB item = new ItemDB();
        int l = item.getItem(subCatID).size();
        items = item.getItem(subCatID);
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1,1,5,5));
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(l,1,5,5));
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2,1,5,5));
        JPanel southNorthPanel = new JPanel();
        southNorthPanel.setLayout(new GridLayout(1, 2, 5, 5));
        JPanel southSouthPanel = new JPanel();
        southSouthPanel.setLayout(new GridLayout(1, 1, 5, 5));
        
        itemLabel = new JLabel("Items: ");
        itemBox = getItems(subCatID);
        for(int i=0; i<l; i++){
        	centerPanel.add(itemBox[i]);
        }
        addCartButton = new JButton("Add to Cart");
        addWishListButton = new JButton("Add to WishList");
        logOutButton = new JButton("Log Out");
        
        northPanel.add(itemLabel);
        southNorthPanel.add(addCartButton);
        southNorthPanel.add(addWishListButton);
        southSouthPanel.add(logOutButton);
        southPanel.add(southNorthPanel);
        southPanel.add(southSouthPanel);
        add(northPanel, bLayout.NORTH);
        add(centerPanel, bLayout.CENTER);
        add(southPanel, bLayout.SOUTH);
        
        AddCartButtonListener addcbListener = new AddCartButtonListener();
        addCartButton.addActionListener(addcbListener);
        
        AddWishListButtonListener addwlListener = new AddWishListButtonListener();
        addWishListButton.addActionListener(addwlListener);
    }
    private JCheckBox[] getItems(int subCatID){
		ItemDB item = new ItemDB();
		ArrayList<Item> it = item.getItem(subCatID);
		JCheckBox[] checkBox = new JCheckBox[it.size()];
		for (int i = 0; it != null && i < it.size(); i++){
			checkBox[i] = new JCheckBox();
			if (it.get(i) != null && checkBox[i] != null){
				System.out.println("Name: " + it.get(i).getItemName() +" Price:$" +  it.get(i).getPrice() + " Brand: " +  it.get(i).getItemBrand() + " Aisle: "+ it.get(i).getAisle());
				checkBox[i].setText("Name: " + it.get(i).getItemName() +" Price: $" +  it.get(i).getPrice() + " Brand: " +  it.get(i).getItemBrand() + "Aisle: "+ it.get(i).getAisle());
			}
		}
		return checkBox;
	}
    public class AddCartButtonListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		Integer[] indexes = new Integer[itemBox.length];
    		int counter = 0;
    		PublicUserDB puserDB = new PublicUserDB();
    		for(int i=0; i<itemBox.length; i++){
    			if(itemBox[i].isSelected() == true){
    				indexes[counter] = i;
    				counter++;
    			}
    		}
    		int[] id = new int[indexes.length];
    		int idCounter = 0;
    		for(int i = 0; indexes[i] != null && i < indexes.length; i++){
    			if (indexes[i] != null){
    				for(int x=0; items != null && x < items.size(); x++){
    					if(items.get(x) != null){
    						if(indexes[i] == x){
    							System.out.println("Name: " + items.get(x).getItemName() + items.get(x).getItemID());
    							id[idCounter] = items.get(x).getItemID();
    							idCounter++;
    						}
    					}
    				}
				}
		}
    		for(int i=0; i<id.length; i++){
    			puserDB.addToCart(id[i]);
    		}
    		JOptionPane.showMessageDialog(null, "Added to Cart");
    	}
    }
    public class AddWishListButtonListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		Integer[] indexes = new Integer[itemBox.length];
    		int counter = 0;
    		PublicUserDB puserDB = new PublicUserDB();
    		for(int i=0; i<itemBox.length; i++){
    			if(itemBox[i].isSelected() == true){
    				indexes[counter] = i;
    				counter++;
    			}
    		}
    		int[] id = new int[indexes.length];
    		int idCounter = 0;
    		for(int i = 0; indexes[i] != null && i < indexes.length; i++){
    			if (indexes[i] != null){
    				for(int x=0; items != null && x < items.size(); x++){
    					if(items.get(x) != null){
    						if(indexes[i] == x){
    							System.out.println("Name: " + items.get(x).getItemName() + items.get(x).getItemID());
    							id[idCounter] = items.get(x).getItemID();
    						}
    					}
    				}
				}
		}
    		for(int i=0; i<id.length; i++){
    			if(id[i] != 0){
    				puserDB.addToCart(id[i]);
    			}
    		}
    		JOptionPane.showMessageDialog(null, "Added to Wist List");
    	}
    }
    public class LogOutButtonListener implements ActionListener{ 
        public void actionPerformed(ActionEvent e){ 
            UserFrame uFrame = new UserFrame(); 
            uFrame.setVisible(true); 
            ItemFrame.this.setVisible(false); 
        } 
    } 
}//end class 