package com.kkhstudy.nutube.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkhstudy.nutube.api.form.LoginForm;
import com.kkhstudy.nutube.security.constants.SecurityConstants;
import com.kkhstudy.nutube.security.token.JwtAuthenticationToken;
import com.kkhstudy.nutube.utils.WebUtil;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public JwtLoginProcessingFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.JWT_AUTH_LOGIN_URL, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (!HttpMethod.POST.name().equals(request.getMethod()) || !WebUtil.isAjax(request)) {
            throw new IllegalStateException("Authenication is not supported");
        }
        LoginForm loginForm = objectMapper.readValue(request.getReader(), LoginForm.class);
        if (StringUtils.isEmpty(loginForm.getEmail()) || StringUtils.isEmpty(loginForm.getPassword())) {
            throw new AuthenticationServiceException("Username or Password is empty");
        }
        JwtAuthenticationToken token = new JwtAuthenticationToken(loginForm.getEmail(), loginForm.getPassword());
        return this.getAuthenticationManager().authenticate(token);
    }

}
