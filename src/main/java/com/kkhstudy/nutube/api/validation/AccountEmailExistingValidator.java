package com.kkhstudy.nutube.api.validation;

import com.kkhstudy.nutube.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class AccountEmailExistingValidator implements ConstraintValidator<AccountEmailExisting, String> {

    private final AccountRepository accountRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return accountRepository.findByEmail(email) == null;
    }
}
