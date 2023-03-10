package com.example.blogApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;

    public ResourceNotFoundException(String message, String resourceName) {
        super(String.format("%s not found",resourceName));
        this.resourceName = resourceName;
    }
}
