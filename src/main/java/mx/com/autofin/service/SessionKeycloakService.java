package mx.com.autofin.service;

import org.springframework.http.ResponseEntity;

public interface SessionKeycloakService {
    
    public ResponseEntity<Object> userSessions (String token);
    
}
