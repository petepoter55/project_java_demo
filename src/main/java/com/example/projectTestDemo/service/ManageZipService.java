package com.example.projectTestDemo.service;

import com.example.projectTestDemo.dtoResponse.Response;
import org.springframework.web.multipart.MultipartFile;

public interface ManageZipService {
    public Response uploadZipFile(MultipartFile file);
}
