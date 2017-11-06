import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class AdminUserFrame extends JFrame{
	
	JPanel panel01;
	JPanel panel02;
	
	JLabel category;
	JLabel subCategory;
	JLabel item;
	
	JButton addCategory;
	JButton updateCategory;
	JButton deleteCategory;
	
	JButton addSubCategory;
	JButton updateSubCategory;
	JButton deleteSubCategory;
	
	JButton addItem;
	JButton updateItem;
	JButton deleteItem;
	
	JButton logOut;
	
	BorderLayout bLayout = new BorderLayout();
	
	public AdminUserFrame()
	{
		super("Kiosk System");
		setSize(400,180);
		setLayout(bLayout);
		setResizable(false);
		
		panel01 = new JPanel();
		panel01.setLayout(new GridLayout(4, 3, 5, 5));
		panel02 = new JPanel();
		panel02.setLayout(new GridLayout(1,1,5,5));
		category = new JLabel("For Category:");
		subCategory = new JLabel("For SubCategory:");
		item = new JLabel("For Items");
		
		addCategory = new JButton("Add Category");
		updateCategory = new JButton("Update Category");
		deleteCategory = new JButton("Delete Category");
		
		addSubCategory = new JButton("Add SubCategory");
		updateSubCategory = new JButton("Update SubCategory");
		deleteSubCategory = new JButton("Delete SubCategory");
		
		addItem = new JButton("Add Item");
		updateItem = new JButton("Update Item");
		deleteItem = new JButton("Delete Item");
		
		logOut = new JButton("LogOut");
		
		panel01.add(category);
		panel01.add(subCategory);
		panel01.add(item);
		panel01.add(addCategory);
		panel01.add(addSubCategory);
		panel01.add(addItem);
		panel01.add(updateCategory);
		panel01.add(updateSubCategory);
		panel01.add(updateItem);
		panel01.add(deleteCategory);
		panel01.add(deleteSubCategory);
		panel01.add(deleteItem);
		
		add(panel01, bLayout.NORTH);
		
		panel02.add(logOut);
		add(panel02, bLayout.SOUTH);
		
		AddCategoryButtonListener acButtonListener = new AddCategoryButtonListener();
		addCategory.addActionListener(acButtonListener);
		UpdateCategoryButtonListener ucButtonListener = new UpdateCategoryButtonListener();
		updateCategory.addActionListener(ucButtonListener);
		DeleteCategoryButtonListener dcButtonListener = new DeleteCategoryButtonListener();
		deleteCategory.addActionListener(dcButtonListener);
		
		AddSubCategoryButtonListener asButtonListener = new AddSubCategoryButtonListener();
		addSubCategory.addActionListener(asButtonListener);
		UpdateSubCategoryButtonListener usButtonListener = new UpdateSubCategoryButtonListener();
		updateSubCategory.addActionListener(usButtonListener);
		DeleteSubCategoryButtonListener deButtonListener = new DeleteSubCategoryButtonListener();
		deleteSubCategory.addActionListener(deButtonListener);
		
		AddItemButtonListener adButtonListener = new AddItemButtonListener();
		addItem.addActionListener(adButtonListener);
		UpdateItemButtonListener uiButtonListener = new UpdateItemButtonListener();
		updateItem.addActionListener(uiButtonListener);
		DeleteItemButtonListener diButtonListener = new DeleteItemButtonListener();
		deleteItem.addActionListener(diButtonListener);
		
		LogOutButtonListener lobListener = new LogOutButtonListener();
		logOut.addActionListener(lobListener);
		
	}
	public class LogOutButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			UserFrame uFrame = new UserFrame();
			uFrame.setVisible(true);
			AdminUserFrame.this.setVisible(false);
		}
	}
	
	public class AddCategoryButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String categoryName;
			categoryName = JOptionPane.showInputDialog(null, "Please enter the name of the category that you would like to add:");
			CategoryDB catDB = new CategoryDB();
			catDB.addCategory(categoryName);
		}
	}
	public class UpdateCategoryButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String oldCategoryName;
			oldCategoryName = JOptionPane.showInputDialog(null, "Please enter the name of the category that you would like to update:");
			String newCategoryName;
			newCategoryName = JOptionPane.showInputDialog(null, "Please enter the new name of the category:");
			CategoryDB catDB = new CategoryDB();
			catDB.updateCategory(oldCategoryName, newCategoryName);
		}
	}
	public class DeleteCategoryButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String newCategoryName;
			newCategoryName = JOptionPane.showInputDialog(null, "Please enter the name of the category that you would like to delete:");
			CategoryDB catDB = new CategoryDB();
			catDB.deleteCategory(newCategoryName);
		}
	}
	public class AddSubCategoryButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String subCategoryName;
			subCategoryName = JOptionPane.showInputDialog(null, "Please enter the name of the subcategory that you would like to add:");
			String categoryName;
			categoryName = JOptionPane.showInputDialog(null, "Please enter the name of the subcategory that the subcategory belongs under:"); 
			SubCategoryDB subCatDB = new SubCategoryDB();
			subCatDB.addSubCategory(subCategoryName, categoryName);
		}
	}
	public class UpdateSubCategoryButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String oldSubCategoryName;
			oldSubCategoryName = JOptionPane.showInputDialog(null, "Please enter the name of the subcategory that you would like to update:");
			String newSubCategoryName;
			newSubCategoryName = JOptionPane.showInputDialog(null, "Please enter the new name of the subcategory:"); 
			SubCategoryDB subCatDB = new SubCategoryDB();
			subCatDB.updateSubCategory(oldSubCategoryName, newSubCategoryName);
		}
	}
	public class DeleteSubCategoryButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String newSubCategoryName;
			newSubCategoryName = JOptionPane.showInputDialog(null, "Please enter the name of the sub category that you would like to delete:");
			SubCategoryDB subcatDB = new SubCategoryDB();
			subcatDB.deleteSubCategory(newSubCategoryName);
	}
}
	public class AddItemButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String itemName;
			String itemBrand;
			String aisle;
			String quantity;
			String price;
			itemName = JOptionPane.showInputDialog(null, "Please enter the name of the item that you would like to add:");
			itemBrand = JOptionPane.showInputDialog(null, "Please enter the brand of the item that you would like to add:");
			aisle = JOptionPane.showInputDialog(null, "Please enter the aisle of the item that you would like to add:");
			quantity = JOptionPane.showInputDialog(null, "Please enter the quantity of the item that you would like to add:");
			price = JOptionPane.showInputDialog(null, "Please enter the price of the item that you would like to add:");
			int q = Integer.parseInt(quantity);
			double p = Double.parseDouble(price);
			ItemDB itemDB = new ItemDB();
			itemDB.addItem(itemName, itemBrand, aisle, q, p);
		}
	}
	public class UpdateItemButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String oldItemName;
			String itemName;
			String itemBrand;
			String aisle;
			String quantity;
			String price;
			oldItemName = JOptionPane.showInputDialog(null, "Please enter the name of the item that you would like to update:");
			itemName = JOptionPane.showInputDialog(null, "Please enter the new name of the item");
			itemBrand = JOptionPane.showInputDialog(null, "Please enter the brand of the item that you would like to update:");
			aisle = JOptionPane.showInputDialog(null, "Please enter the aisle of the item that you would like to update:");
			quantity = JOptionPane.showInputDialog(null, "Please enter the quantity of the item that you would like to update:");
			price = JOptionPane.showInputDialog(null, "Please enter the price of the item that you would like to update:");
			int q = Integer.parseInt(quantity);
			double p = Double.parseDouble(price);
			ItemDB itemDB = new ItemDB();
			itemDB.updateItem(oldItemName, itemName, itemBrand, aisle, q, p);
		}
	}
	public class DeleteItemButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String name;
			name = JOptionPane.showInputDialog(null, "Please enter the name of the item");
			ItemDB itemDB = new ItemDB();
			itemDB.deleteItem(name);
		}
	}
}