package com.example.projectTestDemo.controller;

import com.example.projectTestDemo.dtoRequest.CreateAccountRequest;
import com.example.projectTestDemo.dtoRequest.LoginRequest;
import com.example.projectTestDemo.dtoResponse.JwtResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.service.ManageDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage")
public class UsermanageMentController {
    @Autowired
    ManageDetailService manageDetailService;

    @PostMapping(value = "/create-user" ,consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Response createUsername(
            @RequestBody CreateAccountRequest createAccountRequest
    ){
        return this.manageDetailService.createAccount(createAccountRequest);
    }

    @PostMapping(value = "/login-user" ,consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Response login(
            @RequestBody LoginRequest loginRequest
    ){
        return this.manageDetailService.login(loginRequest);
    }

    @PostMapping(value = "/get-token" ,consumes = { MediaType.APPLICATION_JSON_VALUE })
    public JwtResponse getTokenData(
            @RequestBody LoginRequest loginRequest
    ){
        return this.manageDetailService.getDataToken(loginRequest);
    }
}
