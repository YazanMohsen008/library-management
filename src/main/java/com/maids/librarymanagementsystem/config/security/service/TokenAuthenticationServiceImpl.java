package com.maids.librarymanagementsystem.config.security.service;


import com.maids.librarymanagementsystem.config.security.model.CustomUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    private TokenHandler tokenHandler;

    @Autowired
    public void setTokenHandler(TokenHandler tokenHandler) {
        this.tokenHandler = tokenHandler;
    }

    /**
     * When a user successfully logs into the application, create a token for that user.
     *
     * @param res An http response that will be filled with an 'Authentication' header containing the token.
     */
    public void addAuthentication(HttpServletRequest request, HttpServletResponse res, Authentication authentication) {
        String JWT = tokenHandler.build((CustomUserDetails) authentication.getPrincipal());
        res.addHeader(tokenHandler.HEADER_STRING, tokenHandler.TOKEN_PREFIX + " " + JWT);
    }

    /**
     * The JWTAuthenticationFilter calls this method to verify the user authentication.
     * If the token is not valid, the authentication fails and the request will be refused.
     *
     * @param request An http request that will be check for authentication token to verify.
     * @return Authentication
     */
    public Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(tokenHandler.HEADER_STRING);

        if (token != null && token.startsWith(tokenHandler.TOKEN_PREFIX)) {
            // Parse the token.
            CustomUserDetails user = null;

            try {
                user = tokenHandler.parse(request, token);
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
            } catch (UnsupportedJwtException e) {
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                e.printStackTrace();
            } catch (SignatureException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

            if (user != null)
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            else
                return null;


        }

        return null;

    }


}
