package com.kkhstudy.nutube.api.data;

import com.kkhstudy.nutube.domain.Users;
import lombok.Getter;

@Getter
public class UserWithToken {
    private String email;
    private String username;
    private String bio;
    private String image;
    private String token;

    public UserWithToken(Users users, String token) {
        this.email = users.getEmail();
        this.username = users.getUsername();
        this.bio = users.getBio();
        this.image = users.getImage();
        this.token = token;
    }
}