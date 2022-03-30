package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoResponse.JwtResponse;
import com.example.projectTestDemo.entity.ManageUser;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.repository.UserRepository;
import com.example.projectTestDemo.service.JwtService;
import com.example.projectTestDemo.service.VerifyUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyUserImplement implements VerifyUserService {
    private static final Logger logger = Logger.getLogger(VerifyUserImplement.class);
    @Autowired
    private ManageDetailImplement manageDetailImplement;
    @Autowired
    private JwtImplement jwtImplement;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void verifyUser(String token) throws ResponseException {
        logger.info("===== Start verify interceptor =======");
        try {
            if (token != null && !token.isEmpty()) {
                JwtResponse jwtResponse = this.jwtImplement.getDataJwt(token);
                ManageUser manageUser = this.userRepository.findByUsername(jwtResponse.getUsername());
                if (manageUser == null) {
                    throw new ResponseException("404", "Data Not Found");
                }
            } else {
                throw new ResponseException("404", "Token Not Found");
            }
        } catch (ResponseException e) {
            throw new ResponseException(e.getExceptionCode(),e.getMessage());
        }
        logger.info("===== done verify interceptor =======");
    }
}
