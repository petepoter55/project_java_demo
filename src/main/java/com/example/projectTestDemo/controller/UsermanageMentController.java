package com.example.projectTestDemo.controller;

import com.example.projectTestDemo.dtoRequest.CreateAccountRequest;
import com.example.projectTestDemo.dtoRequest.LoginRequest;
import com.example.projectTestDemo.dtoResponse.JwtResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/manage")
public class UsermanageMentController {
    @Autowired
    ManageDetailService manageDetailService;
    @Autowired
    JwtService jwtService;


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

    @PostMapping(value = "/login-user-test" ,consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Response testValidateRequest(
            @RequestBody (required = true) String jsonRequest
    ){
        return this.manageDetailService.testValidationLoginRequest(jsonRequest);
    }

    @PostMapping(value = "/get-token" ,consumes = { MediaType.APPLICATION_JSON_VALUE })
    public JwtResponse getTokenData(
            @RequestBody LoginRequest loginRequest
    ){
        return this.jwtService.getDataToken(loginRequest);
    }
    @PostMapping(value = "/generate-token")
    public Response generateToken(
            @RequestParam("username") String username,
            @RequestParam("managePeopleTaxId") String managePeopleTaxId,
            @RequestParam("beforeToken") String beforeToken
    ) {
        return this.jwtService.generateToken(username,managePeopleTaxId,beforeToken);
    }

}
