package com.maids.librarymanagementsystem.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maids.librarymanagementsystem.config.security.model.CustomUserDetails;
import com.maids.librarymanagementsystem.config.security.model.LoginRequest;
import com.maids.librarymanagementsystem.config.security.service.AuthService;
import com.maids.librarymanagementsystem.config.security.service.TokenAuthenticationService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component("jwtLoginFilter")
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {


    private static String LOGIN_URL ="/login";


    private TokenAuthenticationService tokenAuthenticationService;
    private final AuthService authService;

    public JWTLoginFilter( TokenAuthenticationService tokenAuthenticationService, AuthService authService) {
        super(new AntPathRequestMatcher(LOGIN_URL));
        this.authService = authService;
        setAuthenticationManager(authentication -> null);
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {

        // Retrieve username and password from the http request and save them in an Account object.
        String username = "";
        String password = "";

        try {
            LoginRequest loginRequest = new ObjectMapper().readValue(req.getReader(), LoginRequest.class);
            username = loginRequest.getUsername();
            password = loginRequest.getPassword();

            if (loginRequest.getUsername() == null || loginRequest.getUsername().isEmpty()
                    || loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        ServletRequestAttributes attributes = new ServletRequestAttributes(req, res);
        initContextHolders(req, attributes);
        // Verify if the correctness of login details.
        // If correct, the successfulAuthentication() method is executed.
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password,
                        new ArrayList<>()
                )
        );
    }

    @Override
    public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                         Authentication auth) {
        try {
            // Pass authenticated user data to the tokenAuthenticationService in order to add a JWT to the http response.
            tokenAuthenticationService.addAuthentication(req, res, auth);
            authService.buildLoginSuccessResponse(req, res, (CustomUserDetails) auth.getPrincipal());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        authService.buildLoginFailedResponse(request,response,failed.getMessage());
      }

    private void initContextHolders(HttpServletRequest request, ServletRequestAttributes requestAttributes) {
        LocaleContextHolder.setLocale(request.getLocale());
        RequestContextHolder.setRequestAttributes(requestAttributes);
        if (logger.isTraceEnabled()) {
            logger.trace("Bound request context to thread: " + request);
        }
    }
}
