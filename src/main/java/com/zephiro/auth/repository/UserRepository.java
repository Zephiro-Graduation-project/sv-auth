package com.zephiro.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zephiro.auth.entity.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    
    UserEntity findByMail(String mail);
    Boolean existsByMail(String mail);

}