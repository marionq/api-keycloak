package mx.com.autofin.service;

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
import org.springframework.web.client.RestTemplate;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RestTemplate clienteRest;

    @Value("${keycloak.base-uri.accessToken}")
    private String uriAccessToken;

    @Override
    public String getAccessToken(TokenRequestModel tokenRequestModel) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/x-www-form-urlencoded");               
        String stringRequest = "grant_type=" + tokenRequestModel.getGrantType() + "&client_id=" + tokenRequestModel.getClientId() + "&username=" + tokenRequestModel.getUsername() + "&password=" + tokenRequestModel.getPassword();
        //String stringRequest = "grant_type=password&client_id=front-end-94&username=adminapi&password=Fatguys8";
        
        HttpEntity<?> request = new HttpEntity<Object>(stringRequest, headers);

        try {
            TokenResponseModel response = clienteRest.postForObject(uriAccessToken, request, TokenResponseModel.class);
            return response.getAccessToken();
        } catch (Exception e) {
            return "Error message: " + e.getMessage().toString();
        }
    }
}
