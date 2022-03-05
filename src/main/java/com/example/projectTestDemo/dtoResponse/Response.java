package com.example.projectTestDemo.dtoResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
