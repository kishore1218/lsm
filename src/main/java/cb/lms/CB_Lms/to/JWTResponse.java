package cb.lms.CB_Lms.to;

import java.io.Serializable;

public class JWTResponse implements Serializable{
	
	
	public JWTResponse(String authToken,String refreshToken) {
		
		this.authToken=authToken;
		this.refreshToken=refreshToken;
	}
	
	
	String authToken;
	
	String refreshToken;
	

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	

}
