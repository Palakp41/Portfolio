import java.util.*;
import java.sql.*;

public class SubCategoryDB {
    private Connection getConnection() {
        Connection connection = null;
        try {
        	String driverName = "oracle.jdbc.driver.OracleDriver";
        	Class.forName(driverName);
            String url = "jdbc:oracle:thin:@apollo.ite.gmu.edu:1521:ite10g";
            String username = "ppatel19";
            String password = "palakp123";
            connection = DriverManager.getConnection(url, username, password);
			return connection;
        }
        catch(Exception e) {
            System.err.println(e);
			return null;
        }
    }

    public ArrayList<SubCategory> getSubCategory(int catID) {
        String sql = "SELECT SUBCATEGORY_ID, SUBCATEGORY_NAME "
                   + "FROM SUBCATEGORY WHERE CATEGORY_ID = " + catID + " ORDER BY SUBCATEGORY_ID ASC";  
        ArrayList<SubCategory> subCategory = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while(rs.next()) {
                int id = rs.getInt("SUBCATEGORY_ID");
                String name = rs.getString("SUBCATEGORY_NAME");
                
                System.out.println(id + name + catID);
                SubCategory cat = new SubCategory(id, name, catID);
                subCategory.add(cat);
            }
            connection.close();
            return subCategory;
        }
        catch(SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    public int getSubCategoryID(String name) {
    	int id = 0;
        String sql = "SELECT SUBCATEGORY_ID "
                   + "FROM SUBCATEGORY, CATEGORY WHERE CATEGORY.CATEGORY_ID = SUBCATEGORY.CATEGORY_ID AND SUBCATEGORY_NAME = '" + name + "' ORDER BY SUBCATEGORY_ID ASC";
        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while(rs.next()) {
                id = rs.getInt("SUBCATEGORY_ID");
                
                System.out.println(id);
            }
            connection.close();
            return id;
        }
        catch(SQLException e) {
            System.err.println(e);
            return 0;
        }
    }
    
public boolean addSubCategory(String subName, String catName) {
	int catID = 0;
	int id = 0;
    String sql = "SELECT CATEGORY_ID FROM CATEGORY WHERE CATEGORY_NAME = '" + catName + "'";
    
    try (Connection connection = getConnection();
         PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
    	while(rs.next()) {
            id = rs.getInt("CATEGORY_ID");
            
            System.out.println(id);
        }
        connection.close();
    }
    catch(SQLException e) {
        System.err.println(e);
    }
    	
        String sql1 =
            "INSERT INTO SUBCATEGORY (SUBCATEGORY_ID, SUBCATEGORY_NAME, CATEGORY_ID) " +
            "VALUES (SEQ_SUBCAT.nextval, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps1 = connection.prepareStatement(sql1)) {	
            ps1.setString(1, subName);
            ps1.setInt(2, id);
            ps1.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }

public boolean updateSubCategory(String oldName, String newName) {

String sql =
    "UPDATE SUBCATEGORY SET SUBCATEGORY_NAME = ? WHERE SUBCATEGORY_NAME = ?";
try (Connection connection = getConnection();
     PreparedStatement ps = connection.prepareStatement(sql)) {	
    ps.setString(1, newName);
    ps.setString(2, oldName);
    ps.executeUpdate();
    connection.close();
    return true;
}
catch(SQLException e) {
    System.err.println(e);
    return false;
}
}
public boolean deleteSubCategory(String newName) {

String sql =
    "DELETE FROM SUBCATEGORY WHERE SUBCATEGORY_NAME = ?";
try (Connection connection = getConnection();
     PreparedStatement ps = connection.prepareStatement(sql)) {	
    ps.setString(1, newName);
    ps.executeUpdate();
    connection.close();
    return true;
}
catch(SQLException e) {
    System.err.println(e);
    return false;
}
}
    
}