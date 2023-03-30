package mx.com.autofin.model;

import lombok.Data;

@Data
public class UserInfoKeycloak {
	
	private String sub;
	private String email_verified;
	private String name;
	private String preferred_username;
	private String given_name;
	private String family_name;
	private String email;

}
