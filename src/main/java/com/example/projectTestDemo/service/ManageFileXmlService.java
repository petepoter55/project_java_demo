package com.example.projectTestDemo.service;

import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.dtoResponse.ValidateXmlResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ManageFileXmlService {
    public String changeFormatXml(MultipartFile file);

    public ValidateXmlResponse validateEbXml(String message) throws IOException;
}
