package mx.com.autofin.service;

import java.util.List;
import mx.com.autofin.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import mx.com.autofin.client.EntityClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityClientService implements EntityClient {
    
    @Autowired
    private EntityClient entityClient;

    @Override
    public ResponseEntity<List<UserEntity>> listRespEnt(String username) {
        return entityClient.listRespEnt(username);
    }
    
}
