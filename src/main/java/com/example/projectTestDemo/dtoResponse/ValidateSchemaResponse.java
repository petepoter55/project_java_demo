package com.example.projectTestDemo.dtoResponse;

import lombok.Data;

@Data
public class ValidateSchemaResponse {
    private String message;
    private boolean status;

    public ValidateSchemaResponse(boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

}
