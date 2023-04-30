package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.view.exception.MessageException;
import com.example.demo.view.model.Message;

@ControllerAdvice
public class ControllerAdviceHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MessageException.class)
    public @ResponseBody Message[] handle(MessageException e) {
        return e.getMessages();
    }

}
