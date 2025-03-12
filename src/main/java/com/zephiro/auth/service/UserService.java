package com.zephiro.auth.service;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zephiro.auth.repository.UserRepository;
import com.zephiro.auth.DTO.UserDTO;
import com.zephiro.auth.entity.Account;
import com.zephiro.auth.entity.UserEntity;
import com.zephiro.auth.security.JWTGenerator;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${SECRET_KEY}")
    private String secretKey;

    public Account login(UserDTO user) {
        try {
            String decryptedPassword = decryptPassword(user.getPassword());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getMail(), decryptedPassword));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String id = userRepository.findByMail(user.getMail()).getId();
            String name = userRepository.findByMail(user.getMail()).getName();
            String token = jwtGenerator.generateToken(authentication);

            return new Account(id, name, token);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials");
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during login");
        }
    }

    public void register(UserEntity user) {
        try {
            String decryptedPassword = decryptPassword(user.getPassword());
            user.setPassword(passwordEncoder.encode(decryptedPassword));
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during registration", e);
        }
    }

    private String decryptPassword(String encryptedPassword) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting password", e);
        }
    }
}
