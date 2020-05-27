package com.kkhstudy.nutube.form;

import com.kkhstudy.nutube.api.validation.AccountEmailExisting;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class RegisterForm {
    @NotBlank(message = "{NotNull.name}")
    @Email(message = "should be an email")
    @AccountEmailExisting
    private String email;
    @NotBlank(message = "can't be empty")
    private String username;
    @NotBlank(message = "can't be empty")
    private String password;
}
