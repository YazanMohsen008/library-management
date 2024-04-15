package com.maids.librarymanagementsystem.config.security.filter;

import com.maids.librarymanagementsystem.config.security.service.AuthService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private final AuthService authService;

    public JWTAuthenticationEntryPoint(AuthService authService) {
        this.authService = authService;
    }

    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException){
        authService.buildLoginFailedResponse(request, response, authException.getMessage());
    }
}