package mx.com.autofin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenRequestModel {
    
    @JsonProperty("grant_type")
    private String grantType;
    
    @JsonProperty("client_id")
    private String clientId;
    
    private String username;
    
    private String password;
    
}
