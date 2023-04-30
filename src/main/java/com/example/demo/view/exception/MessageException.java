package com.example.demo.view.exception;

import com.example.demo.view.model.Message;

import lombok.Getter;

@Getter
public class MessageException extends RuntimeException {

    private final Message[] messages;

    public MessageException(Throwable e, Message... messages) {
        super(e);
        this.messages = messages;
    }

    public MessageException(Message... messages) {
        this.messages = messages;
    }

}
