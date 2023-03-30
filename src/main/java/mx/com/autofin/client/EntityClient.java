package mx.com.autofin.client;

import java.util.List;
import mx.com.autofin.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-keycloak")
public interface EntityClient {
    
    @GetMapping("/nafin/v1/userdbkeycloak/findbyusernameres/{username}")
    public ResponseEntity<List<UserEntity>> listRespEnt(@PathVariable String username);
}
