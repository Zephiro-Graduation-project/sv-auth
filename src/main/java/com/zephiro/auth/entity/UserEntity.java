package com.zephiro.auth.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String mail;
    private String password;
    private LocalDate birthdate;

    public UserEntity(Long id, String name, String mail, String password, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.birthdate = birthdate;
    }

    public UserEntity(String name, String mail, String password, LocalDate birthdate) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.birthdate = birthdate;
    }

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getMail() {
        return mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public LocalDate getBirthdate() {
        return birthdate;
    }
    
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
