package mx.com.autofin.service;

import mx.com.autofin.model.SyncKeycloakModel;
import org.springframework.http.ResponseEntity;

public interface SyncKeycloakService {
    
    public ResponseEntity<Object> syncLdap ();
    
}
