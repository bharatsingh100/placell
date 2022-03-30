package com.project.placell.models;

import javax.persistence.*;

@Entity
@Table(name = "email_verification_token")
public class EmailVerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //random generated string
    private String token;

    @Column(name = "user_email")
    private String userEmail;

    public EmailVerificationToken() {}

    public EmailVerificationToken(Long id, String token, String userEmail){
        this.id = id;
        this.token = token;
        this.userEmail = userEmail;
    }

    public EmailVerificationToken(String token, String userEmail){
        this.token = token;
        this.userEmail = userEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
