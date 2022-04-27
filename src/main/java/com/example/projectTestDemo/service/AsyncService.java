package com.example.projectTestDemo.service;

import com.example.projectTestDemo.dtoResponse.ManagePeopleViewResponse;

import java.util.concurrent.CompletableFuture;

public interface AsyncService {
    CompletableFuture<ManagePeopleViewResponse> inquiryGetDataRestTemplate (String manageId,String token);
}
