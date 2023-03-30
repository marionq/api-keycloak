package mx.com.autofin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import mx.com.autofin.entity.UserEntity;

public interface UserEntityRepositoryCrud extends CrudRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {
	
	List<UserEntity> findByUsername(String username);

}
