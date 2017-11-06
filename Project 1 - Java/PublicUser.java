
public class PublicUser extends User{
	private String name;
	private String address;
	private float phoneNumber;
	
	public PublicUser(String user, String pass, String n, String add, float phone){
		super(user, pass);
		name = n;
		address = add;
		phoneNumber = phone;	
	}
	public PublicUser(String n, String add, float phone){
		super();
		name = n;
		address = add;
		phoneNumber = phone;	
	}
	public void setName(String n){
		name = n;
	}
	public void setAddress(String add){
		address = add;
	}
	public void setPhoneNumber(float phone){
		phoneNumber = phone;
	}
	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}
	public float getPhoneNumber(){
		return phoneNumber;
	}
	public String toString(){
		return "Name: " + name + " Address: " + address + " Phone Number: " + phoneNumber;
	}
}
