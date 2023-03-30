package mx.com.autofin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.autofin.entity.UserEntity;
import mx.com.autofin.repository.UserEntityRepository;
import mx.com.autofin.repository.UserEntityRepositoryCrud;
import mx.com.autofin.response.ResponseHandler;

@RestController
@RequestMapping("/autofin/v1/userdbkeycloak")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE, RequestMethod.OPTIONS})
public class UserEntityController {

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    UserEntityRepositoryCrud userEntityRepositoryCrud;

    @GetMapping()
    public ResponseEntity<Object> list() {
        List<UserEntity> findAll = userEntityRepository.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseHandler.generateResponse("", HttpStatus.NO_CONTENT, null);
        } else {
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, findAll);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> get(@PathVariable String username) {
        List<UserEntity> findAll = userEntityRepositoryCrud.findByUsername(username);
        if (findAll == null || findAll.isEmpty()) {
            return ResponseHandler.generateResponse("", HttpStatus.NO_CONTENT, null);
        } else {
            return ResponseHandler.generateResponse("OK", HttpStatus.OK, findAll);
        }

    }

    @GetMapping("/findbyusernameres/{username}")
    public ResponseEntity<List<UserEntity>> listRespEnt(@PathVariable String username) {
        List<UserEntity> findByUsername = userEntityRepositoryCrud.findByUsername(username);
        if (findByUsername == null || findByUsername.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(findByUsername);
        }
    }

}
