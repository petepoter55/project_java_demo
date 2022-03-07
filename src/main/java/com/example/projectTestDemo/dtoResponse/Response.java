package com.example.projectTestDemo.dtoResponse;

public class Response {
    private String code;
    private String message;
    private boolean status;

    public Response(boolean status, String message,String code) {
        super();
        this.status = status;
        this.message = message;
        this.code = code;
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
}
