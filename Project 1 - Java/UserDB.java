import java.util.*;
import java.sql.*;

public class UserDB {
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

    public ArrayList<User> getUsers() {
        String sql = "SELECT USER_ID, USERNAME, PASSWORD "
                   + "FROM USERS ORDER BY USER_ID ASC";        
        ArrayList<User> users = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while(rs.next()) {
                int id = rs.getInt("USER_ID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");

                System.out.println(id + username + password);
                User user = new User(id, username, password);
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
    public int getID(String userName, String passWord){
    	String sql = "SELECT USER_ID "
                + "FROM USERS WHERE USERNAME = '" + userName + "' AND PASSWORD = '" + passWord + "' ORDER BY USER_ID ASC";        
    	int id = 0;
     
     try (Connection connection = getConnection();
          PreparedStatement ps = connection.prepareStatement(sql);
          ResultSet rs = ps.executeQuery()) {
     	while(rs.next()) {
             id = rs.getInt("USER_ID");

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
    
public boolean addUser(String userName, String passWord) {
    	
        String sql =
            "INSERT INTO USERS (USER_ID, USERNAME, PASSWORD) " +
            "VALUES (SEQ_USER.nextval, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {	
            ps.setString(1, userName);
            ps.setString(2, passWord);
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