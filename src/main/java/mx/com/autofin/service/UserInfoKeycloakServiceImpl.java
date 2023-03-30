package mx.com.autofin.service;

import mx.com.autofin.model.UserInfoKeycloak;
import mx.com.autofin.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class UserInfoKeycloakServiceImpl implements UserInfoKeycloakService {
    
    @Autowired
    private RestTemplate clienteRest;
    
    @Value("${keycloak.base-uri.userinfo}")
    private String uriUserInfo;
    
    @Override
    public String userToken(String authHeader) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", authHeader);

        HttpEntity<?> request = new HttpEntity<Object>(null, headers);

        try {
            ResponseEntity<UserInfoKeycloak> response = clienteRest.exchange(uriUserInfo, HttpMethod.GET, request, UserInfoKeycloak.class);
            String userName = response.getBody().getPreferred_username();
            return userName;
        } catch (Exception e) {
            return "Error message: " + e.getMessage().toString();
        }
    }
}
