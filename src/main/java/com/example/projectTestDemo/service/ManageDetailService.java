package com.example.projectTestDemo.service;

import com.example.projectTestDemo.dtoRequest.CreateAccountRequest;
import com.example.projectTestDemo.dtoRequest.ExportExcelRequest;
import com.example.projectTestDemo.dtoRequest.LoginRequest;
import com.example.projectTestDemo.dtoRequest.ManageDetailGenXmlRequest;
import com.example.projectTestDemo.dtoResponse.ImportExcelManageUserResponse;
import com.example.projectTestDemo.dtoResponse.JwtResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.entity.ManageUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public interface ManageDetailService {
    public String generateXml() throws JsonProcessingException;

    public Response createAccount(CreateAccountRequest createAccountRequest);

    public String gen ();

    public Response login(LoginRequest loginRequest);

    public void exportExcel(ExportExcelRequest exportExcelRequest, HttpServletResponse response);

    public ImportExcelManageUserResponse importExcel(MultipartFile file) throws IOException;

    public Response sendMessageToQueue();

    public Response testValidationLoginRequest(String jsonRequest);
}
