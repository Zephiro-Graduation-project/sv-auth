package com.zephiro.auth.service;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zephiro.auth.DTO.UserDTO;
import com.zephiro.auth.entity.Account;
import com.zephiro.auth.entity.UserEntity;
import com.zephiro.auth.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // ToDo: Add credentials when testing but don't commit them
    String mail = "";
    String password = "";

    @Test
    public void testLogin() {
        // Arrange
        UserDTO user = new UserDTO(mail, password);

        // Act
        Account account = userService.login(user);

        // Assert
        Assertions.assertThat(account).isNotNull();    
    }

    @Test
    public void testRegister() {
        // Arrange
        UserEntity user1 = new UserEntity("Usuario Pruebas", "pruebas@mail.com", password, LocalDate.now());
        UserEntity user2 = new UserEntity("Usuario Duplicado", "pruebas@mail.com", password, LocalDate.now());

        String exc = "";
        
        // Act
        userService.register(user1);
        UserEntity newUser = userRepository.findByMail(user1.getMail());

        try {
            userService.register(user2);
        } catch (Exception e) {
            exc = e.getMessage();
        }

        // Assert
        Assertions.assertThat(newUser).isNotNull();
        Assertions.assertThat(exc).isEqualTo("Email already in use");
    }

    @Test
    public void deleteAccount() {
        // Arrange
        UserEntity user = userRepository.findByMail(mail);
        String id = user.getId();

        // Act
        userService.deleteAccount(id, user.getMail());
        UserEntity deletedUser = userRepository.findByMail(user.getMail());

        // Assert
        Assertions.assertThat(deletedUser).isNull();
    }
}
