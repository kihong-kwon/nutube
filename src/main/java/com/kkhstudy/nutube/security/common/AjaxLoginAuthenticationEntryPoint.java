package com.kkhstudy.nutube.security.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkhstudy.nutube.dto.ErrorDto;
import com.kkhstudy.nutube.dto.ResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 인증이 필요한 서비스에 접근했는데 인증이 되지 못했을때의 처리
public class AjaxLoginAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResultDto<Object> result = new ResultDto<>();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        result.setErr(ErrorDto.builder().errmsg(HttpStatus.UNAUTHORIZED.toString()).build());
        String jsonResult = objectMapper.writeValueAsString(result);
        response.getWriter().write(jsonResult);
    }
}
