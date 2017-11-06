public class AdminUser extends User{
	private int userID = 1;
	public AdminUser(String user, String pass){
		super(user, pass);
		userID++;
	}
	public int getID(){
		return userID;
	}
}
