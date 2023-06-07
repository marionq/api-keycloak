package mx.com.autofin.service;

import mx.com.autofin.model.InstrospectTokenRequestModel;
import mx.com.autofin.model.InstrospectTokenResponseModel;
import mx.com.autofin.model.RefreshTokenLogoutRequestModel;
import mx.com.autofin.model.RefreshTokenRequestModel;
import mx.com.autofin.model.TokenRequestModel;
import mx.com.autofin.model.TokenResponseModel;
import mx.com.autofin.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RestTemplate clienteRest;

    @Value("${keycloak.base-uri.accessToken}")
    private String uriAccessToken;
    
    @Value("${keycloak.base-uri.logoutToken}")
    private String uriLogoutToken;
    
    @Value("${keycloak.base-uri.uriIntrospectToken}")
    private String uriIntrospectToken;
    

    @Override
    public String getAccessToken(TokenRequestModel tokenRequestModel) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/x-www-form-urlencoded");               
        String stringRequest = "grant_type=" + tokenRequestModel.getGrantType() + "&client_id=" + tokenRequestModel.getClientId() + "&username=" + tokenRequestModel.getUsername() + "&password=" + tokenRequestModel.getPassword() + "&client_secret=" + tokenRequestModel.getClientSecret();
        //String stringRequest = "grant_type=password&client_id=front-end-94&username=adminapi&password=Fatguys8";
        
        HttpEntity<?> request = new HttpEntity<Object>(stringRequest, headers);

        try {
            TokenResponseModel response = clienteRest.postForObject(uriAccessToken, request, TokenResponseModel.class);
            return response.getAccessToken();
        } catch (RestClientException e) {
            return "Error message: " + e.getMessage();
        }
    }
    
    @Override
    public ResponseEntity<Object> getAccessTokenCp(TokenRequestModel tokenRequestModel) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/x-www-form-urlencoded");               
        String stringRequest = "grant_type=" + tokenRequestModel.getGrantType() + "&client_id=" + tokenRequestModel.getClientId() + "&username=" + tokenRequestModel.getUsername() + "&password=" + tokenRequestModel.getPassword() + "&client_secret=" + tokenRequestModel.getClientSecret();
        //String stringRequest = "grant_type=password&client_id=front-end-94&username=adminapi&password=Fatguys8";
        
        HttpEntity<?> request = new HttpEntity<Object>(stringRequest, headers);
        
        try {
            TokenResponseModel tokenResponseModel = clienteRest.postForObject(uriAccessToken, request, TokenResponseModel.class);
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, tokenResponseModel);
        } catch (RestClientException e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0])), tokenRequestModel);
        }
    }
    
    @Override
    public ResponseEntity<Object> refreshAccessToken(RefreshTokenRequestModel refreshTokenRequestModel) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/x-www-form-urlencoded");               
        String stringRequest = "grant_type=" + refreshTokenRequestModel.getGrantType() + "&client_id=" + refreshTokenRequestModel.getClientId() + "&refresh_token=" + refreshTokenRequestModel.getRefreshToken() + "&client_secret=" + refreshTokenRequestModel.getClientSecret();
        //String stringRequest = "grant_type=password&client_id=front-end-94&username=adminapi&password=Fatguys8";
        
        HttpEntity<?> request = new HttpEntity<Object>(stringRequest, headers);
        
        try {
            TokenResponseModel tokenResponseModel = clienteRest.postForObject(uriAccessToken, request, TokenResponseModel.class);
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, tokenResponseModel);
        } catch (RestClientException e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0])), refreshTokenRequestModel);
        }
    }
    
    @Override
    public ResponseEntity<Object> logoutToken(RefreshTokenLogoutRequestModel refreshTokenLogoutRequestModel) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/x-www-form-urlencoded");               
        String stringRequest = "client_id=" + refreshTokenLogoutRequestModel.getClientId() + "&refresh_token=" + refreshTokenLogoutRequestModel.getRefreshToken() + "&client_secret=" + refreshTokenLogoutRequestModel.getClientSecret();
        //String stringRequest = "grant_type=password&client_id=front-end-94&username=adminapi&password=Fatguys8";
        
        HttpEntity<?> request = new HttpEntity<Object>(stringRequest, headers);
        
        try {
            TokenResponseModel tokenResponseModel = clienteRest.postForObject(uriLogoutToken, request, TokenResponseModel.class);
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, tokenResponseModel);
        } catch (RestClientException e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0])), refreshTokenLogoutRequestModel);
        }
    }
    
    
    @Override
    public ResponseEntity<Object> introstecToken(InstrospectTokenRequestModel instrospectTokenRequestModel) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/x-www-form-urlencoded");               
        String stringRequest = "client_id=" + instrospectTokenRequestModel.getClientId() + "&token=" + instrospectTokenRequestModel.getToken() + "&client_secret=" + instrospectTokenRequestModel.getClientSecret();
        //String stringRequest = "grant_type=password&client_id=front-end-94&username=adminapi&password=Fatguys8";
        
        HttpEntity<?> request = new HttpEntity<Object>(stringRequest, headers);
        
        try {
            InstrospectTokenResponseModel instrospectTokenResponseModel = clienteRest.postForObject(uriIntrospectToken, request, InstrospectTokenResponseModel.class);
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, instrospectTokenResponseModel);
        } catch (RestClientException e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0])), instrospectTokenRequestModel);
        }
    }
}
