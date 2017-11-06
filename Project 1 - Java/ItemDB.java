import java.util.*;
import java.sql.*;

public class ItemDB {
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

    public ArrayList<Item> getItem(int subCatID) {
        String sql = "SELECT ITEM_ID, ITEM_NAME, BRAND, AISLE, QUANTITY, ITEM_PRICE "
                   + "FROM ITEM WHERE SUBCATEGORY_ID = " + subCatID + " ORDER BY ITEM_ID ASC";        
        ArrayList<Item> item = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while(rs.next()) {
                int id = rs.getInt("ITEM_ID");
                String name = rs.getString("ITEM_NAME");
                String brand = rs.getString("BRAND");
                String aisle = rs.getString("AISLE");
                int quantity = rs.getInt("QUANTITY");
                double price = rs.getDouble("ITEM_PRICE");
                
                System.out.println(id + name + brand + aisle + quantity + " " + price);
                Item it = new Item(id, name, brand, aisle, quantity, price);
                item.add(it);
            }
            connection.close();
            return item;
        }
        catch(SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
public boolean addItem(String name, String brand, String aisle, int quantity, double price) {
    	
        String sql =
            "INSERT INTO ITEM (ITEM_ID, ITEM_NAME, BRAND, AISLE, QUANTITY, ITEM_PRICE) " +
            "VALUES (SEQ_ITEM.nextval, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {	
            ps.setString(1, name);
            ps.setString(2, brand);
            ps.setString(3, aisle);
            ps.setInt(4, quantity);
            ps.setDouble(5, price);
            ps.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
public boolean updateItem(String oldName, String name, String brand, String aisle, int quantity, double price) {

String sql =
    "UPDATE ITEM SET ITEM_NAME = ?, BRAND = ?, AISLE = ?, QUANTITY = ?, ITEM_PRICE = ? WHERE ITEM_NAME = ?";
try (Connection connection = getConnection();
     PreparedStatement ps = connection.prepareStatement(sql)) {	
    ps.setString(1, name);
    ps.setString(2, brand);
    ps.setString(3, aisle);
    ps.setInt(4, quantity);
    ps.setDouble(5, price);
    ps.setString(6, oldName);
    ps.executeUpdate();
    connection.close();
    return true;
}
catch(SQLException e) {
    System.err.println(e);
    return false;
}
}
public boolean deleteItem(String newName) {

String sql =
    "DELETE FROM ITEM WHERE ITEM_NAME = ?";
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