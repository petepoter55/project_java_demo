package com.example.projectTestDemo.service;

import com.example.projectTestDemo.exception.ResponseException;

public interface VerifyUserService {
    public void verifyUser(String token) throws ResponseException;
}
