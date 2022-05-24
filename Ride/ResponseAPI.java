package com.example.exd03.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

@AllArgsConstructor @Data
public class ResponseAPI {
    private String message;
    private Integer statusCode;
    private FieldError errors;
}
