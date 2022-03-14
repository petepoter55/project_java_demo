package com.example.projectTestDemo.dtoResponse;

import java.util.HashMap;

public class ValidateXmlResponse {
    private String code;
    private String message;
    private boolean status;
    private HashMap<String ,String> data;

    public ValidateXmlResponse(boolean status, String message,String code,HashMap<String ,String> data) {
        super();
        this.status = status;
        this.message = message;
        this.code = code;
        this.data = data;
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

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
}
