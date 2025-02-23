package com.zephiro.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zephiro.auth.DTO.UserDTO;
import com.zephiro.auth.model.UserEntity;
import com.zephiro.auth.security.JWTGenerator;
import com.zephiro.auth.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ToDo: Manejar la encriptación de la contraseña desde el front
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getMail(), user.getPassword()));
    
            SecurityContextHolder.getContext().setAuthentication(authentication);
    
            String token = jwtGenerator.generateToken(authentication);
    
            if (userService.findByMail(user.getMail()) == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("{\"error\": \"Authentication failed\"}");
            }
    
            // 🔥 Ahora devolvemos un JSON con el token
            return ResponseEntity.ok("{\"token\": \"" + token + "\"}");
    
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"Invalid credentials\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error occurred during login\"}");
        }
    }
    

    // ToDo: Manejar la encriptación de la contraseña desde el front
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            
            // 🔥 Ahora devolvemos JSON en vez de texto plano
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("{\"message\": \"User registered successfully\"}");
    
        } catch (Exception e) {
            System.out.println("Error:\n" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error occurred during registration\"}");
        }
    }
}
