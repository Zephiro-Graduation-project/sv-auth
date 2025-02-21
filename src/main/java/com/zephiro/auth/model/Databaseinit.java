package com.zephiro.auth.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.zephiro.auth.repository.UserRepository;

@Controller
@Profile("default")
public class Databaseinit implements ApplicationRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Fecha para inicializar la base de datos
        String date = "19-11-2003";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate dateFormat = LocalDate.parse(date, formatter);

        // Contraseña encriptada
        String password = "12345678";
        String passwordEncoded = passwordEncoder.encode(password);

        userRepository.save(new UserEntity("Alejandro Suarez", "alejandrosuarezacosta@javeriana.edu.co", passwordEncoded, dateFormat));
    }
    
}
