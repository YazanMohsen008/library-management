package com.maids.librarymanagementsystem.config.security.service;

import com.maids.librarymanagementsystem.config.security.model.CustomUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class TokenHandler {

    private ApplicationUserDetailsService userDetailsService;
    ResourceBundle tokenProperties = ResourceBundle.getBundle("auth");

    //  houres * minutes * seconds * millies
    final long EXPIRATIONTIME = 20 * 24 * 60 * 60 * 1000;

    final String SECRET = tokenProperties.getString("SECRET");
    final public String TOKEN_PREFIX = tokenProperties.getString("TOKEN_PREFIX");
    final public String HEADER_STRING = tokenProperties.getString("HEADER_STRING");

    @Autowired
    public void setUserDetailsService(ApplicationUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public Map<String, Object> buildUserDetails(CustomUserDetails customUserDetails) {

        Map<String, Object> userDetails = new HashMap<String, Object>();


        if (customUserDetails.getApplicationUser() != null) {
            userDetails.put("userId", customUserDetails.getApplicationUser().getId());
            userDetails.put("username", customUserDetails.getApplicationUser().getUsername());
        }
        return userDetails;
    }

    public String build(CustomUserDetails customUserDetails) {
        try {
            String JWT = "";
            Map<String, Object> userDetails = new HashMap<String, Object>();

            userDetails = this.buildUserDetails(customUserDetails);

            JWT = Jwts.builder()
                    .setId(UUID.randomUUID().toString())
                    .setSubject("")
                    .setClaims(userDetails)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();

            return JWT;
        } catch (Exception exception) {
            throw exception;
        }
    }

    public CustomUserDetails parse(HttpServletRequest request, String token) {
        try {
            DefaultClaims tokenBody = (DefaultClaims) Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            String username = (String) tokenBody.get("username");

            request.setAttribute("userId", tokenBody.get("userId"));


            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);


            return userDetails;
        } catch (Exception exception) {
            throw exception;
        }
    }


}
