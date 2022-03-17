package com.example.projectTestDemo.controller;

import com.example.projectTestDemo.dtoRequest.CreateAccountRequest;
import com.example.projectTestDemo.dtoRequest.ExportExcelRequest;
import com.example.projectTestDemo.dtoRequest.LoginRequest;
import com.example.projectTestDemo.dtoResponse.ImportExcelManageUserResponse;
import com.example.projectTestDemo.dtoResponse.JwtResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.dtoResponse.ValidateXmlResponse;
import com.example.projectTestDemo.entity.ManageUser;
import com.example.projectTestDemo.service.ManageDetailService;
import com.example.projectTestDemo.service.ManageFileXmlService;
import com.example.projectTestDemo.service.ManagePeopleService;
import com.example.projectTestDemo.service.ManageZipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/manage")
public class UsermanageMentController {
    @Autowired
    ManageDetailService manageDetailService;
    @Autowired
    ManagePeopleService managePeopleService;
    @Autowired
    ManageFileXmlService manageFileXmlService;
    @Autowired
    ManageZipService manageZipService;

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

    @PostMapping(value = "/import-excel")
    public ImportExcelManageUserResponse importExcel(
            @RequestParam("upFile") MultipartFile file
    ) throws ParseException, IOException {
        return this.manageDetailService.importExcel(file);
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

    @PostMapping(value = "/upload-zipfile")
    public Response uploadZipFile(
            @RequestParam("upFile") MultipartFile file
    ){
        return this.manageZipService.uploadZipFile(file);
    }
}
