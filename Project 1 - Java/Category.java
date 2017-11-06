public class Category {
	private int categoryID;
	private String categoryName;
	
	public Category(){
	}
	public Category(int catID, String catName){
		categoryID = catID;
		categoryName = catName;
	}
	public void setCategoryName(String catName){
		categoryName = catName;
	}
	public int getCategoryID(){
		return categoryID;
	}
	public String getCategoryName(){
		return categoryName;
	}
}
