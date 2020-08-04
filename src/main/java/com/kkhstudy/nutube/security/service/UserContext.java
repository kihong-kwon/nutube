package com.kkhstudy.nutube.security.service;

import com.kkhstudy.nutube.domain.Users;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserContext extends org.springframework.security.core.userdetails.User {

    private final Users users;

    public UserContext(Users users, Collection<? extends GrantedAuthority> authorities) {
        super(users.getEmail(), users.getPassword(), authorities);
        this.users = users;
    }

    public Users getUsers() {
        return users;
    }
}
