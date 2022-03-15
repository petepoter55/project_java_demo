package com.example.projectTestDemo.dtoResponse;

import com.example.projectTestDemo.entity.ManageUser;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ImportExcelManageUserResponse {
    private String code;
    private String message;
    private boolean status;
    @JsonProperty("data")
    private List<ManageUser> manageUserList;

    public ImportExcelManageUserResponse(boolean status, String message, String code, List<ManageUser> manageUserList) {
        super();
        this.status = status;
        this.message = message;
        this.code = code;
        this.manageUserList = manageUserList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ManageUser> getManageUserList() {
        return manageUserList;
    }

    public void setManageUserList(List<ManageUser> manageUserList) {
        this.manageUserList = manageUserList;
    }
}
