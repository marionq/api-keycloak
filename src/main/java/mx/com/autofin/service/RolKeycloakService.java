package mx.com.autofin.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import mx.com.autofin.model.RolKeycloak;
import mx.com.autofin.model.RolKeycloakAsignar;
import mx.com.autofin.model.RolKeycloakDelete;
import mx.com.autofin.model.RolRealmKeycloak;
import mx.com.autofin.model.RolRealmKeycloakAsignarR1;

public interface RolKeycloakService {
	
	public ResponseEntity<Object> createClientRole(String authHeader, RolKeycloak rolKeycloak);
	public ResponseEntity<Object> deleteClientRole(String authHeader, RolKeycloakDelete rolKeycloak);
	public ResponseEntity<Object> asignarClientRole(String authHeader, RolKeycloakAsignar rolKeycloak);
	public ResponseEntity<Object> borrarClientRole(String authHeader, RolKeycloakAsignar rolKeycloak);
	
	public ResponseEntity<Object> createRealmRole(String authHeader, RolKeycloak rolKeycloak);
	public ResponseEntity<Object> deleteRealmRole(String authHeader, RolKeycloakDelete rolKeycloak);
	public ResponseEntity<Object> asignarRealmRole(String authHeader, RolRealmKeycloakAsignarR1 rolRealmKeycloakAsignarR1);
	public ResponseEntity<Object> borrarRealmRole(String authHeader, RolRealmKeycloakAsignarR1 rolRealmKeycloakAsignarR1);
	
        public ResponseEntity<Object> findRealmRole (String authHeader, String username);
        public String findRoleIdByRoleName (String authHeader, String roleName);
        
	public ResponseEntity<Object> findRoleClient (String authHeader, RolKeycloakAsignar rolClientKeycloak);
}
