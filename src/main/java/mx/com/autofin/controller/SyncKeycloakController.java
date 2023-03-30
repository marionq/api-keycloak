package mx.com.autofin.controller;

import mx.com.autofin.service.SyncKeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autofin/v1/keycloak/synckeycloakldap")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE, RequestMethod.OPTIONS})
public class SyncKeycloakController {

    @Autowired
    SyncKeycloakService syncKeycloakService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<Object> findRoleClient() {
        return syncKeycloakService.syncLdap();
    }

}
