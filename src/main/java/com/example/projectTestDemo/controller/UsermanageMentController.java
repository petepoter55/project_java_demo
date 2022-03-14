package com.example.projectTestDemo.controller;

import com.example.projectTestDemo.dtoRequest.CreateAccountRequest;
import com.example.projectTestDemo.dtoRequest.ExportExcelRequest;
import com.example.projectTestDemo.dtoRequest.LoginRequest;
import com.example.projectTestDemo.dtoResponse.JwtResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.dtoResponse.ValidateXmlResponse;
import com.example.projectTestDemo.service.ManageDetailService;
import com.example.projectTestDemo.service.ManageFileXmlService;
import com.example.projectTestDemo.service.ManagePeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/manage")
public class UsermanageMentController {
    @Autowired
    ManageDetailService manageDetailService;
    @Autowired
    ManagePeopleService managePeopleService;
    @Autowired
    ManageFileXmlService manageFileXmlService;

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

    @GetMapping(value = "/export-excel" ,consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void exportExcel(
            HttpServletResponse response,
            @RequestBody ExportExcelRequest exportExcelRequest
    ) throws ParseException {
        this.manageDetailService.exportExcel(exportExcelRequest, response);
    }

    @PostMapping(value = "/change-formatXml")
    public String changeFormatXML(
            @RequestParam("upFile") MultipartFile file
    ){
        return this.manageFileXmlService.changeFormatXml(file);
    }

    // validate element <soap:Envelope ....</soap:Envelope> Ex. <eb:MessageHeader
    @PostMapping(value = "/validate-Ebxm")
    public ValidateXmlResponse validateEbxml(
            @RequestParam("data") String message
    ) throws IOException {
        return this.manageFileXmlService.validateEbXml(message);
    }
}
