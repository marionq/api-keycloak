package mx.com.autofin.model;

import lombok.Data;

@Data
public class SyncKeycloakModel {

    private String ignored;
    private String added;
    private String updated;
    private String removed;
    private String failed;
    private String status;

}
