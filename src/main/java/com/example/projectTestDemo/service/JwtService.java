package com.example.projectTestDemo.service;

import com.example.projectTestDemo.dtoRequest.LoginRequest;
import com.example.projectTestDemo.dtoResponse.JwtResponse;
import com.example.projectTestDemo.dtoResponse.Response;

public interface JwtService {
    public JwtResponse getDataToken(LoginRequest loginRequest);

    public Response generateToken(String username, String managePeopleTaxId, String beforeToken);

}
