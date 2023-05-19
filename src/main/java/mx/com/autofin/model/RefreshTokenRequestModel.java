package mx.com.autofin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RefreshTokenRequestModel {
    
    @JsonProperty("grant_type")
    private String grantType;
    
    @JsonProperty("client_id")
    private String clientId;
    
    @JsonProperty("client_secret")
    private String clientSecret;
    
    @JsonProperty("refresh_token")
    private String refreshToken;    
    
}
