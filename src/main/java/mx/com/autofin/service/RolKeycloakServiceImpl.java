package mx.com.autofin.service;

import java.util.List;
import mx.com.autofin.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import mx.com.autofin.model.RolClientKeycloak;
import mx.com.autofin.model.RolKeycloak;
import mx.com.autofin.model.RolKeycloakAsignar;
import mx.com.autofin.model.RolKeycloakAsignarRequest;
import mx.com.autofin.model.RolKeycloakDelete;
import mx.com.autofin.model.RolRealmKeycloak;
import mx.com.autofin.model.RolRealmKeycloakAsignarR1;
import mx.com.autofin.model.UserInfoKeycloak;
import mx.com.autofin.response.ResponseHandler;

@Service
public class RolKeycloakServiceImpl implements RolKeycloakService {

    @Autowired
    private RestTemplate clienteRest;

    @Autowired
    private EntityClientService entityClientService;

    @Value("${keycloak.base-uri.create}")
    private String uriCreate;

    @Value("${keycloak.base-uri.createR}")
    private String uriCreateR;

    @Value("${keycloak.base-uri.delete}")
    private String uriDelete;

    @Value("${keycloak.base-uri.deleteR}")
    private String uriDeleteR;

    @Value("${keycloak.base-uri.asignarrole1}")
    private String uriAsignar1;

    @Value("${keycloak.base-uri.asignarrole2}")
    private String uriAsignar2;

    @Value("${keycloak.base-uri.asignarroleR1}")
    private String uriAsignarR1;

    @Value("${keycloak.base-uri.asignarroleR2}")
    private String uriAsignarR2;

    @Value("${keycloak.base-uri.findRoleClient1}")
    private String uriRolClient1;

    @Value("${keycloak.base-uri.findRoleClient2}")
    private String uriRolClient2;

    @Value("${keycloak.base-uri.findAllRealmRol1}")
    private String uriRealmRole1;

    @Value("${keycloak.base-uri.findAllRealmRol2}")
    private String uriRealmRole2;

    @Value("${keycloak.base-uri.findRealmRolByName}")
    private String uriRealmRoleByName;

