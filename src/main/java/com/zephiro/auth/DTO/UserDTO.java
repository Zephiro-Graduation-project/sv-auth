package com.zephiro.auth.DTO;

public class UserDTO {

    private String mail;
    private String password;

    public UserDTO(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public UserDTO(String mail) {
        this.mail = mail;
    }

    public UserDTO() {
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
}
