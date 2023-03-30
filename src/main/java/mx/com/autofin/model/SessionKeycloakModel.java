package mx.com.autofin.model;

import java.util.List;
import lombok.Data;

@Data
public class SessionKeycloakModel {
    
    private String id;
    private String username;
    private String userId;
    private String ipAddress;
    private Long start;
    private Long lastAccess;
    //private List<String> clients;
            
    
}
