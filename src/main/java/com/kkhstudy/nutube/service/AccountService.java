package com.kkhstudy.nutube.service;

import com.kkhstudy.nutube.domain.Account;
import com.kkhstudy.nutube.form.SignUpForm;
import com.kkhstudy.nutube.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

}
