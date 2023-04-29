package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.EqualsAndHashCode;
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

    // -- Model

    public void success(Model model, String code, Object... args) {
        this.message(model, SUCCESS, code, args);
    }

    public void error(Model model, String code, Object... args) {
        this.message(model, ERROR, code, args);
    }

    public void error(Model model, BindingResult result) {
        result.getAllErrors().forEach(e -> this.error(model, e.getCode(), e.getArguments()));
    }

    public void error(Model model, ServiceException e) {
        this.error(model, e.getMessage(), e.getArgs());
    }

    // public void reject(BindingResult result, ServiceException e) {
    //     result.reject(e.getMessage(), e.getArgs(), e.getMessage());
    // }

    public void warning(Model model, String code, Object... args) {
        this.message(model, WARNING, code, args);
    }

    public void info(Model model, String code, Object... args) {
        this.message(model, INFO, code, args);
    }

    public void message(Model model, String type, String code, Object... args) {
        var message = this.message(type, code, args);
        var messages = new HashSet<Message>();
        if (model.containsAttribute(type)) {
            messages.addAll((Set<Message>) model.getAttribute(type));
        }
        messages.add(message);

        if (model instanceof RedirectAttributes) {
            var redirect = (RedirectAttributes) model;
            redirect.addFlashAttribute(type, messages);
        } else {
            model.addAttribute(type, messages);
        }
    }

    // -- Returns Message

    public Message success(String code, Object... args) {
        return this.message(SUCCESS, code, args);
    }

    public Message error(String code, Object... args) {
        return this.message(ERROR, code, args);
    }

    public Message warning(String code, Object... args) {
        return this.message(WARNING, code, args);
    }

    public Message info(String code, Object... args) {
        return this.message(INFO, code, args);
    }

    public Message message(String type, String code, Object... args) {
        return new Message(type, i18n(code, args));
    }

    // -- Returns Exception

    public MessageException error(ServiceException e) {
        return new MessageException(e, this.error(e.getMessage(), e.getArgs()));
    }

    public MessageException error(BindingResult result) {
        return new MessageException(result.getAllErrors().stream().map(e -> this.error(e.getCode(), e.getArguments()))
                .toArray(Message[]::new));
    }

    // -- Source

    public String i18n(String code, Object... args) {
        return messageSource.getMessage(code, args, code, null);
    }

    // -- Classes

    @Getter
    @EqualsAndHashCode
    @RequiredArgsConstructor
    public static class Message {

        private final String type;
        private final String value;

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
