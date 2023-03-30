package mx.com.autofin.service;

import mx.com.autofin.model.SyncKeycloakModel;
import mx.com.autofin.model.TokenRequestModel;
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
public class SyncKeycloakServiceImpl implements SyncKeycloakService {

    @Autowired
    RestTemplate clienteRest;
    
    @Autowired
    TokenService tokenService;

    @Value("${keycloak.base-uri.syncLdap}")
    private String uriSyncLdap;

    @Value("${keycloak.token.username}")
    private String tokenUsername;

    @Value("${keycloak.token.password}")
    private String tokenPassword;

    @Value("${keycloak.token.clientId}")
    private String tokenClientInd;

    @Override
    public ResponseEntity<Object> syncLdap() {
        
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        //Llenado de model para requet
        TokenRequestModel tokenRequestModel = new TokenRequestModel();
        tokenRequestModel.setClientId(tokenClientInd);
        tokenRequestModel.setGrantType("password");
        tokenRequestModel.setPassword(tokenPassword);
        tokenRequestModel.setUsername(tokenUsername);

        //Obtiene el token para llamada a servicio keycloak
        String tokenAdmin = tokenService.getAccessToken(tokenRequestModel);
        //System.out.println("\ntokenAdmin: " + tokenAdmin);

        if (tokenAdmin.split(" ")[0].toString().equals("Error")) {
            return ResponseHandler.generateResponse("Método token Admin: " + tokenAdmin.split(" ")[3].toString(), HttpStatus.resolve(Integer.parseInt(tokenAdmin.split(" ")[2].toString())), tokenAdmin);
        } else {
            headers.add("Authorization", "Bearer " + tokenAdmin);
            HttpEntity<?> request = new HttpEntity<Object>(null, headers);

            try {
                SyncKeycloakModel response = clienteRest.postForObject(uriSyncLdap, request, SyncKeycloakModel.class);
                return ResponseHandler.generateResponse("OK", HttpStatus.OK, response);
            } catch (Exception e) {
                return ResponseHandler.generateResponse("Método Sessiones: " + e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0].toString())), e.getMessage());
            }
        }
    }

}
