package com.example.projectTestDemo.dtoRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {
    private String username;
    private String password;
    private String confirmPassword;
    private String peopleDetailId;
}
