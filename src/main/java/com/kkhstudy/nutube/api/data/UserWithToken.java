package com.kkhstudy.nutube.api.data;

import com.kkhstudy.nutube.domain.Account;
import lombok.Getter;

@Getter
public class UserWithToken {
    private String email;
    private String username;
    private String bio;
    private String image;
    private String token;

    public UserWithToken(Account user, String token) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.bio = user.getBio();
        this.image = user.getImage();
        this.token = token;
    }
}