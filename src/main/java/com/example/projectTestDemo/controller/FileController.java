package com.example.projectTestDemo.controller;

import com.example.projectTestDemo.dtoRequest.ExportExcelRequest;
import com.example.projectTestDemo.dtoRequest.UnzipRequest;
import com.example.projectTestDemo.dtoResponse.ImportExcelManageUserResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.dtoResponse.ValidateXmlResponse;
import com.example.projectTestDemo.service.FileService;
import com.example.projectTestDemo.service.ManageFileXmlService;

import com.example.projectTestDemo.service.ManageZipService;
import com.example.projectTestDemo.service.implement.ManageZipImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;
    @Autowired
    ManageZipImplement manageZipImplement;
    @Autowired
    private ManageZipService manageZipService;
    @Autowired
    ManageFileXmlService manageFileXmlService;

    @GetMapping(value = "/export-excel" ,consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void exportExcel(
            HttpServletResponse response,
            @RequestBody ExportExcelRequest exportExcelRequest
    ) throws ParseException {
        this.fileService.exportExcel(exportExcelRequest, response);
    }

    @PostMapping(value = "/import-excel")
    public ImportExcelManageUserResponse importExcel(
            @RequestParam("upFile") MultipartFile file
    ) throws ParseException, IOException {
        return this.fileService.importExcel(file);
    }

    @PostMapping(value = "/upload-zipfile")
    public Response uploadZipFile(
            @RequestParam("upFile") MultipartFile file
    ){
        return this.manageZipService.uploadZipFile(file);
    }

    // validate element <soap:Envelope ....</soap:Envelope> Ex. <eb:MessageHeader
    @PostMapping(value = "/validate-Ebxm")
    public ValidateXmlResponse validateEbxml(
            @RequestParam("data") String message
    ) throws IOException {
        return this.manageFileXmlService.validateEbXml(message);
    }

    @PostMapping(value = "/unzip-zipFile")
    public void unzipZipFile(
            @RequestBody UnzipRequest unzipRequest
    ){
        this.manageZipService.unzipFile(unzipRequest);
    }

    @PostMapping(value = "/unzip-validateTest")
    public List<String> unzipZipFileTest(

    ){
        return this.manageZipImplement.validateFile();
    }

    @PostMapping(value = "/change-formatXml")
    public String changeFormatXML(
            @RequestParam("upFile") MultipartFile file
    ){
        return this.manageFileXmlService.changeFormatXml(file);
    }
}
