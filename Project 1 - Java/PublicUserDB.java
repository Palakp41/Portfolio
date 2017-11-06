import java.util.*;
import java.sql.*;

public class PublicUserDB {
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

    public ArrayList<PublicUser> getUsersInfo() {
        String sql = "SELECT USER_ID, NAME, ADDRESS, PHONE "
                   + "FROM PUBLICUSER ORDER BY USER_ID ASC";        
        ArrayList<PublicUser> users = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while(rs.next()) {
                int id = rs.getInt("USER_ID");
                String name = rs.getString("NAME");
                String address = rs.getString("ADDRESS");
                float phone = rs.getFloat("PHONE");

                System.out.println(id + name + address + phone);
                PublicUser user = new PublicUser(name, address, phone);
                users.add(user);
            }
            connection.close();
            return users;
        }
        catch(SQLException e) {
            System.err.println(e);
            return null;
        }
    }
public boolean addPublicUser(int id, String name, String address, float phoneNumber) {
    	
        String sql =
            "INSERT INTO PUBLICUSER (USER_ID, NAME, ADDRESS, PHONE ) " +
            "VALUES (" + id + ", ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {	
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setFloat(3, phoneNumber);
            ps.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
public boolean addToCart(int itemId){
	String sql1 = 
			"DELETE FROM CART";
	try (Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql1)) {	
           ps.executeUpdate();
           connection.close();
       }
       catch(SQLException e) {
           System.err.println(e);
       }
	String sql =
            "INSERT INTO CART (USER_ID, ITEM_ID ) " +
            "VALUES (" + 1 + ", " + itemId + ")";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {	
            ps.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
}
public boolean addToWishList(int itemId){
	String sql =
            "INSERT INTO WISHLIST (USER_ID, ITEM_ID ) " +
            "VALUES (" + 1 + ", " + itemId + ")";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {	
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