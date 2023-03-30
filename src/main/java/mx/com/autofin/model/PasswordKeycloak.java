package mx.com.autofin.model;

import lombok.Data;

@Data
public class PasswordKeycloak {
    
    private String id;
    private String type;
    private String temporary;
    private String value;
    
}
