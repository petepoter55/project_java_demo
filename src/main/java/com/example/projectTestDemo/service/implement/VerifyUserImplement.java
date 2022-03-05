package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.service.VerifyUserService;
import org.springframework.stereotype.Service;

@Service
public class VerifyUserImplement implements VerifyUserService {

    @Override
    public void verifyUser() throws ResponseException {
       try {
           System.out.println("yes");
       }catch (ResponseException e){
           throw new ResponseException(e.getExceptionCode(),e.getMessage());
       }
    }
}
