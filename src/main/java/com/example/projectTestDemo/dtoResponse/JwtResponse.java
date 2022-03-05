package com.example.projectTestDemo.dtoResponse;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
public class JwtResponse {
    private String username;
    private String email;
    private String managePeopleTaxId;
    private Date issueDate;
}
