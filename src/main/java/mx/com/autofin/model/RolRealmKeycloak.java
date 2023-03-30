package mx.com.autofin.model;

import java.util.List;
import lombok.Data;

@Data
public class RolRealmKeycloak {

    private String id;
    private String name;
    private String description;
    private String composite;
    private String clientRole;
    private String containerId;

}
