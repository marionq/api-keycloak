package mx.com.autofin.controller;

import mx.com.autofin.model.TokenRequestModel;
import mx.com.autofin.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autofin/v1/token")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
RequestMethod.DELETE, RequestMethod.OPTIONS })
public class TokenController {
    
    @Autowired
    private TokenService tokenService;
    
    @PostMapping(value = "/gettoken", produces="application/json")
	public String getAccessToken(@RequestBody TokenRequestModel tokenRequestModel){            
            return tokenService.getAccessToken(tokenRequestModel);	
	}
    
}
