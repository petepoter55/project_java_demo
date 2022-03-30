package com.example.projectTestDemo.interceptor;

import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.service.VerifyUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final static Logger logger = Logger.getLogger(AuthenticationInterceptor.class);

    @Autowired
    private VerifyUserService verifyUserService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        try {
            this.verifyUserService.verifyUser(httpServletRequest.getHeader("token"));
        }catch (ResponseException e){
            logger.info(e.getMessage(), e);
            httpServletResponse.setHeader("content-type", "application/json");
            PrintWriter out = httpServletResponse.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(new Response(false,e.getMessage(),e.getExceptionCode()));
            out.print(json);
            return false;
        }
        return true;
    }
}
