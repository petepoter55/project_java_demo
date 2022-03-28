package com.example.projectTestDemo.response;

import com.example.projectTestDemo.dtoResponse.ManagePeopleDetailResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResponseManagePeopleTest {
    private boolean status;
    private String message;
    @JsonProperty("data")
    private List<ManagePeopleDetailResponse> managePeopleDetailResponse;
}
