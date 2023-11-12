package efV2;

//Kendra Newman
public class Users {
	private String name; 
	private String email;
	private String username;
	private String password;
	private int userCode;
	
	public Users(String name, String email, String username, String password, int userCode) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.userCode = userCode;
		
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public int getCode() {
		return userCode;
	}
}