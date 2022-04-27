package com.example.projectTestDemo.controller;

import com.example.projectTestDemo.dtoRequest.TestRequest;
import com.example.projectTestDemo.dtoRequest.jaxBRequest.ManagePeopleJaxBRequest;
import com.example.projectTestDemo.dtoRequest.jaxBRequest.ManagePeopleJaxBResponse;
import com.example.projectTestDemo.dtoResponse.ManagePeopleViewResponse;
import com.example.projectTestDemo.model.RequestMapper;
import com.example.projectTestDemo.service.AsyncService;
import com.example.projectTestDemo.service.validation.ValidationAbstract;
import com.example.projectTestDemo.service.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ValidatorFactory validatorFactory;
    @Autowired
    private RequestMapper requestMapper;
    @Autowired
    private AsyncService asyncService;

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

    @RequestMapping(value = "/getDataJaxB", method = RequestMethod.POST)
    public ManagePeopleJaxBRequest getDataTestJaxB(
            @RequestParam(name = "message") String message
    ){
        return this.requestMapper.mapToManagePeopleJaxBResponse(message);
    }

    @RequestMapping(value = "/getDataJaxB3", method = RequestMethod.POST)
    public ManagePeopleJaxBResponse getDataTestJaxB3(
            @RequestParam(name = "message") String message
    ) {
        return this.requestMapper.mapToManagePeopleJaxBResponseGetByRoot(message);
    }

    @GetMapping(value = "/getDataJaxB2")
    public String getDataTestJaxB(
    ) throws Exception {
        return this.requestMapper.mapToManagePeopleJaxBString();
    }

    @GetMapping(value = "/getDataAsync")
    public ManagePeopleViewResponse getDataAsync(
            HttpServletRequest httpServletRequest
    ){
        CompletableFuture<ManagePeopleViewResponse> data = this.asyncService.inquiryGetDataRestTemplate("001",httpServletRequest.getHeader("token"));
        return data.join();
    }
}
