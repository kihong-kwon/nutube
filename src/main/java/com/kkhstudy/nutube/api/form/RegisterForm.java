package com.kkhstudy.nutube.api.form;

import com.kkhstudy.nutube.api.data.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor
public class RegisterForm {
    @Valid
    User user;
}
