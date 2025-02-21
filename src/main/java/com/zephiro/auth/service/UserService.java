package com.zephiro.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zephiro.auth.repository.UserRepository;
import com.zephiro.auth.model.UserEntity;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity findByMail(String mail) {
        return userRepository.findByMail(mail).orElse(null);
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }
}
