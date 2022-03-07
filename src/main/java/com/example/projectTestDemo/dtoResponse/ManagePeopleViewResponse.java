package com.example.projectTestDemo.dtoResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class ManagePeopleViewResponse {
    private boolean status;
    private String message;
    @JsonProperty("data")
    private List<ManagePeopleDetailResponse> managePeopleDetailResponse;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ManagePeopleDetailResponse> getManagePeopleDetailResponse() {
        return managePeopleDetailResponse;
    }

    public void setManagePeopleDetailResponse(List<ManagePeopleDetailResponse> managePeopleDetailResponse) {
        this.managePeopleDetailResponse = managePeopleDetailResponse;
    }
}
