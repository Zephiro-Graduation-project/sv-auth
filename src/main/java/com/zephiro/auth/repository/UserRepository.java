package com.zephiro.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zephiro.auth.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findByMail(String mail);
    Boolean existsByMail(String mail);

}