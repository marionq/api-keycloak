package mx.com.autofin.service;

import java.util.List;
import mx.com.autofin.entity.UserEntity;
import mx.com.autofin.model.SessionKeycloakModel;
import mx.com.autofin.model.TokenRequestModel;
import mx.com.autofin.repository.UserEntityRepositoryCrud;
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
public class SessionKeycloakServiceImpl implements SessionKeycloakService {

    @Autowired
    RestTemplate clienteRest;

    @Autowired
    UserInfoKeycloakService userInfoKeycloakService;

    @Autowired
    UserEntityRepositoryCrud userEntityRepositoryCrud;

    @Autowired
    TokenService tokenService;

    @Value("${keycloak.base-uri.sessions1}")
    private String uriSession1;

    @Value("${keycloak.base-uri.sessions2}")
    private String uriSession2;

    @Value("${keycloak.token.username}")
    private String tokenUsername;

    @Value("${keycloak.token.password}")
    private String tokenPassword;

    @Value("${keycloak.token.clientId}")
    private String tokenClientInd;

    @Override
    public ResponseEntity<Object> userSessions(String token) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        //Se obtiene el username con el token del usuario
        String username = userInfoKeycloakService.userToken(token);
        //System.out.println("\nUserName: " + username);
        if (username.split(" ")[0].toString().equals("Error")) {
            return ResponseHandler.generateResponse("Método getUser: " + username.split(" ")[4].toString(), HttpStatus.resolve(Integer.parseInt(username.split(" ")[2].toString())), username);
        } else {
            //Se obtiene el userId del usuario mediante el username
            List<UserEntity> listUserId = userEntityRepositoryCrud.findByUsername(username);
            String userId = listUserId.get(0).getId().toString();
            //System.out.println("\nUserId: " + userId);

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
                    String uri = uriSession1 + userId + uriSession2;
                    ResponseEntity<SessionKeycloakModel[]> response = clienteRest.exchange(uri, HttpMethod.GET, request, SessionKeycloakModel[].class);
                    return ResponseHandler.generateResponse("Sesiones usuario " + username, HttpStatus.OK, response.getBody());
                } catch (Exception e) {
                    return ResponseHandler.generateResponse("Método Sessiones: " + e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0].toString())), e.getMessage());
                }
            }
        }
    }
}