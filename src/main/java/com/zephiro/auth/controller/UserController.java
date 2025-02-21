package com.zephiro.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zephiro.auth.DTO.UserDTO;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getMail(), user.getPassword()));
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            String token = jwtGenerator.generateToken(authentication);

            if (userService.findByMail(user.getMail()) == null) {
                return new ResponseEntity<>("Authentication failed", HttpStatus.FORBIDDEN);
            }

            return new ResponseEntity<>(token, HttpStatus.OK);

        } catch (AuthenticationException e) {
            // Manejar fallo de autenticación
            System.out.println("Error:\n" + e);
            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            // Manejar otros errores
            System.out.println("Error:\n" + e);
            return new ResponseEntity<>("Error occurred during login", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
