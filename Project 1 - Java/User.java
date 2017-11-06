public class User {
	int userID = 0;
	String username;
	String password;
	
	public User(){
		
	}
	public User(String user, String pass){
		username = user;
		password = pass;
		userID++;
	}
	public User(int id, String user, String pass){
		userID= id;
		username = user;
		password = pass;
	}
	public int getID(){
		return userID;
	}
	
	public void setUsername(String user){
		username = user;
	}
	public void setPassword(String pass){
		password = pass;
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public String toString(){
		return "Username: " + username + " Password: " + password;
	}

}
