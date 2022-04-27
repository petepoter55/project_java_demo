package com.example.projectTestDemo.service;

import org.springframework.http.ResponseEntity;

public interface ManageDetailVelocityService {
    public ResponseEntity<String> getDateVelocity(String manageId);
}
