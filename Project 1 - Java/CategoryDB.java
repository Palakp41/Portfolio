import java.util.*;
import java.sql.*;

public class CategoryDB {
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

    public ArrayList<Category> getCategory() {
        String sql = "SELECT CATEGORY_ID, CATEGORY_NAME "
                   + "FROM CATEGORY ORDER BY CATEGORY_ID ASC";        
        ArrayList<Category> category = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while(rs.next()) {
                int id = rs.getInt("CATEGORY_ID");
                String name = rs.getString("CATEGORY_NAME");

                System.out.println(id + name);
                Category cat = new Category(id, name);
                category.add(cat);
            }
            connection.close();
            return category;
        }
        catch(SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
public boolean addCategory(String name) {
    	
        String sql =
            "INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME) " +
            "VALUES (SEQ_CAT.nextval, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {	
            ps.setString(1, name);
            ps.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
public boolean updateCategory(String oldName, String newName) {
	
    String sql =
        "UPDATE CATEGORY SET CATEGORY_NAME = ? WHERE CATEGORY_NAME = ?";
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
public boolean deleteCategory(String newName) {
	
    String sql =
        "DELETE FROM CATEGORY WHERE CATEGORY_NAME = ?";
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