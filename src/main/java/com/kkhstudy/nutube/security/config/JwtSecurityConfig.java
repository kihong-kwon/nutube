package com.kkhstudy.nutube.security.config;

import com.kkhstudy.nutube.security.common.AjaxLoginAuthenticationEntryPoint;
import com.kkhstudy.nutube.security.filter.JwtLoginProcessingFilter;
import com.kkhstudy.nutube.security.filter.JwtValidateProcessingFilter;
import com.kkhstudy.nutube.security.handler.JwtAuthenticationSuccessHandler;
import com.kkhstudy.nutube.security.provider.JwtAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
@Order(0)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider());
    }

    @Bean
    public AuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider();
    }

    @Bean
    public JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler() {
        return new JwtAuthenticationSuccessHandler();
    }

    @Bean
    public JwtLoginProcessingFilter jwtLoginProcessingFilter() throws Exception {
        JwtLoginProcessingFilter filter = new JwtLoginProcessingFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler());
        return filter;
    }

    @Bean
    public JwtValidateProcessingFilter jwtValidateProcessingFilter() {
        return new JwtValidateProcessingFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/jwt/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new AjaxLoginAuthenticationEntryPoint()) // 인증 필터를 거치지 않는 경우 처리됨.
                .and()
                .addFilterBefore(jwtLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtValidateProcessingFilter(), JwtLoginProcessingFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
