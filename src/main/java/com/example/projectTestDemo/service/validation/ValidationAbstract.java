package com.example.projectTestDemo.service.validation;

import com.example.projectTestDemo.dtoResponse.ValidateSchemaResponse;
import com.example.projectTestDemo.exception.ResponseException;

public abstract class ValidationAbstract {
    public abstract ValidateSchemaResponse validate(String str, String jsonRequest) throws ResponseException;
}
