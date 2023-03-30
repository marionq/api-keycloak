package mx.com.autofin.model;

import lombok.Data;

@Data
public class RolClientKeycloak {
	
	private String id;
	private String name;
	private String description;
	private String composite;
	private String clientRole;
	private String containerId;
}
