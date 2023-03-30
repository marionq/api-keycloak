package mx.com.autofin.model;

import lombok.Data;

@Data
public class RolKeycloakAsignarRequest {
	
	private String id;
	private String name;
	
	public RolKeycloakAsignarRequest(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	

}
