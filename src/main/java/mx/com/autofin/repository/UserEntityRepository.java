package mx.com.autofin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.autofin.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, String> {

}
