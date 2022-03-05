package com.example.projectTestDemo.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ResponseException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String exceptionCode;
    private Object data;

    public ResponseException() {}

    public ResponseException(String exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public ResponseException(String exceptionCode, String message, Throwable t) {
        super(message, t);
        this.exceptionCode = exceptionCode;
    }

    public ResponseException(String exceptionCode, String message, Object data) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.data = data;
    }
}
