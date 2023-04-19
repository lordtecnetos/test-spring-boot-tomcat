package com.example.demo;

import java.util.function.Supplier;

public class ServiceException extends RuntimeException {

    public ServiceException(Throwable e, String message) {
        super(message, e);
    }

    public ServiceException(String message) {
        super(message);
    }

    public static <T> Supplier<ServiceException> notFound(Class<T> clazz) {
        return () -> new ServiceException("erro.%s.not.found".formatted(clazz.getSimpleName().toLowerCase()));
    }

}
