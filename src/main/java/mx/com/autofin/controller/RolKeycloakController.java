package mx.com.autofin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.autofin.model.RolKeycloak;
import mx.com.autofin.model.RolKeycloakAsignar;
import mx.com.autofin.model.RolKeycloakDelete;
import mx.com.autofin.model.RolRealmKeycloak;
import mx.com.autofin.model.RolRealmKeycloakAsignarR1;
import mx.com.autofin.service.RolKeycloakService;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/autofin/v1/roleskeycloak")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
RequestMethod.DELETE, RequestMethod.OPTIONS })
public class RolKeycloakController {
	
	@Autowired
	private RolKeycloakService rolKeycloakService;
	
	
	//Role Client
	
	@PostMapping(value = "/clientrole/crear", produces="application/json")
	public ResponseEntity<Object> crearRol(@RequestHeader("Authorization") String authHeader, @RequestBody RolKeycloak rolKeycloak){
		return rolKeycloakService.createClientRole(authHeader, rolKeycloak);	
	}
	
	@DeleteMapping(value = "/clientrole/borrar", produces="application/json")
	public ResponseEntity<Object> borrarRol(@RequestHeader("Authorization") String authHeader, @RequestBody RolKeycloakDelete rolKeycloak){
		return rolKeycloakService.deleteClientRole(authHeader, rolKeycloak);	
	}
	
	@PostMapping(value = "/clientrole/asignar", produces="application/json")
	public ResponseEntity<Object> asignarRol(@RequestHeader("Authorization") String authHeader, @RequestBody RolKeycloakAsignar rolKeycloak){
		return rolKeycloakService.asignarClientRole(authHeader, rolKeycloak);	
	}
	
	@DeleteMapping(value = "/clientrole/quitar", produces="application/json")
	public ResponseEntity<Object> quitarRol(@RequestHeader("Authorization") String authHeader, @RequestBody RolKeycloakAsignar rolKeycloak){
		return rolKeycloakService.borrarClientRole(authHeader, rolKeycloak);	
	}
	
	@PostMapping(value = "/clientrole/findbyuser", produces="application/json")
	public ResponseEntity<Object> findRoleClient(@RequestHeader("Authorization") String authHeader, @RequestBody RolKeycloakAsignar rolKeycloak){
		return rolKeycloakService.findRoleClient(authHeader, rolKeycloak);	
	}
	
	//Role Realm
	@PostMapping(value = "/realmrole/crear", produces="application/json")
	public ResponseEntity<Object> crearRolRealm(@RequestHeader("Authorization") String authHeader, @RequestBody RolKeycloak rolKeycloak){
		return rolKeycloakService.createRealmRole(authHeader, rolKeycloak);	
	}
	
	@DeleteMapping(value = "/realmrole/borrar", produces="application/json")
	public ResponseEntity<Object> deleteRolRealm(@RequestHeader("Authorization") String authHeader, @RequestBody RolKeycloakDelete rolKeycloak){
		return rolKeycloakService.deleteRealmRole(authHeader, rolKeycloak);	
	}
	
	@PostMapping(value = "/realmrole/asignar", produces="application/json")
	public ResponseEntity<Object> asignarRealmRol(@RequestHeader("Authorization") String authHeader, @RequestBody RolRealmKeycloakAsignarR1 rolRealmKeycloakAsignarR1){
		return rolKeycloakService.asignarRealmRole(authHeader, rolRealmKeycloakAsignarR1);	
	}
	
	@DeleteMapping(value = "/realmrole/quitar", produces="application/json")
	public ResponseEntity<Object> quitarRealmRol(@RequestHeader("Authorization") String authHeader, @RequestBody RolRealmKeycloakAsignarR1 rolRealmKeycloakAsignarR1){
		return rolKeycloakService.borrarRealmRole(authHeader, rolRealmKeycloakAsignarR1);	
	}
        
        @GetMapping(value = "/realmrole/findbyusername/{username}", produces="application/json")
	public ResponseEntity<Object> findRealRole(@RequestHeader("Authorization") String authHeader, @PathVariable String username){
		return rolKeycloakService.findRealmRole(authHeader, username);	
	}
        
        @GetMapping(value = "/realmrole/findrolidbyusername/{rolname}", produces="application/json")
	public String findRealRoleByRoleName(@RequestHeader("Authorization") String authHeader, @PathVariable String rolname){
		return rolKeycloakService.findRoleIdByRoleName(authHeader, rolname);	
	}

}
