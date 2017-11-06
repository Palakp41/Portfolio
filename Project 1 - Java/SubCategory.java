public class SubCategory extends Category{
	private int subCategoryID;
	private String subCategoryName;
	
	public SubCategory(){
		super();
	}
	public SubCategory(int subCatID, String subCatName, int catID){
		super();
		subCategoryID = subCatID;
		subCategoryName = subCatName;
	}
	public void setSubCategoryName(String subCatName){
		subCategoryName = subCatName;
	}
	public String getSubCategoryName(){
		return subCategoryName;
	}
	public int getSubCategoryID(){
		return subCategoryID;
	}
}
