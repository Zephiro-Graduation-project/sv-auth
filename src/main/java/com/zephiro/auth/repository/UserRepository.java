package com.zephiro.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zephiro.auth.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findByMail(String mail);
    Boolean existsByMail(String mail);
    @Query("SELECT u.id FROM UserEntity u WHERE u.mail = :mail")
    Long findIdByMail(String mail);
    @Query("SELECT u.name FROM UserEntity u WHERE u.mail = :mail")
    String findNameByMail(String mail);
}