package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoResponse.ManagePeopleViewResponse;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.service.AsyncService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
@Service
public class AsyncImplement implements AsyncService {
    private static final Logger logger = Logger.getLogger(AsyncImplement.class);
    @Value("${endpoint.local}")
    private String endpoint;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    @Async
    public CompletableFuture<ManagePeopleViewResponse> inquiryGetDataRestTemplate(String manageId,String token) {
        ResponseEntity<ManagePeopleViewResponse> managePeopleViewResponse = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("token",token);

            HttpEntity<ManagePeopleViewResponse> requestEntity = new HttpEntity<>(null, headers);

            managePeopleViewResponse = restTemplate.exchange("http://localhost:8083/peach/api/getData/{manageId}", HttpMethod.GET,requestEntity,ManagePeopleViewResponse.class,manageId);

        }catch (ResponseException e){
            logger.error(e.getMessage());
        }
        return CompletableFuture.completedFuture(managePeopleViewResponse.getBody());
    }
}
