package com.example.projectTestDemo.dtoResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ManagePeopleViewResponse {
    private boolean status;
    private String message;
    @JsonProperty("data")
    private List<ManagePeopleDetailResponse> managePeopleDetailResponse;
}
