package com.restapi.rest_demo.exception.AuthException;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message) {
        super(message);
    }
}
