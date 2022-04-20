package com.example.projectTestDemo.controller;

import com.example.projectTestDemo.dtoRequest.MangeRegisterRequest;
import com.example.projectTestDemo.dtoResponse.ManagePeopleViewResponse;
import com.example.projectTestDemo.service.validation.ValidationAbstract;
import com.example.projectTestDemo.service.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ValidatorFactory validatorFactory;

    @GetMapping(value = "/getDataTest")
    public ValidationAbstract getDataTest(
            @RequestParam(name = "servicename") String servicename
    ){
        return this.validatorFactory.getValidator(servicename);
    }
}
