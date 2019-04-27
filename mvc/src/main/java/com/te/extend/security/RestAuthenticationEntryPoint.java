package com.te.extend.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("restAuthenticationEntryPoint") //with @Component, it becomes a bean, so SecurityConfig can be autowired
public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint { //import class
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized"); // int SC_UNAUTHORIZED = 401; click SC_UNAUTHORIZED
    }
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException{
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
//    }

}
