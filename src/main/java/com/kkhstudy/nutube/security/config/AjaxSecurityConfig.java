package com.kkhstudy.nutube.security.config;

import com.kkhstudy.nutube.security.common.AjaxLoginAuthenticationEntryPoint;
import com.kkhstudy.nutube.security.constants.SecurityConstants;
import com.kkhstudy.nutube.security.handler.AjaxAccessDeniedHandler;
import com.kkhstudy.nutube.security.handler.AjaxAuthenticationFailureHandler;
import com.kkhstudy.nutube.security.handler.AjaxAuthenticationSuccessHandler;
import com.kkhstudy.nutube.security.provider.AjaxAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@Slf4j
@Order(0)
public class AjaxSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final AjaxAccessDeniedHandler ajaxAccessDeniedHandler;
    private final AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
    private final AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;
    private final AjaxAuthenticationProvider ajaxAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/ajax/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new AjaxLoginAuthenticationEntryPoint()) // 인증 필터를 거치지 않는 경우 처리됨.
                .accessDeniedHandler(ajaxAccessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        ajaxConfigurer(http);
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    private void ajaxConfigurer(HttpSecurity http) throws Exception {
        http
            .apply(new AjaxLoginConfigurer<>())
            .successHandlerAjax(ajaxAuthenticationSuccessHandler)
            .failureHandlerAjax(ajaxAuthenticationFailureHandler)
            .loginPage(SecurityConstants.AJAX_AUTH_LOGIN_URL)
            .loginProcessingUrl(SecurityConstants.AJAX_AUTH_LOGIN_URL)
            .setAuthenticationManager(authenticationManagerBean());
    }
}
