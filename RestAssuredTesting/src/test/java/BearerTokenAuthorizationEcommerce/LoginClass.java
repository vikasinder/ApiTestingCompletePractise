package BearerTokenAuthorizationEcommerce;

public class LoginClass {
	
	// Class used for Serialization of object data to be sent across network

	// because this the data that needs to be sent in post request - body as payload
	private String userEmail;
	private String userPassword;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
