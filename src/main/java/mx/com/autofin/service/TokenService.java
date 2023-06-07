package mx.com.autofin.service;

import mx.com.autofin.model.InstrospectTokenRequestModel;
import mx.com.autofin.model.RefreshTokenLogoutRequestModel;
import mx.com.autofin.model.RefreshTokenRequestModel;
import mx.com.autofin.model.TokenRequestModel;
import org.springframework.http.ResponseEntity;

public interface TokenService {
    
    public String getAccessToken(TokenRequestModel tokenRequestModel);
    
    public ResponseEntity<Object> getAccessTokenCp(TokenRequestModel tokenRequestModel);
    
    public ResponseEntity<Object> refreshAccessToken(RefreshTokenRequestModel refreshTokenRequestModel);
    
    public ResponseEntity<Object> logoutToken(RefreshTokenLogoutRequestModel refreshTokenLogoutRequestModel);
    
    public ResponseEntity<Object> introstecToken(InstrospectTokenRequestModel instrospectTokenRequestModel);
    
    
}
