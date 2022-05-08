package com.example.projectTestDemo.controller;

import com.example.projectTestDemo.dtoRequest.TestRequest;
import com.example.projectTestDemo.dtoRequest.jaxBRequest.ManagePeopleJaxBRequest;
import com.example.projectTestDemo.dtoRequest.jaxBRequest.ManagePeopleJaxBResponse;
import com.example.projectTestDemo.dtoResponse.ManagePeopleViewResponse;
import com.example.projectTestDemo.entity.ManageUser;
import com.example.projectTestDemo.model.RequestMapper;
import com.example.projectTestDemo.repository.UserRepository;
import com.example.projectTestDemo.service.AsyncService;
import com.example.projectTestDemo.service.ManageDetailVelocityService;
import com.example.projectTestDemo.service.implement.FileImplement;
import com.example.projectTestDemo.service.validation.ValidationAbstract;
import com.example.projectTestDemo.service.validation.ValidatorFactory;
import com.example.projectTestDemo.tools.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;
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
    @Autowired
    private ManageDetailVelocityService manageDetailVelocityService;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private FileImplement fileImplement;

    @Autowired
    private UserRepository userRepository;

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

    @RequestMapping(value = "/getDataVelocity", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDataVelocity(
            @RequestBody (required = true) String jsonRequest
    ){
        return this.manageDetailVelocityService.getDateVelocity(jsonRequest);
    }

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public void sendEmail(
    ) {
        this.emailUtil.sendSimpleMessage();
    }

    @RequestMapping(value = "/test2/{id}", method = RequestMethod.GET)
    public ManageUser test2(
            @PathVariable(value = "id") Integer id
    ) {
        Optional<ManageUser> manageUser = this.userRepository.findById(BigInteger.valueOf(id));
        Optional<String> opt = Optional.empty();
        System.out.println(opt);
        return manageUser.isPresent() ? manageUser.get() : null;
    }

    @RequestMapping(value = "/testMoveFile", method = RequestMethod.GET)
    public String testMoveFile(
            @RequestParam(value = "file") MultipartFile file
            ) throws IOException {
       return this.fileImplement.moveFile(file);
    }

}
