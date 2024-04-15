package com.maids.librarymanagementsystem.config.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maids.librarymanagementsystem.utils.model.ResponseObject;
import com.maids.librarymanagementsystem.config.security.model.CustomUserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthService {

    public void buildLoginSuccessResponse(HttpServletRequest request, HttpServletResponse servletResponse,
                                          CustomUserDetails userDetails) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String response = "";
        response = mapper.writeValueAsString(ResponseObject.LOGGED_IN(userDetails, null));

        try {
            servletResponse.setContentType("application/json");
            servletResponse.getWriter().print(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buildLoginFailedResponse(HttpServletRequest request, HttpServletResponse response,String message) {

        try {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json");
            String json = mapper.writeValueAsString( new ResponseObject<>(message,ResponseObject.ReturnCode.FAILED,null, null,null));
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
