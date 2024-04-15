package com.maids.librarymanagementsystem.config.security.filter;


import com.maids.librarymanagementsystem.config.security.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final TokenAuthenticationService tokenAuthenticationService;


    @Autowired
    public JWTAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Delegates authentication to the TokenAuthenticationService
        Authentication authentication = tokenAuthenticationService.getAuthentication(request);

        // Apply the authentication to the SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);


        // Go on processing the request
        filterChain.doFilter(request, response);
        // Clears the context from authentication
        resetContextHolders();
        SecurityContextHolder.getContext().setAuthentication(null);
    }


    private void resetContextHolders() {
        LocaleContextHolder.resetLocaleContext();
        RequestContextHolder.resetRequestAttributes();
    }

}
