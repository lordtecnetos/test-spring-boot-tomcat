package com.example.demo;

import java.util.function.Supplier;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final Object[] args;

    public ServiceException(Throwable e, String message, Object... args) {
        super(message, e);
        this.args = args;
    }

    public ServiceException(String message, Object... args) {
        super(message);
        this.args = args;
    }

    public static <T> Supplier<ServiceException> notFound(Class<T> clazz) {
        return () -> new ServiceException("erro.%s.not.found".formatted(clazz.getSimpleName().toLowerCase()));
    }

}
