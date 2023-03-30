package mx.com.autofin.service;

import org.springframework.http.ResponseEntity;

import mx.com.autofin.model.PasswordKeycloak;

public interface PasswordKeycloakService {
	
	public ResponseEntity<Object> changePassword(String authHeader, PasswordKeycloak passwordKeycloak);

}
