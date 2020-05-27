package com.kkhstudy.nutube.api.controller;

import com.kkhstudy.nutube.api.data.UserWithToken;
import com.kkhstudy.nutube.domain.Account;
import com.kkhstudy.nutube.form.RegisterForm;
import com.kkhstudy.nutube.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UsersApi {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/api/jwt/register")
    public UserWithToken registerUser(@RequestBody @Valid RegisterForm registerForm) {
        // TODO image

        Account user = Account.createAccount(registerForm.getEmail(), passwordEncoder.encode(registerForm.getPassword()), registerForm.getUsername(), null);
        Account newAccount = accountRepository.save(user);

        UserWithToken ret = new UserWithToken(newAccount, null);
        return ret;
    }
}
