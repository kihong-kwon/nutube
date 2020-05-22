package com.kkhstudy.nutube.api;

import com.kkhstudy.nutube.domain.Account;
import com.kkhstudy.nutube.dto.LoginDto;
import com.kkhstudy.nutube.dto.RegisterDto;
import com.kkhstudy.nutube.dto.ResultDto;
import com.kkhstudy.nutube.form.LoginForm;
import com.kkhstudy.nutube.form.SignUpForm;
import com.kkhstudy.nutube.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/api/users/register")
    public RegisterDto  registerUser(@RequestBody SignUpForm signUpForm) {
        Account account = Account.createAccount(signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()), signUpForm.getName(), signUpForm.getLastname(), signUpForm.getImage());
        Account newAccount = accountRepository.save(account);

        RegisterDto resultDto = new RegisterDto();
        resultDto.setSuccess(true);
        return resultDto;
    }
/*
    @PostMapping("/api/users/login")
    public LoginDto loginUser(LoginForm form) {
        LoginDto resultDto = new LoginDto();
        resultDto.setLoginSuccess(true);
        return resultDto;
    }*/
}
