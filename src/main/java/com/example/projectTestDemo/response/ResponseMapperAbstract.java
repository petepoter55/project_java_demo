package com.example.projectTestDemo.response;

import com.example.projectTestDemo.exception.ResponseException;

public abstract class ResponseMapperAbstract {
    public abstract String mapSpResponseToJson(String requestName, String response) throws ResponseException;
}
