package com.example.projectTestDemo.service;

import com.example.projectTestDemo.dtoRequest.UnzipRequest;
import com.example.projectTestDemo.dtoResponse.Response;
import org.springframework.web.multipart.MultipartFile;

public interface ManageZipService {
    public Response uploadZipFile(MultipartFile file);

    public void unzipTest(UnzipRequest unzipRequest);
}
