package mx.com.autofin.service;

import mx.com.autofin.model.RefreshTokenRequestModel;
import mx.com.autofin.model.TokenRequestModel;
import org.springframework.http.ResponseEntity;

public interface TokenService {
    
    public String getAccessToken(TokenRequestModel tokenRequestModel);
    
    public ResponseEntity<Object> getAccessTokenCp(TokenRequestModel tokenRequestModel);
    
    public ResponseEntity<Object> refreshAccessToken(RefreshTokenRequestModel refreshTokenRequestModel);
    
    
}
