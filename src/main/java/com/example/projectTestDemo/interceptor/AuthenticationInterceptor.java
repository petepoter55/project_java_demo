package com.example.projectTestDemo.interceptor;

import com.example.projectTestDemo.service.VerifyUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final static Logger logger = Logger.getLogger(AuthenticationInterceptor.class);

    @Autowired
    VerifyUserService verifyUserService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        System.out.println("Hello World");
        return true;
    }
}
