package com.zephiro.auth.DTO;

public class MailDTO {
    private String mail;

    public MailDTO(String mail) {
        this.mail = mail;
    }

    public MailDTO() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
