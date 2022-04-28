package com.example.projectTestDemo.service;

import com.example.projectTestDemo.dtoRequest.CreateAccountRequest;
import com.example.projectTestDemo.dtoRequest.LoginRequest;
import com.example.projectTestDemo.dtoResponse.Response;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface ManageDetailService {
    public String generateXml() throws JsonProcessingException;

    public Response createAccount(CreateAccountRequest createAccountRequest);

    public Response login(LoginRequest loginRequest);

    public Response testValidationLoginRequest(String jsonRequest);
}
