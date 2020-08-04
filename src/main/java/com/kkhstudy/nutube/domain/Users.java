package com.kkhstudy.nutube.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Users {

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

    public static Users createUser(String email, String password, String username, String image) {
        Users users = new Users();
        users.setEmail(email);
        users.setPassword(password);
        users.setUsername(username);
        users.setImage(image);
        users.setRole("ROLE_USER");
        return users;
    }
}
