package mx.com.autofin.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "USER_ENTITY")
public class UserEntity {
	
	@Id
        @Column(name = "ID")
	private String id;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "ENABLED")
	private boolean enabled;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "REALM_ID")
	private String realmId;
	
	@Column(name = "USERNAME")
	private String username;

}
