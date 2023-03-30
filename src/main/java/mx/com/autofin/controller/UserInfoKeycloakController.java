package mx.com.autofin.controller;

import mx.com.autofin.service.UserInfoKeycloakService;
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
public class UserInfoKeycloakController {
    
    @Autowired
    private UserInfoKeycloakService userInfoKeycloakService;
    
    @GetMapping(value = "/userinfo/usertoken", produces="application/json")
	public String userinfo(@RequestHeader("Authorization") String authHeader){
		return userInfoKeycloakService.userToken(authHeader);	
	}
    
}
