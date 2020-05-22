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

    private String password;

    private String name;

    private String lastName;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    private String role;

    public static Account createAccount(String email, String password, String name, String lastName, String profileImage) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setName(name);
        account.setLastName(lastName);
        account.setProfileImage(profileImage);
        account.setRole("ROLE_USER");
        return account;
    }
}