    @Override
    public ResponseEntity<Object> createClientRole(String token, RolKeycloak rolKeycloak) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);
        HttpEntity<?> request = new HttpEntity<Object>(rolKeycloak, headers);

        try {
            clienteRest.postForObject(uriCreate, request, RolKeycloak.class);
            return ResponseHandler.generateResponse("Rol Creado", HttpStatus.OK, request.getBody().toString());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0].toString())), rolKeycloak);
        }
    }

    @Override
    public ResponseEntity<Object> deleteClientRole(String token, RolKeycloakDelete rolKeycloak) {
        String nomRol = rolKeycloak.getName().toString();
        String url = uriDelete + nomRol;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        try {
            HttpEntity<?> request = new HttpEntity<Object>(null, headers);
            clienteRest.exchange(url, HttpMethod.DELETE, request, RolKeycloakDelete.class);
            return ResponseHandler.generateResponse("Rol Borrado", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0].toString())), rolKeycloak);
        }
    }

    @Override
    public ResponseEntity<Object> asignarClientRole(String token, RolKeycloakAsignar rolKeycloak) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);
        String uri = uriAsignar1 + rolKeycloak.getIdUser() + "/" + uriAsignar2 + rolKeycloak.getIdClient();
        RolKeycloakAsignarRequest[] req = new RolKeycloakAsignarRequest[]{new RolKeycloakAsignarRequest(rolKeycloak.getIdRole(), rolKeycloak.getNameRole())};

        //name = (String[])ArrayUtils.addAll(names, newNames);
        HttpEntity<?> request = new HttpEntity<Object>(req, headers);

        try {
            clienteRest.postForObject(uri, request, RolKeycloak.class);
            return ResponseHandler.generateResponse("Rol Asignado", HttpStatus.OK, rolKeycloak);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0].toString())), rolKeycloak);
        }
    }

    @Override
    public ResponseEntity<Object> borrarClientRole(String token, RolKeycloakAsignar rolKeycloak) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);
        String uri = uriAsignar1 + rolKeycloak.getIdUser() + uriAsignar2 + rolKeycloak.getIdClient();
        RolKeycloakAsignarRequest[] req = new RolKeycloakAsignarRequest[]{new RolKeycloakAsignarRequest(rolKeycloak.getIdRole(), rolKeycloak.getNameRole())};

        //name = (String[])ArrayUtils.addAll(names, newNames);
        HttpEntity<?> request = new HttpEntity<Object>(req, headers);

        try {
            clienteRest.exchange(uri, HttpMethod.DELETE, request, RolKeycloakDelete.class);
            return ResponseHandler.generateResponse("Rol Revocado", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0].toString())), rolKeycloak);
        }
    }

    /////////////////////////////////////////////
    @Override
    public ResponseEntity<Object> createRealmRole(String token, RolKeycloak rolKeycloak) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);
        HttpEntity<?> request = new HttpEntity<Object>(rolKeycloak, headers);

        try {
            RolKeycloak response = clienteRest.postForObject(uriCreateR, request, RolKeycloak.class);
            return ResponseHandler.generateResponse("Rol Creado", HttpStatus.OK, request.getBody().toString());

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0].toString())), rolKeycloak);
        }
    }

    @Override
    public ResponseEntity<Object> deleteRealmRole(String token, RolKeycloakDelete rolKeycloak) {
        String nomRol = rolKeycloak.getName().toString();
        String url = uriDeleteR + nomRol;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);
        HttpEntity<?> request = new HttpEntity<Object>(null, headers);

        try {

            clienteRest.exchange(url, HttpMethod.DELETE, request, RolKeycloakDelete.class);
            return ResponseHandler.generateResponse("Rol Borrado", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0].toString())), rolKeycloak);
        }
    }

    @Override
    public ResponseEntity<Object> asignarRealmRole(String token, RolRealmKeycloakAsignarR1 rolRealmKeycloakAsignarR1) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        String rolId = findRoleIdByRoleName(token, rolRealmKeycloakAsignarR1.getRoleName());        

        ResponseEntity<List<UserEntity>> findById = entityClientService.listRespEnt(rolRealmKeycloakAsignarR1.getUserName());

        //if (findById == null || findById.isEmpty()) {
        if (findById.getBody() == null || findById.getBody().isEmpty()) {
            return ResponseHandler.generateResponse("404 Not Found", HttpStatus.NOT_FOUND,
                    "404 Not Found: \"{\"error\":\"User not found\"}\"");
        } else {
            try {
                String uri = uriAsignarR1 + findById.getBody().get(0).getId().toString() + "/" + uriAsignarR2;
                RolKeycloakAsignarRequest[] req = new RolKeycloakAsignarRequest[]{new RolKeycloakAsignarRequest(rolId, rolRealmKeycloakAsignarR1.getRoleName())};
                HttpEntity<?> request = new HttpEntity<Object>(req, headers);
                clienteRest.postForObject(uri, request, RolKeycloak.class);
                return ResponseHandler.generateResponse("Rol Asignado", HttpStatus.OK, rolRealmKeycloakAsignarR1);

            } catch (Exception e) {
                return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0].toString())), e.getMessage());
            }
        }        
    }
    
    
    @Override
    public ResponseEntity<Object> borrarRealmRole(String token, RolRealmKeycloakAsignarR1 rolRealmKeycloakAsignarR1) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        String rolId = findRoleIdByRoleName(token, rolRealmKeycloakAsignarR1.getRoleName());        

        ResponseEntity<List<UserEntity>> findById = entityClientService.listRespEnt(rolRealmKeycloakAsignarR1.getUserName());

        //if (findById == null || findById.isEmpty()) {
        if (findById.getBody() == null || findById.getBody().isEmpty()) {
            return ResponseHandler.generateResponse("404 Not Found", HttpStatus.NOT_FOUND,
                    "404 Not Found: \"{\"error\":\"User not found\"}\"");
        } else {
            try {
                String uri = uriAsignarR1 + findById.getBody().get(0).getId().toString() + "/" + uriAsignarR2;
                RolKeycloakAsignarRequest[] req = new RolKeycloakAsignarRequest[]{new RolKeycloakAsignarRequest(rolId, rolRealmKeycloakAsignarR1.getRoleName())};
                HttpEntity<?> request = new HttpEntity<Object>(req, headers);
                clienteRest.exchange(uri, HttpMethod.DELETE, request, RolKeycloakDelete.class);
                return ResponseHandler.generateResponse("Rol Revocado", HttpStatus.OK, rolRealmKeycloakAsignarR1);

            } catch (Exception e) {
                return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0].toString())), e.getMessage());
            }
        }        
    }
    
    @Override
    public ResponseEntity<Object> findRoleClient(String authHeader, RolKeycloakAsignar rolClientKeycloak) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", authHeader);
        String uri = uriRolClient1 + rolClientKeycloak.getIdUser() + uriRolClient2;
        //String uri = "http://localhost:8080/auth/admin/realms/NAFIN/users/29edf08d-f6ff-40a9-a59f-803a77ca8d7e/role-mappings/clients/d5aab133-bf48-4374-9f88-ecf0b9747a85";

        HttpEntity<?> request = new HttpEntity<Object>(null, headers);

        try {
            ResponseEntity<RolClientKeycloak[]> response = clienteRest.exchange(uri, HttpMethod.GET, request, RolClientKeycloak[].class);
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, response);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0].toString())), e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> findRealmRole(String authHeader, String username) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", authHeader);

        HttpEntity<?> request = new HttpEntity<Object>(null, headers);

        ResponseEntity<List<UserEntity>> findById = entityClientService.listRespEnt(username);

        //if (findById == null || findById.isEmpty()) {
        if (findById.getBody() == null || findById.getBody().isEmpty()) {
            return ResponseHandler.generateResponse("404 Not Found", HttpStatus.NOT_FOUND,
                    "404 Not Found: \"{\"error\":\"User not found\"}\"");
        } else {
            try {
                String uri = uriRealmRole1 + findById.getBody().get(0).getId().toString() + uriRealmRole2;
                ResponseEntity<RolClientKeycloak[]> response = clienteRest.exchange(uri, HttpMethod.GET, request, RolClientKeycloak[].class);
                return ResponseHandler.generateResponse("OK", HttpStatus.OK, response.getBody());

            } catch (Exception e) {
                return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0].toString())), e.getMessage());
            }
        }
    }

    @Override
    public String findRoleIdByRoleName(String authHeader, String rolname) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", authHeader);

        HttpEntity<?> request = new HttpEntity<Object>(null, headers);

        try {
            String uri = uriRealmRoleByName + rolname;
            ResponseEntity<RolRealmKeycloak> response = clienteRest.exchange(uri, HttpMethod.GET, request, RolRealmKeycloak.class);
            String respuesta = response.getBody().getId();            
            return respuesta;

        } catch (Exception e) {            
            return "error";
        }
    }
}
