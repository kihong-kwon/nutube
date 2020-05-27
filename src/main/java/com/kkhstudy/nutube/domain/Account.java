package com.kkhstudy.nutube.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String username;
    private String password;
    private String bio;
    @Lob @Basic(fetch = FetchType.EAGER)
    private String image;

    private String role;

    public static Account createAccount(String email, String password, String username, String image) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setUsername(username);
        account.setImage(image);
        account.setRole("ROLE_USER");
        return account;
    }
}
