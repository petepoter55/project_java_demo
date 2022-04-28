package com.example.projectTestDemo.service;

import com.example.projectTestDemo.dtoRequest.ExportExcelRequest;
import com.example.projectTestDemo.dtoResponse.ImportExcelManageUserResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {
    public ImportExcelManageUserResponse importExcel(MultipartFile file) throws IOException;

    public void exportExcel(ExportExcelRequest exportExcelRequest, HttpServletResponse response);
}
