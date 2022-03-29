package com.example.projectTestDemo.request;

import com.example.projectTestDemo.dtoRequest.LoginRequest;
import com.example.projectTestDemo.entity.ManageUser;
import lombok.Data;

@Data
public class ManageDetailLoginTest {
    private LoginRequest loginRequest;
    private ManageUser manageUser;
    private boolean checkpass;

}
