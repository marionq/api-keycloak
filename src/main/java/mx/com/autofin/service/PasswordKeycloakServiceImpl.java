package mx.com.autofin.service;

import java.util.Base64;
import java.util.List;

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

import mx.com.autofin.entity.UserEntity;
import mx.com.autofin.model.PasswordKeycloak;
import mx.com.autofin.repository.UserEntityRepositoryCrud;
import mx.com.autofin.response.ResponseHandler;

@Service
public class PasswordKeycloakServiceImpl implements PasswordKeycloakService {

	@Autowired
	private RestTemplate clienteRest;
        
        @Autowired
        private EntityClientService entityClientService;

	@Autowired
	UserEntityRepositoryCrud userEntityRepositoryCrud;

	@Value("${keycloak.base-uri.changePassword1}")
	private String uriChangePassword1;

	@Value("${keycloak.base-uri.changePassword2}")
	private String uriChangePassword2;

	@Override
	public ResponseEntity<Object> changePassword(String authHeader, PasswordKeycloak passwordKeycloak) {

		// Se obtiene el Id-keycloak con el username (Por JPA)
		//List<UserEntity> findById = userEntityRepositoryCrud.findByUsername(passwordKeycloak.getId());
                
                // Se obtiene el Id-keycloak con el username (Cliente Feign)                
                ResponseEntity<List<UserEntity>> findById = entityClientService.listRespEnt(passwordKeycloak.getId().toString());

		//if (findById == null || findById.isEmpty()) {
                if (findById.getBody() == null || findById.getBody().isEmpty()) {
			return ResponseHandler.generateResponse("404 Not Found", HttpStatus.NOT_FOUND,
					"404 Not Found: \"{\"error\":\"User not found\"}\"");
		} else {
			// Se asigna el nuevo id-keycloak para el request de reseteo
			//passwordKeycloak.setId(findById.get(0).getId().toString());
                        passwordKeycloak.setId(findById.getBody().get(0).getId().toString());

			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("Authorization", authHeader);
			String uri = uriChangePassword1 + passwordKeycloak.getId() + uriChangePassword2;
			passwordKeycloak.setId(null);
			byte[] bytesDecodificados = Base64.getDecoder().decode(passwordKeycloak.getValue());
			String cadenaDecodificada = new String(bytesDecodificados);
			passwordKeycloak.setValue(cadenaDecodificada);

			HttpEntity<?> request = new HttpEntity<Object>(passwordKeycloak, headers);

			try {
				clienteRest.exchange(uri, HttpMethod.PUT, request, PasswordKeycloak.class);
				return ResponseHandler.generateResponse("Contraseña modificada", HttpStatus.OK, null);
			} catch (Exception e) {
				return ResponseHandler.generateResponse(e.getMessage().split(":")[0].toString(),
                                HttpStatus.resolve(Integer.parseInt(e.getMessage().split(" ")[0].toString())), e.getMessage());
			}
		}
	}
}
