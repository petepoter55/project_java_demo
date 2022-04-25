package com.example.projectTestDemo.controller;

import com.example.projectTestDemo.dtoRequest.MangeRegisterRequest;
import com.example.projectTestDemo.dtoRequest.TestRequest;
import com.example.projectTestDemo.dtoResponse.ManagePeopleViewResponse;
import com.example.projectTestDemo.model.RequestMapper;
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
    @Autowired
    private RequestMapper requestMapper;

    @GetMapping(value = "/getDataTest")
    public ValidationAbstract getDataTest(
            @RequestParam(name = "servicename") String servicename
    ){
        return this.validatorFactory.getValidator(servicename);
    }

    @GetMapping(value = "/getDataTest2")
    public TestRequest getDataTest2(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "username") String username
    ){
        return new TestRequest.Builder().firstname(firstName).lastName(lastName).username(username).build();
    }

    @GetMapping(value = "/getDataJaxB")
    public String getDataTestJaxB(
    ) throws Exception {
        return this.requestMapper.mapToManagePeopleJaxBString();
    }
}
