package mx.com.autofin.service;

import mx.com.autofin.model.TokenRequestModel;

public interface TokenService {
    
    public String getAccessToken(TokenRequestModel tokenRequestModel);
    
    
}
