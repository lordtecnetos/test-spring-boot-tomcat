package com.example.demo;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessagesComponent {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String WARNING = "warning";
    public static final String INFO = "info";

    private final MessageSource messageSource;

    public void success(RedirectAttributes redirect, String code, Object... args) {
        this.message(redirect, SUCCESS, code, args);
    }

    public void error(RedirectAttributes redirect, String code, Object... args) {
        this.message(redirect, ERROR, code, args);
    }

    public void error(BindingResult result, ServiceException e) {
        result.reject(e.getMessage(), e.getArgs(), e.getMessage());
    }

    public void warning(RedirectAttributes redirect, String code, Object... args) {
        this.message(redirect, WARNING, code, args);
    }

    public void info(RedirectAttributes redirect, String code, Object... args) {
        this.message(redirect, INFO, code, args);
    }

    public void message(RedirectAttributes redirect, String type, String code, Object... args) {
        this.message(type, code, args).addTo(redirect);
    }

    public Message success(String code, Object... args) {
        return this.message(SUCCESS, code, args);
    }

    public Message error(String code, Object... args) {
        return this.message(ERROR, code, args);
    }

    public MessageException error(ServiceException e) {
        return new MessageException(e, this.error(e.getMessage(), e.getArgs()));
    }

    public Message warning(String code, Object... args) {
        return this.message(WARNING, code, args);
    }

    public Message info(String code, Object... args) {
        return this.message(INFO, code, args);
    }

    public void validate(BindingResult result) {
        if (result.hasErrors()) {
            throw new MessageException(result.getAllErrors().stream().map(e -> error(e.getCode(), e.getArguments()))
                    .toArray(Message[]::new));
        }
    }

    public Message message(String type, String code, Object... args) {
        return new Message(type, i18n(code, args));
    }

    public String i18n(String code, Object... args) {
        return messageSource.getMessage(code, args, code, null);
    }

    @Getter
    @RequiredArgsConstructor
    public static class Message {

        private final String type;
        private final String value;

        public void addTo(RedirectAttributes redirect) {
            redirect.addFlashAttribute(type, this);
        }

    }

    @Getter
    public static class MessageException extends RuntimeException {

        private final Message[] messages;

        public MessageException(Throwable e, Message... messages) {
            super(e);
            this.messages = messages;
        }

        public MessageException(Message... messages) {
            this.messages = messages;
        }

    }

}
