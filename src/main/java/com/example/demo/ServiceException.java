package com.example.demo;

public class ServiceException extends RuntimeException {

    public ServiceException(Throwable e, String message) {
        super(message, e);
    }

}
