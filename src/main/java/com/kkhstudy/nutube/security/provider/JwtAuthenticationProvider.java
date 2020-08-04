package com.kkhstudy.nutube.security.provider;

import com.kkhstudy.nutube.security.service.UserContext;
import com.kkhstudy.nutube.security.token.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserContext userContext = (UserContext) userDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userContext.getUsers().getPassword())) {
            throw new BadCredentialsException("BadCredentialsException");
        }
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(userContext.getUsers(), null, userContext.getAuthorities());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
