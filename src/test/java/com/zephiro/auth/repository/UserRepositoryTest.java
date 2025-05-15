package com.zephiro.auth.repository;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import com.zephiro.auth.entity.UserEntity;
 
@Profile("dev")
@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    public void new_user(){
        // Arrange
        UserEntity user = new UserEntity("Usuario Pruebas", "pruebas@mail.com", "12345678", LocalDate.now());
        
        // Act
        UserEntity saved = userRepository.save(user);

        // Assert
        Assertions.assertThat(saved).isNotNull();
    }

    @Test
    public void modify_user() {
        // Arrange
        UserEntity userToUpdate = userRepository.findByMail("pruebas@mail.com");

        // Act
        userToUpdate.setName("Usuario Modificado");
        UserEntity updated = userRepository.save(userToUpdate);

        // Assert
        Assertions.assertThat(updated).isNotNull();
        Assertions.assertThat(updated.getName()).isEqualTo("Usuario Modificado");
    }

    @Test
    public void delete_user() {
        // Arrange
        UserEntity userToDelete = userRepository.findByMail("pruebas@mail.com");

        // Act
        userRepository.delete(userToDelete);
        UserEntity deletedUser = userRepository.findByMail("pruebas@mail.com");

        // Assert
        Assertions.assertThat(deletedUser).isNull();
    }
}