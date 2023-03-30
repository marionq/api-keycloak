package mx.com.autofin.controller;

import mx.com.autofin.service.SessionKeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autofin/v1/keycloak")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
RequestMethod.DELETE, RequestMethod.OPTIONS })
public class SessionKeycloakController {
    
    @Autowired
    SessionKeycloakService sessionKeycloakService;
    
    @GetMapping(value = "/sesionesusuario", produces="application/json")
	public ResponseEntity<Object> getUserSession(@RequestHeader("Authorization") String token){            
            return sessionKeycloakService.userSessions(token);	
	}
    
}
