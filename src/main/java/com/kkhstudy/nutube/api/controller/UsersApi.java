package com.kkhstudy.nutube.api.controller;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.kkhstudy.nutube.api.data.UserWithToken;
import com.kkhstudy.nutube.api.form.RegisterForm;
import com.kkhstudy.nutube.api.validation.AccountEmailExisting;
import com.kkhstudy.nutube.domain.Users;
import com.kkhstudy.nutube.repository.UsersRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UsersApi {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/api/jwt/users")
    public UserWithToken registerUser(@RequestBody @Valid RegisterParam registerParam) {
        // TODO image
        Users users = Users.createUser(registerParam.getEmail(), passwordEncoder.encode(registerParam.getPassword()), registerParam.getUsername(), null);
        Users newUsers = usersRepository.save(users);

        UserWithToken ret = new UserWithToken(newUsers, null);
        return ret;
    }

    @PostMapping("/api/jwt/login")
    public UserWithToken loginUser(@RequestBody @Valid LoginParam loginParam) {
        Users users = usersRepository.findByEmail(loginParam.getEmail());
        UserWithToken userWithToken = new UserWithToken(users, "");
        return null;
    }

    @PostMapping("/api/ajax/register")
    public UserWithToken registerAjaxUser(@RequestBody @Valid RegisterForm registerForm) {


        return null;
    }

    private Map<String, Object> userResponse(UserWithToken userWithToken) {
        return new HashMap<String, Object>() {{
            put("user", userWithToken);
        }};
    }
}

@Getter
@JsonRootName(value = "user")
@NoArgsConstructor
class LoginParam {
    @NotBlank(message = "can't be empty")
    @Email(message = "should be an email")
    private String email;
    @NotBlank(message = "can't be empty")
    private String password;
}

@Getter
@JsonRootName(value = "user")
@NoArgsConstructor
class RegisterParam {
    @NotBlank(message = "can't be empty")
    @Email(message = "should be an email")
    @AccountEmailExisting
    private String email;
    @NotBlank(message = "can't be empty")
    private String username;
    @NotBlank(message = "can't be empty")
    private String password;
}