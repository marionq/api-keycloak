package mx.com.autofin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenResponseModel {
    @JsonProperty("access_token")
    private String accessToken;
    
    @JsonProperty("expires_in")
    private String expiresIn;
    
    @JsonProperty("refresh_expires_in")
    private String refreshExpiresIn;
    
    @JsonProperty("refresh_token")
    private String refreshToken;
    
    @JsonProperty("token_type")
    private String tokenType;
    
    @JsonProperty("not-before-policy")
    private String notBeforePolicy;
    
    @JsonProperty("session_state")
    private String sessionState;
    
    private String scope;
}
