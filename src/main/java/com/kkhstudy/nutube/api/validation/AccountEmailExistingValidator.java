package com.kkhstudy.nutube.api.validation;

import com.kkhstudy.nutube.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class AccountEmailExistingValidator implements ConstraintValidator<AccountEmailExisting, String> {

    private final UsersRepository usersRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return usersRepository.findByEmail(email) == null;
    }
}
