package mx.com.autofin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.autofin.model.PasswordKeycloak;
import mx.com.autofin.service.PasswordKeycloakService;

@RestController
@RequestMapping("/autofin/v1/userkeycloak")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ChangePasswordKeycloackController {
    
    @Autowired
    PasswordKeycloakService passwordKeycloakService;
    
    @PutMapping(value = "/resetpasswd", produces="application/json")
	public ResponseEntity<Object> findRoleClient(@RequestHeader("Authorization") String authHeader, @RequestBody PasswordKeycloak passwordKeycloak){
		return passwordKeycloakService.changePassword(authHeader, passwordKeycloak);	
	}
    
}
