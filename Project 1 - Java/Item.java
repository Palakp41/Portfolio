
public class Item extends SubCategory{
	private int itemID;
	private String itemName;
	private String brand;
	private String aisle;
	private int quantity;
	private double price;
	
	public Item(int itID, String name, String b, String a, int q, double p){
		super();
		itemID = itID;
		itemName = name;
		brand = b;
		aisle = a;
		quantity = q;
		price = p;
	}
	public int getItemID(){
		return itemID;
	}
	public void setItemName(String name){
		itemName = name;
	}
	public void setItemBrand(String b){
		brand = b;
	}
	public void setAisle(String a){
		aisle = a;
	}
	public void setQuanity(int q){
		quantity = q;
	}
	public void setPrice(double p){
		price = p;
	}
	public String getItemName(){
		return itemName;
	}
	public String getItemBrand(){
		return brand;
	}
	public String getAisle(){
		return aisle;
	}
	public int getQuantity(){
		return quantity;
	}
	public double getPrice(){
		return price;
	}
}
